package com.bsc.testtemi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bsc.testtemi.ui.navigation.NavRoutes
import com.bsc.testtemi.ui.produtos.ProdutosViewModel
import com.bsc.testtemi.ui.setor.SetorListScreen
import com.bsc.testtemi.ui.setor.SetorViewModel
import com.bsc.testtemi.ui.subsetor.SubSetorListScreen
import com.bsc.testtemi.ui.subsetor.SubSetorViewModel
import com.google.gson.annotations.SerializedName
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity() : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                MainScreen()
            }

        }
    }



    @Composable
    fun MainScreen() {
        val navController = rememberNavController()
        val vmSetorList: SetorViewModel = getViewModel()
        val vmSubSetorList: SubSetorViewModel = getViewModel()
        val vmProdutosList: ProdutosViewModel = getViewModel()
        NavHost(
            navController = navController,
            startDestination = NavRoutes.ListSetorRoute.route
        ) {
            composable(NavRoutes.ListSetorRoute.route) {
                SetorListScreen(navController, vmSetorList)
            }
            composable("${NavRoutes.ListSubSetorScreen.route}/{descSetor}",
            arguments = listOf(navArgument("descSetor") {})){
               SubSetorListScreen(navController, vmSubSetorList, it.arguments!!.getString("descSetor"))
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

data class ScreenObjects(
    @SerializedName("viewModelName" ) var viewModelName : String,
    @SerializedName("viewModelObject" ) var viewModelObject : ViewModel,
    @SerializedName("navControllerObject" ) var navControllerObject : NavHostController
)

