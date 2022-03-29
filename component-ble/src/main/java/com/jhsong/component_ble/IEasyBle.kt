package com.jhsong.component_ble

import android.bluetooth.le.ScanCallback
import android.content.Context

/**
 * ClassName IEasyBle
 * Describe TODO<EasyBle操作接口>
 * Author zihao
 * Date 2022/3/13 17:25
 * Version v1.0
 */
interface IEasyBle {
    fun connect(context: Context, deviceAddress: String)

    fun disconnect(deviceAddress: String)

    fun scanDevice(scanCallback: ScanCallback)
}