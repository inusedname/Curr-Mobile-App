package dev.vstd.shoppingcart.ui.payment.checkout

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vstd.shoppingcart.utils.VietnamAdministrationProvider
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SelectAddressVimel @Inject constructor(application: Application) :
    AndroidViewModel(application) {
    private val vnAdministrationProvider = VietnamAdministrationProvider()
    val cities = MutableStateFlow(listOf<VietnamAdministrationProvider.City>())
    val districts = MutableStateFlow(listOf<VietnamAdministrationProvider.District>())
    val currentCity = MutableStateFlow<VietnamAdministrationProvider.City?>(null)
    val currentDistrict = MutableStateFlow<VietnamAdministrationProvider.District?>(null)

    init {
        cities.value = vnAdministrationProvider.getCities(application)
    }

    fun setCurrentCity(city: VietnamAdministrationProvider.City) {
        currentCity.value = city
        fetchDistricts(city.id)
    }

    fun setDistrict(district: VietnamAdministrationProvider.District) {
        currentDistrict.value = district
    }

    fun fetchDistricts(cityId: Int) {
        districts.value = vnAdministrationProvider.getDistricts(getApplication(), cityId)
    }
}