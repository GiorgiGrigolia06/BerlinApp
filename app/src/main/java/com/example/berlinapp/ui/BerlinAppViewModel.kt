package com.example.berlinapp.ui

import androidx.lifecycle.ViewModel
import com.example.berlinapp.data.coolPlacesList
import com.example.berlinapp.data.navigationItemsList
import com.example.berlinapp.model.NavigationItem
import com.example.berlinapp.model.RecommendationOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class BerlinAppUiState(
    val recommendationsList: List<RecommendationOption>,
    val selectedListItem: RecommendationOption,
    val selectedNavigationItem: NavigationItem,
    val isShowingContentList: Boolean,
    val isShowingContent: Boolean
)

class BerlinAppViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(
        BerlinAppUiState(
            recommendationsList = navigationItemsList[0].list,
            selectedListItem = coolPlacesList[0],
            selectedNavigationItem = navigationItemsList[0],
            isShowingContentList = true,
            isShowingContent = false
        )
    )

    val uiState: StateFlow<BerlinAppUiState> = _uiState

    fun updateRecommendationsList(selectedNavigationItem: NavigationItem) {
        _uiState.update {
            it.copy(
                selectedNavigationItem = selectedNavigationItem,
                recommendationsList = selectedNavigationItem.list
            )
        }
    }

    fun goToContent(selectedListItem: RecommendationOption) {
        _uiState.update {
            it.copy(
                selectedListItem = selectedListItem,
                isShowingContent = true,
                isShowingContentList = false
            )
        }
    }

    fun goToPreviousPage(previousSelectedItem: RecommendationOption) {
        _uiState.update {
            it.copy(
                isShowingContentList = true,
                isShowingContent = false,
                selectedListItem = previousSelectedItem
            )
        }
    }
}

