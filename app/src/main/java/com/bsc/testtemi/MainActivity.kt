package com.bsc.testtemi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.findNavController
import com.bsc.testtemi.data.model.ListSetor
import com.bsc.testtemi.ui.setor.SetorViewModel
import kotlinx.android.synthetic.main.main_activity.*
import org.koin.androidx.viewmodel.ext.android.getViewModel


class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SetorListScreen()
                }

        }
       //setContentView(R.layout.main_activity)
        //setButtonAction()
    }


    @Composable
    fun SetorListScreen(){
        //val viewModel: SetorViewModel by viewModels()
        val viewModel: SetorViewModel = getViewModel()
        //val viewModel = viewModel(modelClass = SetorViewModel::class.java)
        viewModel.requestSetor()
        val state by viewModel.state.collectAsState()
        LazyColumn {
            if (state.isEmpty()) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }

            }

            items(state) { character: ListSetor ->
                Text(text = character.descSetor.toString(),
                    fontSize = 25.sp
                )
            }


        }

    }


//    fun setButtonAction(){
//        btnQuery.setOnClickListener {
//            val txtQuery = txtQuery.text.toString()
//            val bundle = Bundle()
//            bundle.putString("strQuery", txtQuery)
//            findNavController(R.id.container).navigate(R.id.ProdutosFragment, bundle)
//        }
//    }
//
//    override fun onBackPressed() {
//        val fragments = supportFragmentManager.backStackEntryCount
//        if (fragments == 1) {
//            finish()
//            return
//        }
//        super.onBackPressed()
//    }
}
