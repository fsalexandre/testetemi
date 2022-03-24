package com.bsc.protonbusmodscom.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bsc.protonbusmodscom.R
import com.bsc.protonbusmodscom.data.model.DisplayLayersData
import com.bsc.protonbusmodscom.listener.objImageListener
import kotlinx.android.synthetic.main.listobjimage_item.view.*

class ImgObjectsAdapter(val objImageList: List<DisplayLayersData>, val context: Context, private val listener: objImageListener) :
    RecyclerView.Adapter<ImgObjectsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.listobjimage_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.fr_thumb_img.setImageBitmap(objImageList[position].bitmap_source)
        holder.itemView.txtTextContent.text = "Text: "+objImageList[position].bitmap_text+" - Size: "+objImageList[position].bitmap_textSize.toString()
        holder.itemView.setOnClickListener {
            listener.onSelectObj(objImageList[position], it, position)
        }
    }

    override fun getItemCount(): Int {
        return objImageList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}