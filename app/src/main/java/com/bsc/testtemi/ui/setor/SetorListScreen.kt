package com.bsc.testtemi.ui.setor

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bsc.testtemi.data.model.ListSetor
import com.bsc.testtemi.ui.navigation.NavRoutes

@Composable
fun SetorListScreen(navController: NavHostController, viewModel: SetorViewModel){
    viewModel.requestSetor()
    val setorListState by viewModel.setorListState.collectAsState()
    LazyColumn {
        if (setorListState.isEmpty()) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }

        }
        items(setorListState) { listSetorObject: ListSetor ->
            Row(modifier = Modifier.clickable { navController.navigate(NavRoutes.ListSubSetorScreen.route+"/"+listSetorObject.descSetor)}) {
                Text(text = listSetorObject.descSetor.toString(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(16.dp)
                )

            }
            Divider(
                color = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                thickness = 2.dp,

            )
        }
    }
}