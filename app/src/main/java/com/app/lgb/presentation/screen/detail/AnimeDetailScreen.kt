package com.app.lgb.presentation.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import coil.compose.rememberImagePainter
import com.app.lgb.presentation.viewmodel.AnimeListViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.lgb.R

@Composable
fun AnimeDetailScreen(
    navController: NavHostController,
    backStackEntry: NavBackStackEntry,
    viewModel: AnimeListViewModel = hiltViewModel()
) {
    val animeId = backStackEntry.arguments?.getString("animeId")?.toIntOrNull()
    val anime = viewModel.animeList.collectAsState().value.find { it.malId == animeId }
    val scrollState = rememberScrollState()

    anime?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(1.dp), // Padding around the top bar
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp)) // Space between icon and title

                Text(
                    text = stringResource(R.string.desc),
                    fontSize = 20.sp, // You can adjust font size as you wish
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .weight(1f)
                )
            }

            Image(
                painter = rememberImagePainter(it.imageUrl),
                contentDescription = it.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it.synopsis, style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(scrollState))
        }
    }
}

