package com.bsc.protonbusmodscom.data.model

import com.google.gson.annotations.SerializedName

data class ModsData(

    @SerializedName("id_mod_bus") val id_mod_bus : Int,
    @SerializedName("fr_thumb_title") val fr_thumb_title : String,
    @SerializedName("fr_thumb_desc") val fr_thumb_desc : String,
    @SerializedName("fr_thumb_url") val fr_thumb_url : String,
    @SerializedName("dt_mod_manufacturer") val dt_mod_manufacturer : String,
    @SerializedName("dt_mod_year_prod") val dt_mod_year_prod : String,
    @SerializedName("dt_mod_assembly") val dt_mod_assembly : String,
    @SerializedName("dt_mod_style") val dt_mod_style : String,
    @SerializedName("dt_mod_url") val dt_mod_url : String,
    @SerializedName("dt_credit_information") val dt_credit_information : String,
    @SerializedName("vl_hits") val vl_hits : Int
)
