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

    private val _setorListState = MutableStateFlow(emptyList<ListSetor>())
    val setorListState: StateFlow<List<ListSetor>>
        get() = _setorListState

    val errorMessage = MutableLiveData<String>()

    fun requestSetor() {
        val response = repository.requestSetor()
        response.enqueue(object : Callback<List<ListSetor>> {
            override fun onResponse(call: Call<List<ListSetor>>, response: Response<List<ListSetor>>) {
                _setorListState.value = response.body()!!
            }
            override fun onFailure(call: Call<List<ListSetor>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}