package dev.vstd.shoppingcart.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dev.keego.shoppingcart.R
import dev.keego.shoppingcart.databinding.FragmentNavBarBinding
import dev.vstd.shoppingcart.ui.base.BaseFragment

class NavBarFragment : BaseFragment<FragmentNavBarBinding>() {
    override fun onViewCreated(binding: FragmentNavBarBinding) {
        setBotNavClicks(binding)
    }

    private fun setBotNavClicks(binding: FragmentNavBarBinding) {
        val navController =
            (childFragmentManager.findFragmentById(R.id.container_nav_bar) as NavHostFragment).navController
        binding.botNav.setupWithNavController(navController)
    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentNavBarBinding
        get() = FragmentNavBarBinding::inflate

}