package com.bsc.testtemi.ui.listener

import android.view.View
import com.bsc.testtemi.data.model.ListSetor

interface SetorListListener {
    fun onSelectVolume(setorItem: ListSetor, v: View)
}