package com.accenture.f1app.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.accenture.f1app.data.model.driver.Driver

class DriverViewModel(val drivers: MutableList<Driver>) : ViewModel() {
}

class DriverViewModelFactory(private val drivers: MutableList<Driver>) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DriverViewModel(drivers) as T
    }
}