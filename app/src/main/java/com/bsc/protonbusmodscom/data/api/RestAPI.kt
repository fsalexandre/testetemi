package com.bsc.protonbusmodscom.data.api

import com.bsc.protonbusmodscom.data.model.ModsData
import com.bsc.protonbusmodscom.settings.settingsURL
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface RestApi {
    @Headers("Content-Type: text/json")
    @GET(settingsURL.api_volumes_endpoint)
    fun requestMods(): Call<List<ModsData>>
}