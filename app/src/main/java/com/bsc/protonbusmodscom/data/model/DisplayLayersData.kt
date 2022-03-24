package com.bsc.protonbusmodscom.data.model

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.Typeface
import com.google.gson.annotations.SerializedName

data class DisplayLayersData(
    @SerializedName("bitmap_source") val bitmap_source : Bitmap,
    @SerializedName("bitmap_typeface") val bitmap_typeface : Typeface,
    @SerializedName("bitmap_textSize") val bitmap_textSize : Float,
    @SerializedName("bitmap_text") val bitmap_text : String,
    @SerializedName("bitmap_color") val bitmap_color : Int,
    @SerializedName("bitmap_style") val bitmap_style : Paint.Style,
    @SerializedName("bitmap_x") val bitmap_x : Float,
    @SerializedName("bitmap_y") val bitmap_y : Float,
)

