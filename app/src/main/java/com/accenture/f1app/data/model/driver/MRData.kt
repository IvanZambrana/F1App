package com.accenture.f1app.data.model.driver

data class MRData(
    val DriverTable: DriverTable,
    val limit: String,
    val offset: String,
    val series: String,
    val total: String,
    val url: String,
    val xmlns: String
)