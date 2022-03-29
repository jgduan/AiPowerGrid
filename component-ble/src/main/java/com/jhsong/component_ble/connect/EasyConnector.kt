package com.jhsong.component_ble.connect

import android.bluetooth.*
import android.content.Context
import android.util.Log
import com.jhsong.component_ble.util.BleUtils

/**
 * ClassName EasyConnector
 * Describe TODO<>
 * Author zihao
 * Date 2022/3/17 22:25
 * Version v1.0
 */
class EasyConnector constructor(builder: Builder) {
    private var mBluetoothGattCallback: BluetoothGattCallback? = null

    init {
        mBluetoothGattCallback = builder.bluetoothGattCallback
    }

    companion object {
        val TAG = "EasyConnector"

        fun build(): Builder {
            return Builder()
        }
    }

    class Builder {
        var bluetoothGattCallback: BluetoothGattCallback? = null

        fun setBluetoothGattCallback(bluetoothGattCallback: BluetoothGattCallback): Builder {
            this@Builder.bluetoothGattCallback = bluetoothGattCallback
            return this
        }

        fun create(): EasyConnector = EasyConnector(this)
    }

    fun connectDevice(context: Context, address: String) {
        BleUtils.getBluetoothAdapter().getRemoteDevice(address)
            .connectGatt(context, false, mBluetoothGattCallback)
    }
}