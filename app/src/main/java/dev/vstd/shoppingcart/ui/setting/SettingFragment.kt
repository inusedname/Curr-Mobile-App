package dev.vstd.shoppingcart.ui.setting

import android.view.LayoutInflater
import android.view.ViewGroup
import dev.keego.shoppingcart.databinding.FragmentSettingBinding
import dev.vstd.shoppingcart.ui.base.BaseFragment

class SettingFragment : BaseFragment<FragmentSettingBinding>() {
    override fun onViewCreated(binding: FragmentSettingBinding) {

    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSettingBinding
        get() = FragmentSettingBinding::inflate
}