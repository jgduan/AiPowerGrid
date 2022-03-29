package com.jhsong.base

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * ClassName BaseActivity
 * Describe TODO<>
 * Author zihao
 * Date 2022/3/15 22:13
 * Version v1.0
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = getViewBinding(layoutInflater)
        setContentView(binding.root)

        initData()
        initView()
    }

    abstract fun getViewBinding(layoutInflater: LayoutInflater): VB
    abstract fun initData()
    abstract fun initView()

    fun showToast(contentStr: String) {
        Toast.makeText(this, contentStr, Toast.LENGTH_LONG).show()
    }
}