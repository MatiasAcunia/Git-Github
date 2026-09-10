package com.naini.chromeyoutubeblocker

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent

class BlockerDeviceAdminReceiver : DeviceAdminReceiver() {
    override fun onDisableRequested(context: Context, intent: Intent): CharSequence {
        return "Esta protección evita desinstalar el bloqueador impulsivamente. Si continuás, Android permitirá quitar la protección desde Ajustes."
    }
}
