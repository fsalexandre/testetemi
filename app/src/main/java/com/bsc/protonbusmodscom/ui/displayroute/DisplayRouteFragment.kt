package com.bsc.protonbusmodscom.ui.displayroute

import android.content.res.Resources
import android.graphics.*
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bsc.protonbusmodscom.R
import com.bsc.protonbusmodscom.ui.main.MainFragment
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import com.google.android.material.slider.Slider
import kotlinx.android.synthetic.main.fragment_display_route.*
import android.graphics.drawable.LayerDrawable

import android.graphics.drawable.Drawable
import android.graphics.Bitmap
import androidx.core.content.ContextCompat
import android.graphics.BitmapFactory
import androidx.core.graphics.drawable.toBitmap
import com.bsc.protonbusmodscom.data.model.DisplayLayersData


class DisplayRouteFragment : Fragment(), AdapterView.OnItemSelectedListener {

    companion object {
        fun newInstance() = DisplayRouteFragment()
    }


    private var objBitmaps: MutableList<DisplayLayersData> = mutableListOf()
    private lateinit var bitmap_source: Bitmap
    private lateinit var bitmap_typeface: Typeface
    private var bitmap_textSize: Float=0f
    private var bitmap_color: Int=-1
    private lateinit var bitmap_style: Paint.Style
    private lateinit var bitmap_text: String
    private var xIMG: Float = 15f
    private var yIMG: Float = 160f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_route, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInitialData()

        setButtons()
        txtDisplayRoute.setText("2022 WELCOME!!")
    }

    private fun setButtons(){
        btnAddCamada.setOnClickListener {
            val objBitmapItem = DisplayLayersData(
                bitmap_text = bitmap_text,
                bitmap_typeface = bitmap_typeface,
                bitmap_color = bitmap_color,
                bitmap_source = bitmap_source,
                bitmap_style = bitmap_style,
                bitmap_textSize = bitmap_textSize
            )
            objBitmaps.addAll(listOf(objBitmapItem))

        }
        btnLeft.setOnClickListener {
            xIMG-=10
            generateImage(requireView(), sliderfontsize.value, getSelectedFontTTF(requireView()), xIMG, yIMG)
        }
        btnRight.setOnClickListener {
            xIMG+=10
            generateImage(requireView(), sliderfontsize.value, getSelectedFontTTF(requireView()), xIMG, yIMG)
        }
        btnUP.setOnClickListener {
            yIMG-=10
            generateImage(requireView(), sliderfontsize.value, getSelectedFontTTF(requireView()), xIMG, yIMG)
        }
        btnDown.setOnClickListener {
            yIMG+=10
            generateImage(requireView(), sliderfontsize.value, getSelectedFontTTF(requireView()), xIMG, yIMG)
        }
        btnChoiceColor.setOnClickListener {
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            // Kotlin Code
            MaterialColorPickerDialog
                .Builder(requireContext())        					// Pass Activity Instance
                .setTitle("Choose Font Color")           		// Default "Choose Color"
                .setColorShape(ColorShape.SQAURE)   	// Default ColorShape.CIRCLE
                .setColorSwatch(ColorSwatch._300)   	// Default ColorSwatch._500
                .setDefaultColor(-1) 		// Pass Default Color
                .setColorListener { color, colorHex ->
                    // Handle Color Selection
                    this.bitmap_color=color
                    generateImage(requireView(), sliderfontsize.value, getSelectedFontTTF(requireView()), xIMG, yIMG)
                }
                .show()
        }
    }


    private fun setInitialData(){
        val view = requireView()


        fontSpinner.onItemSelectedListener = this


        sliderfontsize.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
                // Responds to when slider's touch event is being started

            }

            override fun onStopTrackingTouch(slider: Slider) {
                // Responds to when slider's touch event is being stopped

            }
        })

        sliderfontsize.addOnChangeListener { slider, value, fromUser ->
            // Responds to when slider's value is changed
            generateImage(view, value, getSelectedFontTTF(view), xIMG, yIMG)
        }

        txtDisplayRoute.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                generateImage(view, sliderfontsize.value, getSelectedFontTTF(view), xIMG, yIMG)
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })



        ArrayAdapter.createFromResource(
            view.context,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            fontSpinner.adapter = adapter
        }
    }

    fun generateImage(v: View, fontsize: Float, ttf: Int, x: Float, y: Float){
        val src = BitmapFactory.decodeResource(resources, R.drawable.displaytemplate)

        val dest = Bitmap.createBitmap(src.width, src.height, Bitmap.Config.ARGB_8888)

        val yourText = txtDisplayRoute.text.toString()

        val cs = Canvas(dest)
        val tPaint = Paint()
        tPaint.typeface = ResourcesCompat.getFont(v.context, ttf)
        tPaint.textSize = fontsize
        tPaint.color = bitmap_color
        tPaint.style = Paint.Style.FILL
        cs.drawText(yourText, x, y, tPaint)

        objBitmaps.forEach{
            cs.drawBitmap(it.bitmap_source, 0f, 0f, tPaint)
        }

            bitmap_text = yourText
            bitmap_typeface = tPaint.typeface
            bitmap_color = tPaint.color
            bitmap_source = dest
            bitmap_style = tPaint.style
            bitmap_textSize = fontsize

        imgDisplay.setImageBitmap(dest)

    }

    private fun createSingleImageFromMultipleImages(
        firstImage: Bitmap,
        secondImage: Bitmap
    ): Bitmap? {
        val result = Bitmap.createBitmap(firstImage.width, firstImage.height, firstImage.config)
        val canvas = Canvas(result)
        canvas.drawBitmap(firstImage, 0f, 0f, null)
        canvas.drawBitmap(secondImage, 0f, 0f, null)
        return result
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        this.view?.let { updateDisplaybySelectedItem(it) }

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    private fun updateDisplaybySelectedItem(v: View){
        val slider = v.findViewById<Slider>(R.id.sliderfontsize)
        generateImage(v, slider.value, getSelectedFontTTF(v), xIMG, yIMG)
    }

    fun getSelectedFontTTF(v: View): Int {

        val fontSpinner: Spinner =  v.findViewById(R.id.fontSpinner)
        when(fontSpinner.selectedItem.toString()){
            "Bat Bus Readout" -> return R.font.batbus
            "BusMatrix Condensed" -> return R.font.busmatrix
            "MBTA Bus Route Display! 216" -> return R.font.mtba216
            "MBTA Bus Route Display" -> return R.font.mtbadisplay
        }
        return 0
    }


}