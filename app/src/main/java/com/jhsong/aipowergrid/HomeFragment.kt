package com.jhsong.aipowergrid

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jhsong.aipowergrid.databinding.FragmentHomeBinding
import com.jhsong.base.BaseFragment

/**
 * ClassName HomeFragment
 * Describe TODO<>
 * Author zihao
 * Date 2022/3/29 23:34
 * Version v1.0
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater, parent, false)
    }

    override fun initView() {

    }

    override fun initData() {

    }
}