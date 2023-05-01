package com.accenture.f1app.data

import android.util.Log
import com.accenture.f1app.data.model.circuit.Circuit
import com.accenture.f1app.data.model.driver.Driver
import com.accenture.f1app.data.network.CircuitService
import com.accenture.f1app.data.network.DriverService

class CircuitRepository {
    private val api = CircuitService()

    suspend fun getCircuitsFromCurrentSeason(): List<Circuit> {
        val response = api.getCircuitsFromCurrentSeason()
        //DriverProvider.driverList = response
        Log.i("izn", response.get(0).circuitName)
        return response
    }
}