package com.hms.currencyexchange.data.vos

import com.google.gson.annotations.SerializedName

data class ExchangeRateVO(
    @SerializedName("info") val info: String,
    @SerializedName("description") val description: String,
    @SerializedName("timestamp") val timestamp: String,
    @SerializedName("rates") val rates: Map<String, String>)