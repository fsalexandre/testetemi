package com.bsc.testtemi.ui.subsetor

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
import com.bsc.testtemi.data.model.ListSubSetor

@Composable
fun SubSetorListScreen(
    navHostController: NavHostController,
    vmSubSetorList: SubSetorViewModel,
    desc_setor: String?
){

    if (desc_setor != null) {
        vmSubSetorList.requestSubSetor(desc_setor)
    }
    val subSetorListState by vmSubSetorList.subSetorListState.collectAsState()
    LazyColumn {
        if (subSetorListState.isEmpty()) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }

        }

        items(subSetorListState) { listSubSetorObject: ListSubSetor ->
            Text(text = listSubSetorObject.desc_subsetor,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                modifier = Modifier.padding(16.dp)
            )
            Divider(
                color = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth(),
                thickness = 2.dp
            )
        }


    }

}