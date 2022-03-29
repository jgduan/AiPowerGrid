package com.jhsong.component_ble.util

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent

/**
 * ClassName BleUtils
 * Describe TODO<>
 * Author zihao
 * Date 2022/3/14 0:20
 * Version v1.0
 */
object BleUtils {

    const val REQUEST_CODE_ENABLE_BLE = 1

    fun openBle(activity: Activity) {
        if (!getBluetoothAdapter().isEnabled) {
            val enableIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            activity.startActivityForResult(enableIntent, REQUEST_CODE_ENABLE_BLE)
        }
    }

    fun isBleEnable(): Boolean {
        return getBluetoothAdapter().isEnabled
    }

    fun getBluetoothAdapter(): BluetoothAdapter {
        return BluetoothAdapter.getDefaultAdapter()
    }
}