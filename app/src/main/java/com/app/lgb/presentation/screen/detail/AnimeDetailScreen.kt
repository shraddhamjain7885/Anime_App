package com.app.lgb.presentation.screen.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import com.app.lgb.presentation.viewmodel.AnimeListViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.app.common.Resource
import com.app.domain.domain.model.AnimeItem
import com.app.lgb.R

@Composable
fun AnimeDetailScreen(
    navController: NavHostController,
    backStackEntry: NavBackStackEntry,
    viewModel: AnimeListViewModel = hiltViewModel()
) {
    val animeId = backStackEntry.arguments?.getString("animeId")?.toIntOrNull()
    val animeListState = viewModel.animeList.collectAsState().value

    when (animeListState) {
        is Resource.Loading -> LoadingState()
        is Resource.Error -> ErrorState(animeListState.message)
        is Resource.Success -> {
            val anime = animeListState.data?.find { it.malId == animeId }
            if (anime != null) {
                AnimeDetailContent(anime = anime, navController = navController)
            } else {
                NotFoundState()
            }
        }
    }
}

@Composable
fun LoadingState() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorState(message: String?) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = message ?: stringResource(id = R.string.something_went_wrong),
            color = Color.Red,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun NotFoundState() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(stringResource(id = R.string.anim_not_found), color = Color.Gray)
    }
}

@Composable
fun AnimeDetailContent(anime: AnimeItem, navController: NavHostController) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.padding(16.dp)) {
        DetailHeader(navController)
        AsyncImage(
            model = anime.imageUrl,
            contentDescription = anime.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = anime.title,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = anime.synopsis,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
        )
    }
}

@Composable
fun DetailHeader(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = stringResource(R.string.desc),
            fontSize = 20.sp,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
    }
}

