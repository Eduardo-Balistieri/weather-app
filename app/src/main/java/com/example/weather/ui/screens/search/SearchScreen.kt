package com.example.weather.ui.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Place
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.domain.entities.Location
import com.example.weather.ui.components.SearchBox
import com.example.weather.ui.theme.searchPageBackground
import com.example.weather.viewmodels.MainViewModel

@Composable
fun SearchScreen(
    mainViewModel: MainViewModel,
    navigateToWeatherScreen: (Location) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var searchResults by remember { mutableStateOf(emptyList<Location>()) }

    var displayList by remember { mutableStateOf(false) }

    LaunchedEffect(searchQuery) {
        if(searchQuery.isNotBlank()) {
            searchResults = mainViewModel.searchForLocation(searchQuery)

            if(!displayList) {
                displayList = true
            }
        }
        else {
            displayList = false
        }
    }

    Surface {
        SearchScreenContent(
            searchQuery = searchQuery,
            onTextChange = { text -> searchQuery = text },
            searchResults = searchResults,
            displayList = displayList,
            onLocationClick = { location -> navigateToWeatherScreen(location) }
        )
    }
}

@Composable
fun SearchScreenContent(
    searchQuery: String,
    onTextChange: (String) -> Unit,
    searchResults: List<Location>,
    displayList: Boolean,
    onLocationClick: (Location) -> Unit
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.searchPageBackground)
            .padding(
                top = 16.dp,
                start = 8.dp,
                end = 8.dp
            )
    ) {
        SearchBox(
            text = searchQuery,
            onTextChange = { text -> onTextChange(text) }
        )

        if(displayList) {
            LazyColumn (
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                items(searchResults) { location ->
                    Column (
                        modifier = Modifier
                            .clickable { onLocationClick(location) }
                            .padding(horizontal = 6.dp)
                    ) {
                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(vertical = 12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Place,
                                contentDescription = stringResource(id = R.string.place_icon)
                            )
                            Text(
                                text = "${location.city}, ${location.country}",
                                modifier = Modifier
                                    .padding(start = 12.dp)
                            )
                        }
                        Divider(
                            color = Color.White,
                            thickness = 1.dp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .alpha(0.05F)
                        )
                    }
                }
            }
        }
        else {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_weaher_draw),
                    contentDescription = stringResource(id = R.string.forecast_illustration),
                    modifier = Modifier
                        .fillMaxWidth(0.85F)
                )
                Text(
                    text = stringResource(id = R.string.search_screen_hint),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth(0.5F),
                    lineHeight = 24.sp
                )
            }
        }
    }
}

@Composable
@Preview
fun SearchScreenContentPreview() {
    SearchScreenContent(
        searchQuery = "",
        onTextChange = { },
        searchResults = emptyList(),
        displayList = false,
        onLocationClick = { }
    )
}
