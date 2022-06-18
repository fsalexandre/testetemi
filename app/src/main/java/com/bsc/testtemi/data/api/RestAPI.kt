package com.bsc.testtemi.data.api

import com.bsc.testtemi.data.model.ListProdutos
import com.bsc.testtemi.data.model.ListSetor
import com.bsc.testtemi.data.model.ListSubSetor
import com.bsc.testtemi.settings.settingsURL
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RestApi {
    @Headers("Content-Type: text/json")
    @GET(settingsURL.api_listsetor_endpoint)
    fun requestSetor(): Call<List<ListSetor>>

    @Headers("Content-Type: text/json")
    @GET(settingsURL.api_listsubsetor_endpoint)
    fun requestSubSetor(@Query("setor") setor: String): Call<List<ListSubSetor>>


    @Headers("Content-Type: text/json")
    @GET(settingsURL.api_listprodutos_endpoint)
    fun requestProdutos(@Query("subsetor") subsetor: String): Call<List<ListProdutos>>

    @Headers("Content-Type: text/json")
    @GET(settingsURL.api_listprodutosbyquery_endpoint)
    fun requestProdutosByQuery(@Query("query") query: String): Call<List<ListProdutos>>
}