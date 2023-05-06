package com.accenture.f1app.data

import android.util.Log
import com.accenture.f1app.data.model.driver.Driver
import com.accenture.f1app.data.network.DriverService
import com.accenture.f1app.data.network.TeamService
import java.lang.reflect.Constructor

class TeamRepository {
    private val api = TeamService()

    suspend fun getConstructorsFromCurrentSeason(): List<com.accenture.f1app.data.model.team.Constructor> {
        val response = api.getConstructorsFromCurrentSeason()
        //DriverProvider.driverList = response
        Log.i("izn", response.get(0).name)
        return response
    }
}