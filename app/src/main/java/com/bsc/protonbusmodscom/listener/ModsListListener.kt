package com.bsc.protonbusmodscom.listener

import android.view.View
import com.bsc.protonbusmodscom.data.model.ModsData

interface ModsListListener {
    fun onSelectVolume(modItem: ModsData, v: View)
}