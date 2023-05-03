package com.accenture.f1app.data.network

import com.accenture.f1app.data.model.circuit.CircuitResponse
import com.accenture.f1app.data.model.driver.DriverResponse
import com.accenture.f1app.data.model.team.TeamResponse
import retrofit2.Response
import retrofit2.http.GET

interface F1ApiClient {
    @GET("api/f1/current/drivers.json")
    suspend fun getDriversFromCurrentSeason(): Response<DriverResponse>

    @GET("api/f1/current/constructors.json")
    suspend fun getConstructorsFromCurrentSeason(): Response<TeamResponse>

    @GET("api/f1/current/circuits.json")
    suspend fun getCircuitsFromCurrentSeason(): Response<CircuitResponse>


}