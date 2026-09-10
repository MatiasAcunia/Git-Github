package com.naini.chromeyoutubeblocker

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.os.SystemClock
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import java.net.URI
import java.util.ArrayDeque

class YouTubeBlockAccessibilityService : AccessibilityService() {

    private var lastBlockAt = 0L

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.packageName?.toString() != CHROME_PACKAGE) return
        val root = rootInActiveWindow ?: return
        val candidateTexts = collectCandidateTexts(root)
        if (candidateTexts.any(::isBlockedYouTubeUrl)) {
            blockNow()
        }
    }

    override fun onInterrupt() = Unit

    private fun collectCandidateTexts(root: AccessibilityNodeInfo): List<String> {
        val out = ArrayList<String>()

        listOf(
            "com.android.chrome:id/url_bar",
            "com.android.chrome:id/search_box_text"
        ).forEach { id ->
            runCatching { root.findAccessibilityNodeInfosByViewId(id) }
                .getOrNull()
                ?.forEach { node ->
                    node.text?.toString()?.let(out::add)
                    node.contentDescription?.toString()?.let(out::add)
                }
        }

        val queue = ArrayDeque<AccessibilityNodeInfo>()
        queue.add(root)
        var visited = 0
        while (queue.isNotEmpty() && visited < 250) {
            val node = queue.removeFirst()
            visited++
            node.text?.toString()?.let { text ->
                if (looksUrlLike(text)) out.add(text)
            }
            node.contentDescription?.toString()?.let { text ->
                if (looksUrlLike(text)) out.add(text)
            }
            for (i in 0 until node.childCount) {
                node.getChild(i)?.let(queue::addLast)
            }
        }
        return out.distinct()
    }

    private fun looksUrlLike(text: String): Boolean {
        val t = text.trim().lowercase()
        return t.contains("youtube.") || t.contains("youtu.be") || t.contains("youtube-nocookie.")
    }

    private fun isBlockedYouTubeUrl(raw: String): Boolean {
        var text = raw.trim().lowercase()
        if (text.isBlank()) return false
        if (!text.contains("://")) text = "https://$text"

        val host = runCatching { URI(text).host?.lowercase() }.getOrNull()
            ?: return raw.lowercase().contains("youtube.com") ||
                raw.lowercase().contains("youtu.be") ||
                raw.lowercase().contains("youtube-nocookie.com")

        return host == "youtube.com" ||
            host.endsWith(".youtube.com") ||
            host == "youtu.be" ||
            host.endsWith(".youtu.be") ||
            host == "youtube-nocookie.com" ||
            host.endsWith(".youtube-nocookie.com")
    }

    private fun blockNow() {
        val now = SystemClock.elapsedRealtime()
        if (now - lastBlockAt < 1200) return
        lastBlockAt = now

        performGlobalAction(GLOBAL_ACTION_BACK)

        val intent = Intent(this, BlockedActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        startActivity(intent)
    }

    companion object {
        private const val CHROME_PACKAGE = "com.android.chrome"
    }
}
