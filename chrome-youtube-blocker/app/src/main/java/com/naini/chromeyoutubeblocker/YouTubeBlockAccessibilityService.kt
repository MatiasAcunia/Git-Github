package com.naini.chromeyoutubeblocker

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.os.SystemClock
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import java.net.URI
import java.util.Locale

/**
 * Browser-only accessibility fallback. No page-wide scanning: text inside a search result
 * must never be interpreted as the current URL. Keep the event callback short and fail open.
 */
class YouTubeBlockAccessibilityService : AccessibilityService() {
    private var lastInspection = 0L
    private var lastBlock = 0L
    private var blocking = false

    override fun onServiceConnected() {
        super.onServiceConnected()
        record("connected")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        val pkg = event?.packageName?.toString() ?: return
        if (pkg !in BROWSERS) return
        val now = SystemClock.elapsedRealtime()
        if (blocking || now - lastInspection < 180L) return
        lastInspection = now
        try {
            val root = rootInActiveWindow ?: return
            val urls = when (pkg) {
                "com.android.chrome" -> listOf("com.android.chrome:id/url_bar", "com.android.chrome:id/search_box_text")
                "com.mi.globalbrowser" -> listOf("com.mi.globalbrowser:id/url_bar", "com.mi.globalbrowser:id/search_box_text", "com.mi.globalbrowser:id/address_bar")
                else -> listOf("com.android.browser:id/url_bar", "com.android.browser:id/search_box_text", "com.android.browser:id/address_bar")
            }
            var blocked = false
            for (id in urls) {
                val nodes = root.findAccessibilityNodeInfosByViewId(id) ?: continue
                for (node in nodes.take(5)) {
                    if (isYouTubeHost(node.text?.toString()) || isYouTubeHost(node.contentDescription?.toString())) {
                        blocked = true
                        break
                    }
                }
                if (blocked) break
            }
            // Some browser builds expose the address bar without a stable resource id.
            // Only examine editable address fields, never arbitrary page text.
            if (!blocked) blocked = findEditableAddress(root)
            if (blocked && now - lastBlock > 1500L) {
                lastBlock = now
                blocking = true
                try {
                    performGlobalAction(GLOBAL_ACTION_BACK)
                    startActivity(Intent(this, BlockedActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    })
                    record("blocked:$pkg")
                } finally {
                    blocking = false
                }
            }
        } catch (e: Exception) {
            record("error:" + e.javaClass.simpleName)
        }
    }

    private fun findEditableAddress(root: AccessibilityNodeInfo): Boolean {
        val queue = java.util.ArrayDeque<AccessibilityNodeInfo>()
        queue.add(root)
        var visited = 0
        while (queue.isNotEmpty() && visited++ < 90) {
            val node = queue.removeFirst()
            if (node.isEditable && isYouTubeHost(node.text?.toString())) return true
            for (i in 0 until node.childCount) node.getChild(i)?.let(queue::addLast)
        }
        return false
    }

    private fun isYouTubeHost(raw: String?): Boolean {
        val value = raw?.trim()?.lowercase(Locale.ROOT) ?: return false
        if (value.isEmpty() || value.any { it.isWhitespace() }) return false
        val candidate = if ("://" in value) value else "https://$value"
        val host = try { URI(candidate).host?.lowercase(Locale.ROOT) } catch (_: Exception) { null }
        return host != null && HOSTS.any { host == it || host.endsWith(".$it") }
    }

    private fun record(value: String) {
        try {
            getSharedPreferences("health", MODE_PRIVATE).edit()
                .putLong("last_event_ms", System.currentTimeMillis())
                .putString("last_event", value).apply()
        } catch (_: Exception) { }
    }

    override fun onInterrupt() { record("interrupted") }
    override fun onDestroy() { record("destroyed"); super.onDestroy() }

    companion object {
        private val BROWSERS = setOf("com.android.chrome", "com.mi.globalbrowser", "com.android.browser")
        private val HOSTS = setOf("youtube.com", "youtu.be", "youtube-nocookie.com")
    }
}
