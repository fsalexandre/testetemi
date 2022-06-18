package com.bsc.testtemi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
            val bundle = Bundle()
            bundle.putString("strQuery", txtQuery)
            findNavController(R.id.container).navigate(R.id.ProdutosFragment, bundle)
        }
    }

    override fun onBackPressed() {
        val fragments = supportFragmentManager.backStackEntryCount
        if (fragments == 1) {
            finish()
            return
        }
        super.onBackPressed()
    }
}
