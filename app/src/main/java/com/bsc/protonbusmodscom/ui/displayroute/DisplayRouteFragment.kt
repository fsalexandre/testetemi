package com.bsc.protonbusmodscom.ui.displayroute

import android.content.ContentValues
import android.graphics.*
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.bsc.protonbusmodscom.R
import com.bsc.protonbusmodscom.adapter.ImgObjectsAdapter
import com.bsc.protonbusmodscom.data.adapter.FontListAdapter
import com.bsc.protonbusmodscom.data.model.DisplayLayersData
import com.bsc.protonbusmodscom.data.model.FontList
import com.bsc.protonbusmodscom.listener.objImageListener
import com.bsc.protonbusmodscom.settings.RequestPermissionHandler
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import kotlinx.android.synthetic.main.fragment_display_route.*
import kotlinx.android.synthetic.main.fragment_display_route.view.*

import java.io.*
import java.util.*


class DisplayRouteFragment : Fragment(), objImageListener {

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
    private  var fontList: MutableList<FontList> = mutableListOf()


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
        testFunctions()

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


        txtDisplayRoute.doOnTextChanged { text, start, before, count -> generateImage(view, bitmap_textSize, getSelectedFontTTF(view), bitmap_x, bitmap_y) }


//        ArrayAdapter.createFromResource(
//            view.context,
//            R.array.fonts_array,
//            android.R.layout.simple_spinner_item
//        ).also { adapter ->
//
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            fontSpinner.adapter = adapter
//        }

        setupFontSpinner()


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
        //tPaint.typeface = ResourcesCompat.getFont(v.context, R.font.lightdot13x6)
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

    private fun setupFontSpinner() {
        val strings = resources.getIntArray(R.array.fonts_array)
        for (i in strings.indices-1) {
            val itemAux = FontList(
                id_font = strings[i],
                desc_font = resources.getStringArray(R.array.fonts_array)[i].replace("res/font/","").replace(".ttf","")
            )
            fontList.add(itemAux)
        }

        val spinnerAdapter = FontListAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            values = fontList
        )

        // Specify dropdown layout style - simple list view with 1 item per line
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        // Apply the adapter to the spinner
        fontSpinner.adapter = spinnerAdapter
        // spinner is referenced spinner by finViewById.
        fontSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                view?.let { updateDisplaybySelectedItem(view) }
                Log.d("position",position.toString())
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // code for nothing selected in dropdown
            }

        }
        txtDisplayRoute.setText("1234 SAMPLE")
        txtDisplayRoute.requestFocus()
    }



    private fun updateDisplaybySelectedItem(v: View){
        generateImage(v, bitmap_textSize, getSelectedFontTTF(v), bitmap_x, bitmap_y)
    }


    private fun getSelectedFontTTF(v: View): Int {
        val selectedItem: FontList = fontSpinner.selectedItem as FontList

        when(selectedItem.desc_font){
            "Bat Bus Readout" -> return R.font.batbus
            "BusMatrix Condensed" -> return R.font.busmatrix
            "MBTA Bus Route Display! 216" -> return R.font.mtba216
            "MBTA Bus Route Display" -> return R.font.mtbadisplay
            "Led 8x6" -> return R.font.led8x6
            "Mobitec 13x9" -> return R.font.mobitec13x9
            "Marcopolo 13x9" -> return R.font.marcopolo13x9
            "Dimelthoz 11x96" -> return R.font.dimelthoz11x96
            "Lightdot 16x10" -> return R.font.lightdot16x10
            "Lightdot 13x9" -> return R.font.lightdot13x9
            "Inova 13x7" -> return R.font.inova13x7
            "Lightdot 13x6" -> return R.font.lightdot13x6
            "Lumiplan Duhamel" -> return R.font.lumiplanduhamel
        }

        return R.font.batbus
    }

    private fun saveImageToStorage(bitmap: Bitmap) {

        val contentResolver = requireActivity().contentResolver
        lateinit var filescan: File
        val filename = "BSC_"+System.currentTimeMillis().toString()+".png"
        val imageOutStream: OutputStream = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val imgDirAux = Environment.DIRECTORY_DCIM+ File.separator + "BSC Display Routes"
            val imgAux = File(imgDirAux,filename)
            filescan = imgAux
            val values = ContentValues()
            values.put(MediaStore.Images.Media.DISPLAY_NAME,filename)
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/png")
            values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_DCIM+ File.separator + "BSC Display Routes")
            val uri: Uri? = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            contentResolver.openOutputStream(uri!!)!!

        } else {
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + File.separator + "/BSC Display Routes"
            val image = File(imagesDir, filename)

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
        Toast.makeText(requireContext(),"Display Route saved in Images Gallery",Toast.LENGTH_LONG).show()
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
        objectImageAdapter.notifyDataSetChanged()
        btnAddCamada.setImageResource(R.drawable.updatearrow)

    }


    private fun testFunctions(){
        val folderContext: String = "dest"
        Log.d("folders",Environment.getDataDirectory().toString()+ File.separator +"com.viamep.pbsu/files"+ File.separator + folderContext)

        val file = File("/storage/emulated/0/Android/"+Environment.getDataDirectory().toString()+ File.separator +"com.viamep.pbsu/files"+ File.separator + folderContext)
        //val file = File("/Android/"+Environment.getDataDirectory().toString())
        val files = file.list()
        if (file.isDirectory) {
            //  files exist
            Log.d("folder","Encontrei o Proton Bus @ "+file.absolutePath)
        }else{
            Log.d("folder","achei nao")
            file.mkdir()
        }


    }

}