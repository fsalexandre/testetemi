package com.bsc.testtemi.ui.listener

import android.view.View
import com.bsc.testtemi.data.model.ListProdutos

interface ProdutosListListener {
    fun onSelectVolume(produtoItem: ListProdutos, v: View)
}