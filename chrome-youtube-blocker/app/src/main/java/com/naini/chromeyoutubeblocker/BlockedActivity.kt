package com.naini.chromeyoutubeblocker

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class BlockedActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pad = (24 * resources.displayMetrics.density).toInt()
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(pad, pad, pad, pad)
        }
        val title = TextView(this).apply {
            text = "YouTube bloqueado"
            textSize = 30f
            gravity = Gravity.CENTER
        }
        val body = TextView(this).apply {
            text = "YouTube está bloqueado dentro de Chrome. YouTube Music sigue disponible."
            textSize = 18f
            gravity = Gravity.CENTER
            setPadding(0, pad, 0, pad)
        }
        val music = Button(this).apply {
            text = "ABRIR YOUTUBE MUSIC"
            setOnClickListener {
                packageManager.getLaunchIntentForPackage("com.google.android.apps.youtube.music")?.let { startActivity(it) }
            }
        }
        val close = Button(this).apply {
            text = "CERRAR"
            setOnClickListener { finish() }
        }

        root.addView(title, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        root.addView(body, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        root.addView(music, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        root.addView(close, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        setContentView(root)
    }
}
