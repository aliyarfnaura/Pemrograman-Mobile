package com.example.moviescrollablelist.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.moviescrollablelist.data.movieList

@Composable
fun MovieList(navController: NavHostController) {
    val context = LocalContext.current

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(movieList.size) { index ->
            val movie = movieList[index]
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = movie.image),
                        contentDescription = null,
                        modifier = Modifier
                            .width(100.dp)
                            .height(140.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = movie.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text(
                            text = movie.description,
                            fontSize = 14.sp,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row {
                            Button(
                                onClick = {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movie.url))
                                    context.startActivity(intent)
                                }
                            ) {
                                Text("Letterboxd")
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(
                                onClick = {
                                    navController.navigate("movieDetail/${Uri.encode(movie.description)}/${movie.image}")
                                }
                            ) {
                                Text("Detail")
                            }
                        }
                    }
                }
            }
        }
    }
}
