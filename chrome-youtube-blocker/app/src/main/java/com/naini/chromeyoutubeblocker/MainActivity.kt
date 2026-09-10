package com.naini.chromeyoutubeblocker

import android.app.Activity
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.ActionMode
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
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
                text = if (isDeviceAdminActive()) {
                    "BLOQUEO ACTIVO Y PROTEGIDO\n\nYouTube en Chrome queda bloqueado. YouTube Music sigue permitido. La protección contra desinstalación está activa."
                } else {
                    "BLOQUEO ACTIVO\n\nYouTube en Chrome queda bloqueado. YouTube Music sigue permitido. Podés agregar una capa extra contra desinstalación accidental."
                }
                textSize = 17f
                gravity = Gravity.CENTER
                setPadding(0, pad, 0, pad)
            }
            root.addView(status, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            if (!isDeviceAdminActive()) {
                val protect = Button(this).apply {
                    text = "PROTEGER CONTRA DESINSTALACIÓN"
                    setOnClickListener { requestDeviceAdmin() }
                }
                root.addView(protect, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            }

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
        var round = 1

        fun renderRound() {
            root.removeAllViews()
            val pad = (24 * resources.displayMetrics.density).toInt()

            val title = TextView(this).apply {
                text = "Confirmación estricta $round/5"
                textSize = 26f
                gravity = Gravity.CENTER
            }
            root.addView(title, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            val instruction = TextView(this).apply {
                text = "Escribí exactamente esta frase a mano. Pegar, autofill, dictado y entradas de varios caracteres se rechazan."
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
                isLongClickable = false
                setPadding(0, pad / 2, 0, pad)
            }
            root.addView(phrase, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            val input = StrictManualEditText(this).apply {
                hint = "Escribí la frase completa"
                textSize = 17f
                gravity = Gravity.CENTER
                inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
                importantForAutofill = View.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS
                setAutofillHints(null)
            }
            root.addView(input, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            val confirm = Button(this).apply {
                text = if (round < 5) "SIGUIENTE" else "ABRIR AJUSTES"
                isEnabled = false
            }
            root.addView(confirm, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            val cancel = Button(this).apply {
                text = "CANCELAR"
                setOnClickListener { render() }
            }
            root.addView(cancel, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            input.setOnManualTextChangedListener { current ->
                confirm.isEnabled = current == unlockPhrase
            }

            confirm.setOnClickListener {
                if (input.text.toString() != unlockPhrase) return@setOnClickListener
                if (round < 5) {
                    round++
                    renderRound()
                } else {
                    startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
                }
            }

            input.requestFocus()
            input.post {
                (getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager)
                    ?.showSoftInput(input, InputMethodManager.SHOW_IMPLICIT)
            }
        }

        renderRound()
    }

    private fun isAccessibilityServiceEnabled(): Boolean {
        val expected = ComponentName(
            packageName,
            "$packageName.YouTubeBlockAccessibilityService"
        ).flattenToString()
        val enabled = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        ) ?: return false
        return enabled.split(':').any { it.equals(expected, ignoreCase = true) }
    }

    private fun isDeviceAdminActive(): Boolean {
        val dpm = getSystemService(DEVICE_POLICY_SERVICE) as DevicePolicyManager
        return dpm.isAdminActive(ComponentName(this, BlockerDeviceAdminReceiver::class.java))
    }

    private fun requestDeviceAdmin() {
        val admin = ComponentName(this, BlockerDeviceAdminReceiver::class.java)
        val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN).apply {
            putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, admin)
            putExtra(
                DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                "Esta capa agrega fricción para evitar desinstalar el bloqueador impulsivamente. Android siempre mantiene una salida desde Ajustes."
            )
        }
        startActivity(intent)
    }

    private fun openMusic() {
        packageManager.getLaunchIntentForPackage("com.google.android.apps.youtube.music")?.let {
            startActivity(it)
        }
    }
}

private class StrictManualEditText(context: Context) : EditText(context) {
    private var internalChange = false
    private var accepted = ""
    private var listener: ((String) -> Unit)? = null

    init {
        isLongClickable = false
        setTextIsSelectable(false)
        customSelectionActionModeCallback = BlockActionModeCallback
        customInsertionActionModeCallback = BlockActionModeCallback

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (internalChange) return
                val next = s?.toString().orEmpty()
                val validAppend = next.length == accepted.length + 1 && next.startsWith(accepted)
                val validDelete = next.length == accepted.length - 1 && accepted.startsWith(next)
                val unchanged = next == accepted

                if (validAppend || validDelete || unchanged) {
                    accepted = next
                    listener?.invoke(accepted)
                } else {
                    internalChange = true
                    setText(accepted)
                    setSelection(accepted.length)
                    internalChange = false
                }
            }

            override fun afterTextChanged(s: Editable?) = Unit
        })
    }

    fun setOnManualTextChangedListener(block: (String) -> Unit) {
        listener = block
        block(accepted)
    }

    override fun onTextContextMenuItem(id: Int): Boolean {
        if (
            id == android.R.id.paste ||
            id == android.R.id.pasteAsPlainText ||
            id == android.R.id.copy ||
            id == android.R.id.cut ||
            id == android.R.id.selectAll
        ) return false
        return super.onTextContextMenuItem(id)
    }

    override fun startActionMode(callback: ActionMode.Callback?, type: Int): ActionMode? = null
    override fun startActionMode(callback: ActionMode.Callback?): ActionMode? = null

    private object BlockActionModeCallback : ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean = false
        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean = false
        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean = false
        override fun onDestroyActionMode(mode: ActionMode?) = Unit
    }
}
