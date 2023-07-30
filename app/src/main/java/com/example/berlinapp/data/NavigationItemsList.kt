package com.example.berlinapp.data

import com.example.berlinapp.R
import com.example.berlinapp.model.NavigationItem

val navigationItemsList = listOf(
    NavigationItem(title = R.string.cool_places_title, id = 1, list = coolPlacesList, icon = R.drawable.baseline_place_24),
    NavigationItem(title = R.string.museums_title, id = 2, list = museumsList, icon = R.drawable.baseline_museum_24),
    NavigationItem(title = R.string.bars_title, id = 3, list = barsList, icon = R.drawable.baseline_local_bar_24),
    NavigationItem(title = R.string.clubs_title, id = 4, list = clubsList, icon = R.drawable.baseline_nightlife_24),
    NavigationItem(title = R.string.food_spots_title, id = 5, list = foodSpotsList, icon = R.drawable.baseline_fastfood_24)
)