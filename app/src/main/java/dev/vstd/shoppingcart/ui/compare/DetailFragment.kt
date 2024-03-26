package dev.vstd.shoppingcart.ui.compare

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import dev.keego.shoppingcart.databinding.FragmentDetailTabBinding
import dev.vstd.shoppingcart.ui.base.BaseFragment

class DetailFragment : BaseFragment<FragmentDetailTabBinding>() {
    override fun onViewCreated(binding: FragmentDetailTabBinding) {
        Log.d("TAG", "onViewCreated: DetailFragment")
    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailTabBinding
        get() = FragmentDetailTabBinding::inflate

}