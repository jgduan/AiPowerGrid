package com.jhsong.aipowergrid

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jhsong.aipowergrid.databinding.FragmentMineBinding
import com.jhsong.base.BaseFragment

/**
 * ClassName MineFragment
 * Describe TODO<个人中心页面>
 * Author zihao
 * Date 2022/3/29 23:36
 * Version v1.0
 */
class MineFragment : BaseFragment<FragmentMineBinding>() {
    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup?
    ): FragmentMineBinding {
        return FragmentMineBinding.inflate(layoutInflater, parent, false)
    }

    override fun initView() {

    }

    override fun initData() {

    }
}