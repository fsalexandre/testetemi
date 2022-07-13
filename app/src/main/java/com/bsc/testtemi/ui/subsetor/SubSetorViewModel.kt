package com.bsc.testtemi.ui.subsetor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bsc.testtemi.data.api.Repository
import com.bsc.testtemi.data.model.ListSetor
import com.bsc.testtemi.data.model.ListSubSetor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubSetorViewModel(private val repository: Repository) : ViewModel() {

    private val _subSetorListState = MutableStateFlow(emptyList<ListSubSetor>())
    val subSetorListState: StateFlow<List<ListSubSetor>>
        get() = _subSetorListState

    val errorMessage = MutableLiveData<String>()

    fun requestSubSetor(strSetor: String) {
        val response = repository.requestSubSetor(strSetor)
        response.enqueue(object : Callback<List<ListSubSetor>> {
            override fun onResponse(call: Call<List<ListSubSetor>>, response: Response<List<ListSubSetor>>) {
                _subSetorListState.value = response.body()!!
            }
            override fun onFailure(call: Call<List<ListSubSetor>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

}