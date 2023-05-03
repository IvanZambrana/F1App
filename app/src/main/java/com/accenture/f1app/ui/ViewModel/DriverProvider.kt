package com.accenture.f1app.ui.ViewModel

import com.accenture.f1app.data.model.driver.Driver

class DriverProvider {
    companion object {
        var driverList: List<Driver> = listOf()
    }
}