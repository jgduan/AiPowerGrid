package com.jhsong.component_ble

import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothProfile
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.util.Log
import com.jhsong.component_ble.connect.EasyConnector
import com.jhsong.component_ble.scan.EasyScanner
import com.jhsong.component_ble.scan.IScanLifecycleObserver

/**
 * ClassName EasyBle
 * Describe TODO<EasyBle>
 * Author zihao
 * Date 2022/3/13 17:24
 * Version v1.0
 */
class EasyBle private constructor(builder: Builder) : IEasyBle {
    private var mBluetoothGatt: BluetoothGatt? = null

    companion object {
        fun build(): Builder {// 静态实例化建造者Builder类
            return Builder()
        }
    }

    override fun connect(context: Context, deviceAddress: String) {
        EasyConnector.build().setBluetoothGattCallback(object : BluetoothGattCallback() {
            override fun onConnectionStateChange(
                gatt: BluetoothGatt?,
                status: Int,
                newState: Int
            ) {
                Log.e(EasyConnector.TAG, "status:$status,newState:$newState")
                if (status == BluetoothGatt.GATT_SUCCESS) {
                    when (newState) {
                        BluetoothProfile.STATE_CONNECTING -> {
                            // 正在连接中
                        }
                        BluetoothProfile.STATE_CONNECTED -> {
                            // 连接成功，开始发现gatt中的服务
                            mBluetoothGatt = gatt
                            gatt?.discoverServices()
                        }
                        BluetoothProfile.STATE_DISCONNECTING -> {

                        }
                        BluetoothProfile.STATE_DISCONNECTED -> {
                            Log.e(EasyConnector.TAG, "断开连接")
                        }
                    }
                } else {
                    Log.e(EasyConnector.TAG, "断开连接")
                }
            }

            override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
                // 发现服务回调
                gatt?.run {
                    val serviceList = services
                    for (service in serviceList) {
                        Log.e(
                            EasyConnector.TAG,
                            "-------------------------serviceUUID:${service.uuid}"
                        )
                        for (characteristics in service.characteristics) {
                            characteristics.descriptors
                            Log.e(EasyConnector.TAG, "包含特征：${characteristics.uuid}")
                        }
                    }
                }
            }

            override fun onCharacteristicWrite(
                gatt: BluetoothGatt?,
                characteristic: BluetoothGattCharacteristic?,
                status: Int
            ) {
                when (status) {
                    BluetoothGatt.GATT_SUCCESS -> {
                        // 写入成功
                    }
                    BluetoothGatt.GATT_FAILURE -> {
                        // 写入失败
                    }
                    BluetoothGatt.GATT_WRITE_NOT_PERMITTED -> {
                        // 没有权限
                    }
                }
            }

            override fun onCharacteristicRead(
                gatt: BluetoothGatt?,
                characteristic: BluetoothGattCharacteristic?,
                status: Int
            ) {
            }
        }).create().connectDevice(context, deviceAddress)
    }

    override fun disconnect(deviceAddress: String) {
        mBluetoothGatt?.disconnect()
    }

    override fun scanDevice(scanCallback: ScanCallback) {
        // 开始前需要检查是否开启了蓝牙权限、是否打开了位置信息
        // 这里开始进行BLE设备扫描
        EasyScanner.build()
            .setScanCallback(scanCallback)
            .setScanTime(5000)
            .create()
            .startScan()
    }

    // 建造者类
    class Builder {
        // 创建EasyBle类的实例化
        fun create(): EasyBle = EasyBle(this)
    }
}