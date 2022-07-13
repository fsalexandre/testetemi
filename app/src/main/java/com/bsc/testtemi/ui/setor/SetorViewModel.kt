package com.bsc.testtemi.ui.setor

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bsc.testtemi.data.api.Repository
import com.bsc.testtemi.data.model.ListSetor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SetorViewModel (private val repository: Repository) : ViewModel() {

    val setorList = MutableLiveData<List<ListSetor>>()
    val errorMessage = MutableLiveData<String>()

    private val _setorListCompose = mutableListOf<List<ListSetor>>()
    val setorListCompose: MutableList<List<ListSetor>> = _setorListCompose

    private val _state = MutableStateFlow(emptyList<ListSetor>())
    val state: StateFlow<List<ListSetor>>
        get() = _state

    fun requestSetor() {
        val response = repository.requestSetor()
        response.enqueue(object : Callback<List<ListSetor>> {
            override fun onResponse(call: Call<List<ListSetor>>, response: Response<List<ListSetor>>) {
                setorList.postValue(response.body())
                _state.value = response.body()!!
            }
            override fun onFailure(call: Call<List<ListSetor>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}