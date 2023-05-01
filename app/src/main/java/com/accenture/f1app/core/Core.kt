package com.accenture.f1app.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Core {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://ergast.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}