package com.bsc.testtemi.data.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import com.bsc.testtemi.data.model.ListProdutos
import com.bsc.testtemi.data.model.ListSetor
import com.bsc.testtemi.data.model.ListSubSetor
import com.bsc.testtemi.settings.settingsURL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.dsl.module
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

class Repository(private val api: RestApi) {

    fun requestSetor() = api.requestSetor()
    fun requestSubSetor(strSetor: String) = api.requestSubSetor(setor=strSetor)
    fun requestProdutos(strSubSetor: String) = api.requestProdutos(subsetor = strSubSetor)
    fun requestProdutosbyQuery(strQuery: String) = api.requestProdutosByQuery(query = strQuery)

}
