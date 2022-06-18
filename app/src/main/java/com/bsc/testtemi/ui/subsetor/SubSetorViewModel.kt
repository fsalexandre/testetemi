package com.bsc.testtemi.ui.subsetor

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bsc.testtemi.data.api.Repository
import com.bsc.testtemi.data.model.ListSubSetor

class SubSetorViewModel : ViewModel() {
    private var repository: Repository = Repository()

    fun requestSubSetor(strSetor: String) {
        repository.requestSubSetor(strSetor)
    }

    fun returnSubSetor(): LiveData<List<ListSubSetor>> {
        return repository.returnSubSetorList()
    }
}