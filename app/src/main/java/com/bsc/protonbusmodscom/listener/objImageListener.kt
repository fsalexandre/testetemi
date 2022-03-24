package com.bsc.protonbusmodscom.listener

import android.view.View
import com.bsc.protonbusmodscom.data.model.DisplayLayersData

interface objImageListener {
    fun onSelectObj(objItem: DisplayLayersData, v: View, pos: Int)
}
