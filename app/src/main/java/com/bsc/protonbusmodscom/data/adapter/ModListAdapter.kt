package com.bsc.protonbusmodscom.data.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bsc.protonbusmodscom.R
import com.bsc.protonbusmodscom.data.model.ModsData
import com.bsc.protonbusmodscom.listener.ModsListListener
import com.bsc.protonbusmodscom.ui.main.MainFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.listmod_item.view.*

class ModListAdapter(private val modList: List<ModsData>, private val context: Context, private val listener: ModsListListener) :
    RecyclerView.Adapter<ModListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.listmod_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        try {
            val urlThumbnail = "https://www.protonbusmods.com/img/search-result/"+modList[position].id_mod_bus+".webp"
            Picasso.get().load(urlThumbnail).into(holder.itemView.fr_thumb_img)
        }catch (e: Exception){

        }
        holder.itemView.fr_thumb_title.text = modList[position].fr_thumb_title
        holder.itemView.fr_thumb_desc.text = modList[position].fr_thumb_desc
        holder.itemView.dt_mod_style.text = modList[position].dt_mod_style
        holder.itemView.dt_mod_assembly.text = modList[position].dt_mod_assembly
        holder.itemView.setOnClickListener {
            listener.onSelectVolume(modList[position], it)
        }
    }

    override fun getItemCount(): Int {
        return modList.size

    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}