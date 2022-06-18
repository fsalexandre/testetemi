package com.bsc.testtemi.ui.produtos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bsc.testtemi.data.api.Repository
import com.bsc.testtemi.data.model.ListProdutos
import com.bsc.testtemi.data.model.ListSubSetor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProdutosViewModel(private val repository: Repository) : ViewModel() {


    val produtoList = MutableLiveData<List<ListProdutos>>()
    val produtoByQueryList = MutableLiveData<List<ListProdutos>>()
    val errorMessage = MutableLiveData<String>()

    fun requestProdutos(strSetor: String) {
        val response = repository.requestProdutos(strSetor)
        response.enqueue(object : Callback<List<ListProdutos>> {
            override fun onResponse(call: Call<List<ListProdutos>>, response: Response<List<ListProdutos>>) {
                produtoList.postValue(response.body())
            }
            override fun onFailure(call: Call<List<ListProdutos>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun requestProdutosbyQuery(strSetor: String) {
        val response = repository.requestProdutosbyQuery(strSetor)
        response.enqueue(object : Callback<List<ListProdutos>> {
            override fun onResponse(call: Call<List<ListProdutos>>, response: Response<List<ListProdutos>>) {
                produtoByQueryList.postValue(response.body())
            }
            override fun onFailure(call: Call<List<ListProdutos>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }




}