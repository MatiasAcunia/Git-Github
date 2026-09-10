package com.naini.chromeyoutubeblocker

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.text.InputType
import android.view.ActionMode
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

    private val unlockPhrase =
        "quiero desactivar conscientemente el bloqueo de youtube y aceptar perder tiempo otra vez"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        render()
    }

    override fun onResume() {
        super.onResume()
        render()
    }

    private fun render() {
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
        root.addView(title, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        if (isAccessibilityServiceEnabled()) {
            val status = TextView(this).apply {
                text = "BLOQUEO ACTIVO\n\nModo estricto habilitado. YouTube en Chrome queda bloqueado. YouTube Music sigue permitido."
                textSize = 17f
                gravity = Gravity.CENTER
                setPadding(0, pad, 0, pad)
            }
            root.addView(status, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            val music = Button(this).apply {
                text = "ABRIR YOUTUBE MUSIC"
                setOnClickListener { openMusic() }
            }
            root.addView(music, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            val manage = Button(this).apply {
                text = "DESACTIVAR / ADMINISTRAR"
                setOnClickListener { showStrictGate(root) }
            }
            root.addView(manage, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        } else {
            val body = TextView(this).apply {
                text = "El bloqueador está desactivado. Activa el servicio de Accesibilidad para bloquear automáticamente YouTube dentro de Chrome."
                textSize = 17f
                gravity = Gravity.CENTER
                setPadding(0, pad, 0, pad)
            }
            root.addView(body, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            val enable = Button(this).apply {
                text = "ACTIVAR BLOQUEADOR"
                setOnClickListener {
                    startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
                }
            }
            root.addView(enable, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            val music = Button(this).apply {
                text = "ABRIR YOUTUBE MUSIC"
                setOnClickListener { openMusic() }
            }
            root.addView(music, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }

        setContentView(root)
    }

    private fun showStrictGate(root: LinearLayout) {
        root.removeAllViews()
        val pad = (24 * resources.displayMetrics.density).toInt()

        val title = TextView(this).apply {
            text = "Confirmación estricta"
            textSize = 26f
            gravity = Gravity.CENTER
        }
        root.addView(title, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        val instruction = TextView(this).apply {
            text = "Para abrir los ajustes que permiten desactivar el bloqueador, escribí exactamente esta frase a mano:"
            textSize = 17f
            gravity = Gravity.CENTER
            setPadding(0, pad, 0, pad / 2)
        }
        root.addView(instruction, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        val phrase = TextView(this).apply {
            text = unlockPhrase
            textSize = 18f
            gravity = Gravity.CENTER
            setTextIsSelectable(false)
            setPadding(0, pad / 2, 0, pad)
        }
        root.addView(phrase, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        val input = NoPasteEditText(this).apply {
            hint = "Escribí la frase completa"
            textSize = 17f
            gravity = Gravity.CENTER
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
            isLongClickable = false
            setTextIsSelectable(false)
            importantForAutofill = View.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS
        }
        root.addView(input, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        val confirm = Button(this).apply {
            text = "ABRIR AJUSTES"
            isEnabled = false
        }
        root.addView(confirm, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        val cancel = Button(this).apply {
            text = "CANCELAR"
            setOnClickListener { render() }
        }
        root.addView(cancel, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        input.addTextChangedListener(SimpleTextWatcher {
            confirm.isEnabled = it == unlockPhrase
        })

        confirm.setOnClickListener {
            if (input.text.toString() == unlockPhrase) {
                startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
            }
        }

        input.requestFocus()
        input.post {
            (getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager)
                ?.showSoftInput(input, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    private fun isAccessibilityServiceEnabled(): Boolean {
        val expected = ComponentName(this, YouTubeBlockAccessibilityService::class.java).flattenToString()
        val enabled = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        ) ?: return false
        return enabled.split(':').any { it.equals(expected, ignoreCase = true) }
    }

    private fun openMusic() {
        packageManager.getLaunchIntentForPackage("com.google.android.apps.youtube.music")?.let {
            startActivity(it)
        }
    }
}

private class NoPasteEditText(context: Context) : EditText(context) {
    override fun onTextContextMenuItem(id: Int): Boolean {
        if (id == android.R.id.paste || id == android.R.id.pasteAsPlainText) return false
        return super.onTextContextMenuItem(id)
    }

    override fun startActionMode(callback: ActionMode.Callback?, type: Int): ActionMode? = null
    override fun startActionMode(callback: ActionMode.Callback?): ActionMode? = null

    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        super.onSelectionChanged(text?.length ?: 0, text?.length ?: 0)
    }
}

private class SimpleTextWatcher(
    private val onChanged: (String) -> Unit
) : android.text.TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onChanged(s?.toString().orEmpty())
    }
    override fun afterTextChanged(s: android.text.Editable?) = Unit
}
