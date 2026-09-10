package com.naini.chromeyoutubeblocker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pad = (24 * resources.displayMetrics.density).toInt()
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setPadding(pad, pad * 2, pad, pad)
        }

        val title = TextView(this).apply {
            text = "No YouTube en Chrome"
            textSize = 28f
            gravity = Gravity.CENTER
        }
        val body = TextView(this).apply {
            text = "Activa una sola vez el servicio de Accesibilidad. Después, cualquier página de YouTube abierta en Chrome se bloqueará automáticamente. YouTube Music no se bloquea."
            textSize = 17f
            gravity = Gravity.CENTER
            setPadding(0, pad, 0, pad)
        }
        val enable = Button(this).apply {
            text = "ACTIVAR BLOQUEADOR"
            setOnClickListener {
                startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
            }
        }
        val music = Button(this).apply {
            text = "ABRIR YOUTUBE MUSIC"
            setOnClickListener { openMusic() }
        }

        root.addView(title, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        root.addView(body, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        root.addView(enable, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        root.addView(music, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        setContentView(root)
    }

    private fun openMusic() {
        packageManager.getLaunchIntentForPackage("com.google.android.apps.youtube.music")?.let {
            startActivity(it)
        }
    }
}
