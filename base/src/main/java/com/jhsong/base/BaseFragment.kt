package com.jhsong.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * ClassName BaseFragment
 * Describe TODO<>
 * Author zihao
 * Date 2022/3/15 22:27
 * Version v1.0
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    var binding: ViewBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding(layoutInflater, container)
        initData()
        initView()
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    abstract fun getViewBinding(layoutInflater: LayoutInflater, parent: ViewGroup?): VB
    abstract fun initView()
    abstract fun initData()
}