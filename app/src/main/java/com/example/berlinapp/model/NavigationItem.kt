package com.example.berlinapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class NavigationItem(
    @StringRes val title: Int,
    val id: Int,
    val list: List<RecommendationOption> = emptyList(),
    @DrawableRes val icon: Int = -1
)