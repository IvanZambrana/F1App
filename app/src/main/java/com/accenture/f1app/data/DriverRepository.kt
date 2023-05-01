package com.accenture.f1app.data

import android.util.Log
import com.accenture.f1app.data.model.driver.Driver
import com.accenture.f1app.data.network.DriverService

class DriverRepository {
    private val api = DriverService()

    suspend fun getDriversFromCurrentSeason(): List<Driver> {
        val response = api.getDriversFromCurrentSeason()
        //DriverProvider.driverList = response
        Log.i("izn", response.get(0).familyName)
        return response
    }
}