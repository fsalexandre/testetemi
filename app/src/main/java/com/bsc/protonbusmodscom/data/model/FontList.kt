package com.bsc.protonbusmodscom.data.model

import com.google.gson.annotations.SerializedName

data class FontList(
    @SerializedName("id_font") val id_font : Int,
    @SerializedName("desc_font") val desc_font : String
)
