package com.bsc.testtemi.data.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bsc.testtemi.data.model.ListProdutos
import com.bsc.testtemi.data.model.ListSetor
import com.bsc.testtemi.data.model.ListSubSetor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Repository {
    val api: RestApi = ServiceBuilder.getRetrofitInstance()

    private val _setorList = MutableLiveData<List<ListSetor>>()
    private val setorList: LiveData<List<ListSetor>> = _setorList

    fun requestSetor(){
        api.requestSetor().enqueue(
            object : Callback<List<ListSetor>> {
                override fun onFailure(call: Call<List<ListSetor>>, t: Throwable) {
                    Log.d("okhttp","Não foi possível buscar os dados")
                }

                override fun onResponse(
                    call: Call<List<ListSetor>>,
                    response: Response<List<ListSetor>>
                ) {
                    val responseMods = response.body()
                    if (response.code() == 200) {
                        Log.d("okhttp","deu certo")
                        _setorList.value = responseMods!!
                    } else {
                        Log.d("okhttp","deu errado")
                    }
                }
            }
        )
    }

    fun returnSetorList(): LiveData<List<ListSetor>> {
        return setorList
    }


    private val _subSetorList = MutableLiveData<List<ListSubSetor>>()
    private val subSetorList: LiveData<List<ListSubSetor>> = _subSetorList

    fun requestSubSetor(strSetor: String){
        api.requestSubSetor(
            setor=strSetor
        ).enqueue(
            object : Callback<List<ListSubSetor>> {
                override fun onFailure(call: Call<List<ListSubSetor>>, t: Throwable) {
                    Log.d("okhttp","Não foi possível buscar os dados")
                }

                override fun onResponse(
                    call: Call<List<ListSubSetor>>,
                    response: Response<List<ListSubSetor>>
                ) {
                    val responseMods = response.body()
                    if (response.code() == 200) {
                        Log.d("okhttp","deu certo")
                        _subSetorList.value = responseMods!!
                    } else {
                        Log.d("okhttp","deu errado")
                    }
                }
            }
        )
    }

    fun returnSubSetorList(): LiveData<List<ListSubSetor>> {
        return subSetorList
    }


    private val _produtoList = MutableLiveData<List<ListProdutos>>()
    private val produtoList: LiveData<List<ListProdutos>> = _produtoList

    fun requestProdutos(strSubSetor: String){
        api.requestProdutos(
            subsetor = strSubSetor
        ).enqueue(
            object : Callback<List<ListProdutos>> {
                override fun onFailure(call: Call<List<ListProdutos>>, t: Throwable) {
                    Log.d("okhttp","Não foi possível buscar os dados")
                }

                override fun onResponse(
                    call: Call<List<ListProdutos>>,
                    response: Response<List<ListProdutos>>
                ) {
                    val responseMods = response.body()
                    if (response.code() == 200) {
                        Log.d("okhttp","deu certo")
                        _produtoList.value = responseMods!!
                    } else {
                        Log.d("okhttp","deu errado")
                    }
                }
            }
        )
    }

    fun returnProdutosList(): LiveData<List<ListProdutos>> {
        return produtoList
    }

    private val _produtoListbyQuery = MutableLiveData<List<ListProdutos>>()
    private val produtoListbyQuery: LiveData<List<ListProdutos>> = _produtoListbyQuery

    fun requestProdutosByQuery(strQuery: String){
        api.requestProdutosByQuery(
            query = strQuery
        ).enqueue(
            object : Callback<List<ListProdutos>> {
                override fun onFailure(call: Call<List<ListProdutos>>, t: Throwable) {
                    Log.d("okhttp","Não foi possível buscar os dados")
                }

                override fun onResponse(
                    call: Call<List<ListProdutos>>,
                    response: Response<List<ListProdutos>>
                ) {
                    val responseMods = response.body()
                    if (response.code() == 200) {
                        Log.d("okhttp","deu certo")
                        _produtoListbyQuery.value = responseMods!!
                    } else {
                        Log.d("okhttp","deu errado")
                    }
                }
            }
        )
    }

    fun returnProdutosListByQuery(): LiveData<List<ListProdutos>> {
        return produtoListbyQuery
    }

}