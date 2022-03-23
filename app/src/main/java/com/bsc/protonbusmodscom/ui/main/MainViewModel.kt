package com.bsc.protonbusmodscom.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bsc.protonbusmodscom.data.api.Repository
import com.bsc.protonbusmodscom.data.model.ModsData

class MainViewModel : ViewModel() {
    private var repository: Repository = Repository()

    fun requestMods() {
        repository.requestMods()
    }

    fun returnMods(): LiveData<List<ModsData>> {
        return repository.returnModsList()
    }
}