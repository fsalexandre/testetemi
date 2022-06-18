package com.bsc.testtemi.ui.setor

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bsc.testtemi.data.api.Repository
import com.bsc.testtemi.data.model.ListSetor

class SetorViewModel : ViewModel() {
    private var repository: Repository = Repository()

    fun requestSetor() {
        repository.requestSetor()
    }

    fun returnSetor(): LiveData<List<ListSetor>> {
        return repository.returnSetorList()
    }
}