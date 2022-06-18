package com.bsc.testtemi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bsc.testtemi.R
import com.bsc.testtemi.data.model.ListProdutos
import com.bsc.testtemi.ui.listener.ProdutosListListener
import kotlinx.android.synthetic.main.listprodutos_item.view.*

class ProdutosListAdapter(private val produtosList: List<ListProdutos>, private val context: Context, private val listener: ProdutosListListener) :
    RecyclerView.Adapter<ProdutosListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.listprodutos_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.txtMarca.text = produtosList[position].desc_marca
        holder.itemView.txtProduto.text = produtosList[position].desc_produto
        holder.itemView.txtSKU.text = produtosList[position].desc_sku
        holder.itemView.setOnClickListener {
            listener.onSelectVolume(produtosList[position], it)
        }
    }

    override fun getItemCount(): Int {
        return produtosList.size

    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}