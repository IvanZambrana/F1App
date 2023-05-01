package com.accenture.f1app.data.network

import com.accenture.f1app.core.Core
import com.accenture.f1app.data.model.circuit.Circuit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CircuitService {
    private val retrofit = Core.getRetrofit()

    suspend fun getCircuitsFromCurrentSeason(): List<Circuit> {
        return withContext(Dispatchers.IO) {
            val response =
                retrofit.create(F1ApiClient::class.java).getCircuitsFromCurrentSeason()
            response.body()?.MRData?.CircuitTable?.Circuits ?: emptyList()
        }
    }
}