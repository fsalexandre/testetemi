package com.bsc.testtemi.ui.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bsc.testtemi.R
import com.bsc.testtemi.data.model.ListSetor
import com.bsc.testtemi.data.model.ListSubSetor
import com.bsc.testtemi.ui.listener.SetorListListener
import com.bsc.testtemi.ui.listener.SubSetorListListener
import kotlinx.android.synthetic.main.listsetor_item.view.*
import kotlinx.android.synthetic.main.listsubsetor_item.view.*

class SubSetorListAdapter(private val subSetorList: List<ListSubSetor>, private val context: Context, private val listener: SubSetorListListener) :
    RecyclerView.Adapter<SubSetorListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.listsubsetor_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.txtSubSetor.text = subSetorList[position].desc_subsetor
        holder.itemView.setOnClickListener {
            listener.onSelectVolume(subSetorList[position], it)
        }
    }

    override fun getItemCount(): Int {
        return subSetorList.size

    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}