package com.bsc.testtemi.ui.setor

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bsc.testtemi.data.api.Repository
import com.bsc.testtemi.data.model.ListSetor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SetorViewModel(private val repository: Repository) : ViewModel() {

    val setorList = MutableLiveData<List<ListSetor>>()
    val errorMessage = MutableLiveData<String>()

    fun requestSetor() {
        val response = repository.requestSetor()
        response.enqueue(object : Callback<List<ListSetor>> {
            override fun onResponse(call: Call<List<ListSetor>>, response: Response<List<ListSetor>>) {
                setorList.postValue(response.body())
            }
            override fun onFailure(call: Call<List<ListSetor>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}