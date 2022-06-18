package com.bsc.testtemi.data.model

import com.google.gson.annotations.SerializedName

data class ListProdutos(
    @SerializedName("desc_marca") val desc_marca : String,
    @SerializedName("desc_produto") val desc_produto : String,
    @SerializedName("desc_sku") val desc_sku : Int
)