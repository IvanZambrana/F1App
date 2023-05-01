package com.accenture.f1app.data.network

import com.accenture.f1app.core.Core
import com.accenture.f1app.data.model.driver.Driver
import com.accenture.f1app.data.model.team.Constructor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TeamService {
    private val retrofit = Core.getRetrofit()

    suspend fun getConstructorsFromCurrentSeason(): List<Constructor> {
        return withContext(Dispatchers.IO) {
            val response =
                retrofit.create(F1ApiClient::class.java).getConstructorsFromCurrentSeason()
            response.body()?.MRData?.ConstructorTable?.Constructors ?: emptyList()
        }
    }
}