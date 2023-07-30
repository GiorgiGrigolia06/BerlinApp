package com.example.berlinapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class RecommendationOption(
    @StringRes val title: Int,
    @DrawableRes val imageResourceId: Int,
    val id: Int,
    @StringRes val content: Int = -1,
    @StringRes val category: Int = -1,
    val list: List<RecommendationOption> = emptyList(),
    @DrawableRes val icon: Int = -1
)
