package com.bsc.protonbusmodscom.data.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bsc.protonbusmodscom.data.model.ModsData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Repository {
    val api: RestApi = ServiceBuilder.getRetrofitInstance()

    private val _modsList = MutableLiveData<List<ModsData>>()
    private val modsList: LiveData<List<ModsData>> = _modsList

    fun requestMods(){
        api.requestMods().enqueue(
            object : Callback<List<ModsData>> {
                override fun onFailure(call: Call<List<ModsData>>, t: Throwable) {
                    Log.d("okhttp","Não foi possível buscar os dados")
                }

                override fun onResponse(
                    call: Call<List<ModsData>>,
                    response: Response<List<ModsData>>
                ) {
                    val responseMods = response.body()
                    if (response.code() == 200) {
                        Log.d("okhttp","deu certo")
                        _modsList.value = responseMods!!
                    } else {
                        Log.d("okhttp","deu errado")
                    }
                }
            }
        )
    }

    fun returnModsList(): LiveData<List<ModsData>> {
        return modsList
    }
}