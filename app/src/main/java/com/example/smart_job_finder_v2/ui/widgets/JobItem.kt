package com.example.smart_job_finder_v2.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smart_job_finder_v2.R
import com.example.smart_job_finder_v2.Screen
import com.example.smart_job_finder_v2.models.Job

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobItem(
    job: Job,
    navigate: (String) -> Unit
) {
    Card(
        onClick = { navigate(Screen.DetailsScreen.route) },
        modifier = Modifier.padding(6.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.size(82.dp)) {
                Image(
                    contentDescription = "image",
                    painter = painterResource(id = R.drawable.amazon),
                    contentScale = ContentScale.Fit
                )

            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(top = 8.dp)
            ) {

                Text(
                    text = job.title,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.Default.Place,
                        contentDescription = "Location",
                        tint = Color.Black,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        "Graz",
                        modifier = Modifier.padding(end = 8.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
                Text(
                    text = job.type,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            IconButton(onClick = {}, modifier = Modifier.padding(top = 16.dp)) {
                Icon(
                    Icons.Filled.FavoriteBorder,
                    contentDescription = "Settings", modifier = Modifier.size(38.dp),

                    )
            }

        }
    }

}
