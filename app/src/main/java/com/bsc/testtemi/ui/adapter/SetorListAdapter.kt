package com.bsc.testtemi.ui.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bsc.testtemi.R
import com.bsc.testtemi.data.model.ListSetor
import com.bsc.testtemi.ui.listener.SetorListListener
import kotlinx.android.synthetic.main.listsetor_item.view.*

class SetorListAdapter(private val setorList: List<ListSetor>, private val context: Context, private val listener: SetorListListener) :
    RecyclerView.Adapter<SetorListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.listsetor_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.txtSetor.text = setorList[position].descSetor
        holder.itemView.setOnClickListener {
            listener.onSelectVolume(setorList[position], it)
        }
    }

    override fun getItemCount(): Int {
        return setorList.size

    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}