package com.jhsong.component_ble.scan

import android.bluetooth.le.ScanCallback
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LifecycleOwner
import com.jhsong.component_ble.util.BleUtils
import java.lang.Exception

/**
 * ClassName EasyScanner
 * Describe TODO<Ble扫描类>
 * Author zihao
 * Date 2022/3/13 23:08
 * Version v1.0
 */
class EasyScanner private constructor(builder: Builder) : IScanLifecycleObserver {

    private var mScanCallback: ScanCallback? = null
    var mScanTime = DEFAULT_SCAN_TIME

    init {
        mScanCallback = builder.scanCallback
        mScanTime = builder.scanTime
    }

    companion object {
        const val DEFAULT_SCAN_TIME = 3000L

        fun build(): Builder {
            return Builder()
        }
    }

    class Builder {
        var scanCallback: ScanCallback? = null
        var scanTime = 3000L

        fun setScanTime(scanTime: Long): Builder {
            this@Builder.scanTime = scanTime
            return this
        }

        fun setScanCallback(scanCallback: ScanCallback): Builder {
            this@Builder.scanCallback = scanCallback
            return this
        }

        fun create(): EasyScanner = EasyScanner(this)
    }

    private var mHandler: Handler? = null
    fun startScan() {
        mScanCallback?.run {
            BleUtils.getBluetoothAdapter().bluetoothLeScanner?.startScan(this)
        }


        try {
            if (mHandler == null) {
                mHandler = Handler(Looper.myLooper()!!)
            }
            mHandler?.postDelayed({
                stopScan()
            }, mScanTime)
        } catch (e: Exception) {

        }
    }

    private fun stopScan() {
        mHandler?.removeCallbacksAndMessages(null)
        mScanCallback?.run {
            BleUtils.getBluetoothAdapter().bluetoothLeScanner?.stopScan(
                this
            )
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        if (BleUtils.isBleEnable()) {
            stopScan()
        }
    }
}