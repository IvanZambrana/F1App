package com.accenture.f1app.data.network

import com.accenture.f1app.data.model.circuit.CircuitResponse
import com.accenture.f1app.data.model.driver.Driver
import com.accenture.f1app.data.model.driver.DriverResponse
import com.accenture.f1app.data.model.team.TeamResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface F1ApiClient {
    @GET("api/f1/current/drivers.json")
    suspend fun getDriversFromCurrentSeason(@Query("q") query: String? = null): Response<DriverResponse>
    //suspend fun getDriversFromCurrentSeason(): Response<DriverResponse>

    @GET("api/f1/current/constructors.json")
    suspend fun getConstructorsFromCurrentSeason(@Query("q") query: String? = null): Response<TeamResponse>
    //suspend fun getConstructorsFromCurrentSeason(): Response<TeamResponse>

    @GET("api/f1/current/circuits.json")
    suspend fun getCircuitsFromCurrentSeason(@Query("q") query: String? = null): Response<CircuitResponse>
    //suspend fun getCircuitsFromCurrentSeason(): Response<CircuitResponse>
    @GET("api/f1/current/drivers/{id}.json")
    suspend fun getDriverById(@Path("id") driverId: String): Driver

}