package com.example.berlinapp.ui

import android.app.Activity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.berlinapp.ui.theme.BerlinAppTheme
import com.example.berlinapp.ui.utils.ContentType


@Composable
fun BerlinApp(
    viewModel: BerlinAppViewModel = viewModel(),
    windowSize: WindowWidthSizeClass,
) {
    val uiState by viewModel.uiState.collectAsState()

    val contentType: ContentType = when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            ContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Medium -> {
            ContentType.MAIN_LIST_AND_CONTENT_LIST
        }
        WindowWidthSizeClass.Expanded -> {
            ContentType.MAIN_LIST_CONTENT_LIST_AND_CONTENT
        }
        else -> {
            ContentType.LIST_ONLY
        }
    }

    var previousSelectedItem by remember { mutableStateOf(uiState.selectedListItem) }

    val activity = LocalContext.current as Activity
    AppScreen(
        recommendationList = uiState.recommendationsList,
        onBackPressed = if (uiState.isShowingContentList) { { activity.finish() } }
        else { { viewModel.goToPreviousPage(previousSelectedItem) } },
        onClick = {
            previousSelectedItem = uiState.selectedListItem
            viewModel.goToContent(it)
        },
        contentType = contentType,
        currentTab = uiState.selectedNavigationItem,
        onTabPressed = { viewModel.updateRecommendationsList(it) },
        isShowingContent = uiState.isShowingContent,
        isShowingContentList = uiState.isShowingContentList,
        recommendation = uiState.selectedListItem
    )


    if (contentType == ContentType.MAIN_LIST_CONTENT_LIST_AND_CONTENT) {
        AppScreen(
            contentType = contentType,
            recommendationList = uiState.recommendationsList,
            currentTab = uiState.selectedNavigationItem,
            recommendation = uiState.selectedListItem,
            onClick = { viewModel.goToContent(it) },
            isShowingContent = true,
            isShowingContentList = true,
            onBackPressed = { activity.finish() },
            onTabPressed = { viewModel.updateRecommendationsList(it) }
        )
    }
}


@Preview(showBackground = true, widthDp = 1000)
@Composable
fun CategoryCardPreview() {
    BerlinAppTheme(darkTheme = true) {
        BerlinApp(windowSize = WindowWidthSizeClass.Compact)
    }
}
