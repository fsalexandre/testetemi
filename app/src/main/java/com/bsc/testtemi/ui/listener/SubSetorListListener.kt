package com.bsc.testtemi.ui.listener

import android.view.View
import com.bsc.testtemi.data.model.ListSetor
import com.bsc.testtemi.data.model.ListSubSetor

interface SubSetorListListener {
    fun onSelectVolume(subSetorItem: ListSubSetor, v: View)
}