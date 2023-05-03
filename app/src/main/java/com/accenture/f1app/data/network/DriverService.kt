package com.accenture.f1app.data.network

import com.accenture.f1app.core.Core
import com.accenture.f1app.data.model.driver.Driver
import com.accenture.f1app.data.model.driver.DriverResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class DriverService {
    private val retrofit = Core.getRetrofit()

    suspend fun getDriversFromCurrentSeason(): List<Driver> {
        return withContext(Dispatchers.IO) {
            val response: Response<DriverResponse> =
                retrofit.create(F1ApiClient::class.java).getDriversFromCurrentSeason()
            response.body()?.MRData?.DriverTable?.Drivers ?: emptyList()
        }
    }
}