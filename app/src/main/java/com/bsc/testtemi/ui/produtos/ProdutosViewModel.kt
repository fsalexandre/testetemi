package com.bsc.testtemi.ui.produtos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bsc.testtemi.data.api.Repository
import com.bsc.testtemi.data.model.ListProdutos


class ProdutosViewModel: ViewModel() {
    private var repository: Repository = Repository()

    fun requestProdutos(strSubSetor: String) {
        repository.requestProdutos(strSubSetor)
    }

    fun returnProdutos(): LiveData<List<ListProdutos>> {
        return repository.returnProdutosList()
    }

    fun requestProdutosbyQuery(strQuery: String) {
        repository.requestProdutosByQuery(strQuery)
    }

    fun returnProdutosbyQuery(): LiveData<List<ListProdutos>> {
        return repository.returnProdutosListByQuery()
    }



}