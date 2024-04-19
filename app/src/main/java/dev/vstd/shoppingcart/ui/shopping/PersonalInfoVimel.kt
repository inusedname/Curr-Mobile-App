package dev.vstd.shoppingcart.ui.shopping

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vstd.shoppingcart.dataMock.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class PersonalInfoVimel @Inject constructor(val userRepository: UserRepository): ViewModel() {
    fun foo() {
        println("foo")
    }
}