package com.bsc.protonbusmodscom.data.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.core.content.res.ResourcesCompat
import com.bsc.protonbusmodscom.R
import com.bsc.protonbusmodscom.data.model.FontList

class FontListAdapter(context: Context,
                             @LayoutRes private val layoutResource: Int,
                             @IdRes private val textViewResourceId: Int = 0,
                             private val values: MutableList<FontList>) : ArrayAdapter<FontList>(context, layoutResource, values) {

    override fun getItem(position: Int): FontList = values[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = createViewFromResource(convertView, parent, layoutResource)
        return bindData(getItem(position), view)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = createViewFromResource(convertView, parent, android.R.layout.simple_spinner_dropdown_item)
        return bindData(getItem(position), view)
    }

    private fun setupFont(view: TextView, descFont: String? = null) {

        var valuePass: String = view.text.toString()
        if(descFont!=null){
            valuePass=descFont
        }

        when(valuePass){
            "Bat Bus Readout" -> view.typeface=ResourcesCompat.getFont(context, R.font.batbus)
            "BusMatrix Condensed" -> view.typeface=ResourcesCompat.getFont(context, R.font.busmatrix)
            "MBTA Bus Route Display! 216" -> view.typeface=ResourcesCompat.getFont(context, R.font.mtba216)
            "MBTA Bus Route Display" -> view.typeface=ResourcesCompat.getFont(context, R.font.mtbadisplay)
            "Led 8x6" -> view.typeface=ResourcesCompat.getFont(context, R.font.led8x6)
            "Mobitec 13x9" -> view.typeface=ResourcesCompat.getFont(context, R.font.mobitec13x9)
            "Marcopolo 13x9" -> view.typeface=ResourcesCompat.getFont(context, R.font.marcopolo13x9)
            "Dimelthoz 11x96" -> view.typeface=ResourcesCompat.getFont(context, R.font.dimelthoz11x96)
            "Lightdot 16x10" -> view.typeface=ResourcesCompat.getFont(context, R.font.lightdot16x10)
            "Lightdot 13x9" -> view.typeface=ResourcesCompat.getFont(context,R.font.lightdot13x9)
            "Inova 13x7" -> view.typeface=ResourcesCompat.getFont(context,R.font.inova13x7)
            "Lightdot 13x6" -> view.typeface=ResourcesCompat.getFont(context, R.font.lightdot13x6)
            "Lumiplan Duhamel" -> view.typeface=ResourcesCompat.getFont(context, R.font.lumiplanduhamel)
        }
    }

    private fun createViewFromResource(convertView: View?, parent: ViewGroup, layoutResource: Int): TextView {
        val context = parent.context
        val view = convertView ?: LayoutInflater.from(context).inflate(layoutResource, parent, false)
        return try {
            if (textViewResourceId == 0) view as TextView
            else {
                view.findViewById(textViewResourceId) ?:
                throw RuntimeException("Failed to find view with ID " +
                        "${context.resources.getResourceName(textViewResourceId)} in item layout")
            }
        } catch (ex: ClassCastException){
            Log.d("CustomArrayAdapter", "You must supply a resource ID for a TextView")
            throw IllegalStateException(
                "ArrayAdapter requires the resource ID to be a TextView", ex)
        }
    }

    private fun bindData(value: FontList, view: TextView): TextView {
        view.text = value.desc_font.uppercase()
        view.textSize = 30f
        setupFont(view, value.desc_font)
        return view
    }
}