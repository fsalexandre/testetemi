package com.bsc.testtemi

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.main_activity.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setButtonAction()
    }

    fun setButtonAction(){
    btnQuery.setOnClickListener {
        val txtQuery = txtQuery.text.toString()
        val bundle = bundleOf("strQuery" to txtQuery)
        findNavController(R.id.container).navigate(R.id.ProdutosFragment, bundle)
    }
}
}
