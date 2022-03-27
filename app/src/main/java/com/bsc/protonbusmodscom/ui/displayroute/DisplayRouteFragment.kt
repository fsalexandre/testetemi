package com.bsc.protonbusmodscom.ui.displayroute

import android.content.ContentValues
import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.res.ResourcesCompat
import com.bsc.protonbusmodscom.R
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import com.google.android.material.slider.Slider
import kotlinx.android.synthetic.main.fragment_display_route.*

import android.graphics.drawable.Drawable
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import com.bsc.protonbusmodscom.adapter.ImgObjectsAdapter
import com.bsc.protonbusmodscom.data.model.DisplayLayersData
import com.bsc.protonbusmodscom.listener.objImageListener
import com.google.gson.Gson
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.view.View.OnLongClickListener
import androidx.databinding.adapters.ViewBindingAdapter

import androidx.databinding.adapters.ViewBindingAdapter.setOnLongClickListener
import java.io.*
import android.os.Build
import android.provider.Settings
import android.util.Log
import com.bsc.protonbusmodscom.settings.RequestPermissionHandler
import java.lang.Exception
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.core.net.toUri
import androidx.core.view.drawToBitmap
import androidx.core.widget.doOnTextChanged
import com.bsc.protonbusmodscom.settings.settingsURL
import java.io.File.pathSeparator
import java.math.RoundingMode.valueOf


class DisplayRouteFragment : Fragment(), AdapterView.OnItemSelectedListener, objImageListener {

    companion object {
        fun newInstance() = DisplayRouteFragment()
    }


    private var objBitmaps: MutableList<DisplayLayersData> = mutableListOf()
    private lateinit var bitmap_source: Bitmap
    private lateinit var bitmap_typeface: Typeface
    private var bitmap_textSize: Float=200f
    private var bitmap_color: Int=-1
    private lateinit var bitmap_style: Paint.Style
    private lateinit var bitmap_text: String
    private var bitmap_x: Float = 15f
    private var bitmap_y: Float = 160f
    private lateinit var objectImageAdapter: ImgObjectsAdapter
    private lateinit var mRequestPermissionHandler: RequestPermissionHandler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mRequestPermissionHandler = RequestPermissionHandler()
        return inflater.inflate(R.layout.fragment_display_route, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInitialData()

        setButtons()
        txtDisplayRoute.setText("2022 WELCOME!!")
    }

    override fun onResume() {
        super.onResume()
        mRequestPermissionHandler.requestPermission(requireActivity(), arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ), 123, object : RequestPermissionHandler.RequestPermissionListener {
            override fun onSuccess() {
//                Toast.makeText(
//                    this@MainActivity,
//                    "Request permission success",
//                    Toast.LENGTH_SHORT
//                ).show()
            }

            override fun onFailed() {
                Toast.makeText(
                    requireContext(),
                    "Request permission failed, try allow again...",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        mRequestPermissionHandler.onRequestPermissionsResult(
            requestCode, permissions,
            grantResults
        )
    }


    private fun setButtons(){
        btnAddCamada.setOnClickListener {
            val objBitmapItem = DisplayLayersData(
                bitmap_text = bitmap_text,
                bitmap_typeface = bitmap_typeface,
                bitmap_color = bitmap_color,
                bitmap_source = bitmap_source,
                bitmap_style = bitmap_style,
                bitmap_textSize = bitmap_textSize,
                bitmap_x = bitmap_x,
                bitmap_y = bitmap_y
            )
            objBitmaps.addAll(listOf(objBitmapItem))
            setObjList()
            txtDisplayRoute.requestFocus()
            txtDisplayRoute.setText("")
            btnAddCamada.setImageResource(R.drawable.addarrow)

        }


        btnLeft.setOnClickListener {
            bitmap_x-=10
            generateImage(requireView(), bitmap_textSize, getSelectedFontTTF(requireView()), bitmap_x, bitmap_y)
        }
        btnRight.setOnClickListener {
            bitmap_x+=10
            generateImage(requireView(), bitmap_textSize, getSelectedFontTTF(requireView()), bitmap_x, bitmap_y)
        }

        btnUP.setOnClickListener {
            bitmap_y-=10
            generateImage(requireView(), bitmap_textSize, getSelectedFontTTF(requireView()), bitmap_x, bitmap_y)
        }
        btnDown.setOnClickListener {
            bitmap_y+=10
            generateImage(requireView(), bitmap_textSize, getSelectedFontTTF(requireView()), bitmap_x, bitmap_y)
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
                    generateImage(requireView(), bitmap_textSize, getSelectedFontTTF(requireView()), bitmap_x, bitmap_y)
                }
                .show()
        }
        btnSaveGallery.setOnClickListener {
            //saveImage(imgDisplayCompile.drawable,"null")
            saveImageToStorage(imgDisplayCompile.drawable.toBitmap())
        }
        btnPlus.setOnClickListener {
            bitmap_textSize+=5
            generateImage(requireView(), bitmap_textSize, getSelectedFontTTF(requireView()), bitmap_x, bitmap_y)         
        }
        btnMinus.setOnClickListener {
            bitmap_textSize-=5
            generateImage(requireView(), bitmap_textSize, getSelectedFontTTF(requireView()), bitmap_x, bitmap_y)
        }        
    }


    private fun setInitialData(){
        val view = requireView()


        fontSpinner.onItemSelectedListener = this



        txtDisplayRoute.doOnTextChanged { text, start, before, count -> generateImage(view, bitmap_textSize, getSelectedFontTTF(view), bitmap_x, bitmap_y) }


        ArrayAdapter.createFromResource(
            view.context,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            fontSpinner.adapter = adapter
        }
    }

    private fun generateImage(v: View, fontsize: Float, ttf: Int, x: Float, y: Float){
        val src = BitmapFactory.decodeResource(resources, R.drawable.displaytemplate)

        val dest = Bitmap.createBitmap(src.width, src.height, Bitmap.Config.ARGB_8888)
        val dest2 = Bitmap.createBitmap(src.width, src.height, Bitmap.Config.ARGB_8888)
        val yourText = txtDisplayRoute.text.toString()

        val cs = Canvas(dest)
        val cs2 = Canvas(dest2)
        val tPaint = Paint()
        tPaint.typeface = ResourcesCompat.getFont(v.context, ttf)
        tPaint.textSize = fontsize
        tPaint.color = bitmap_color
        tPaint.style = Paint.Style.FILL
        cs.drawText(yourText, x, y, tPaint)
        cs2.drawText(yourText, x, y, tPaint)
        objBitmaps.forEach{
            cs2.drawBitmap(it.bitmap_source, 0f, 0f, tPaint)
        }

            bitmap_text = yourText
            bitmap_typeface = tPaint.typeface
            bitmap_color = tPaint.color
            bitmap_source = dest
            bitmap_style = tPaint.style
            bitmap_textSize = tPaint.textSize
            bitmap_x = x
            bitmap_y = y

        imgDisplay.setImageBitmap(dest)
        imgDisplayCompile.setImageBitmap(dest2)

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        this.view?.let { updateDisplaybySelectedItem(it) }

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    private fun updateDisplaybySelectedItem(v: View){
        generateImage(v, bitmap_textSize, getSelectedFontTTF(v), bitmap_x, bitmap_y)
    }

    private fun getSelectedFontTTF(v: View): Int {


        when(fontSpinner.selectedItem.toString()){
            "Bat Bus Readout" -> return R.font.batbus
            "BusMatrix Condensed" -> return R.font.busmatrix
            "MBTA Bus Route Display! 216" -> return R.font.mtba216
            "MBTA Bus Route Display" -> return R.font.mtbadisplay
        }
        return 0
    }

    private fun saveImageToStorage(bitmap: Bitmap) {

        val contentResolver = requireActivity().contentResolver
        lateinit var filescan: File
        val imageOutStream: OutputStream = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val imgDirAux = Environment.DIRECTORY_DCIM+ File.separator + "BSC Display Routes"
            val imgAux = File(imgDirAux,"image_screenshot.png")
            filescan = imgAux
            val values = ContentValues()
            values.put(MediaStore.Images.Media.DISPLAY_NAME,"image_screenshot.png")
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/png")
            values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_DCIM+ File.separator + "BSC Display Routes")
            val uri: Uri? = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            contentResolver.openOutputStream(uri!!)!!

        } else {
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + File.separator + "/BSC Display Routes"
            val image = File(imagesDir, "image_screenshot.png")

            val root = Environment.getExternalStorageDirectory()
            val dir = File(root.absolutePath.toString() + "/DCIM/BSC Display Routes")
            dir.mkdirs()

            filescan = image
            FileOutputStream(image)
        }

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, imageOutStream)
        imageOutStream.close()


        MediaScannerConnection.scanFile(context, arrayOf(filescan.toString()),
            null, null)
    }

    private fun setObjList() {

        if (rvListObjectsEditor.adapter == null) {
            objectImageAdapter = ImgObjectsAdapter(objBitmaps, this.requireContext(), this)
            rvListObjectsEditor.adapter = objectImageAdapter
        } else {
            objectImageAdapter.notifyDataSetChanged()
        }
    }

    override fun onSelectObj(itemObjData: DisplayLayersData, v: View, pos: Int) {
        bitmap_text = itemObjData.bitmap_text
        bitmap_typeface = itemObjData.bitmap_typeface
        bitmap_color = itemObjData.bitmap_color
        bitmap_source = itemObjData.bitmap_source
        bitmap_style = itemObjData.bitmap_style
        bitmap_textSize = itemObjData.bitmap_textSize
        bitmap_x = itemObjData.bitmap_x
        bitmap_y = itemObjData.bitmap_y
        txtDisplayRoute.setText(itemObjData.bitmap_text)
        objBitmaps.removeAt(pos)
        btnAddCamada.setImageResource(R.drawable.updatearrow)

    }






}