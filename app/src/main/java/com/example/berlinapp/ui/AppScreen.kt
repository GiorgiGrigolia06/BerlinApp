package com.example.berlinapp.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.berlinapp.R
import com.example.berlinapp.data.navigationItemsList
import com.example.berlinapp.model.NavigationItem
import com.example.berlinapp.model.RecommendationOption
import com.example.berlinapp.ui.utils.ContentType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(
    contentType: ContentType,
    recommendationList: List<RecommendationOption>,
    currentTab: NavigationItem,
    recommendation: RecommendationOption,
    modifier: Modifier = Modifier,
    onClick: (RecommendationOption) -> Unit,
    onTabPressed: (NavigationItem) -> Unit = { },
    onBackPressed: () -> Unit = { },
    isShowingContent: Boolean,
    isShowingContentList: Boolean
) {
    BackHandler {
        onBackPressed()
    }

    when(contentType) {
        ContentType.LIST_ONLY -> {
            Column(modifier = modifier) {
                if(isShowingContentList) {
                    RecommendationsList(
                        recommendationList = recommendationList,
                        onClick = onClick,
                        modifier = Modifier.weight(1f),
                        contentType = contentType
                    )

                    BerlinAppBottomNavigationBar(
                        currentTab = currentTab,
                        onTabPressed = onTabPressed,
                        navigationItemContentList = navigationItemsList,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                }

                if(isShowingContent) {
                    ContentScreen(
                        recommendation = recommendation,
                        contentType = contentType,
                        onBackPressed = onBackPressed
                    )
                }
            }
        }

        ContentType.MAIN_LIST_AND_CONTENT_LIST -> {
            Row {
                if (isShowingContentList) {
                    BerlinAppNavigationRail(
                        currentTab = currentTab,
                        onTabPressed = onTabPressed,
                        navigationItemContentList = navigationItemsList,
                        modifier = Modifier
                            .padding(top = dimensionResource(R.dimen.foldable_size_navigation_rail_top_padding))
                    )

                    RecommendationsList(
                        recommendationList = recommendationList,
                        onClick = onClick,
                        modifier = Modifier.weight(1f),
                        contentType = contentType
                    )
                }

                if (isShowingContent) {
                    ContentScreen(
                        recommendation = recommendation,
                        contentType = contentType,
                        onBackPressed = onBackPressed
                    )
                }
            }
        }

        else -> {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                if (isShowingContent && isShowingContentList) {
                    PermanentNavigationDrawer(
                        drawerContent = {
                            PermanentDrawerSheet(modifier = Modifier.width(dimensionResource(R.dimen.permanent_drawer_sheet_width))) {
                                NavigationDrawerContent(
                                    currentTab = currentTab,
                                    onTabPressed = onTabPressed,
                                    navigationItemContentList = navigationItemsList,
                                    modifier = Modifier
                                        .padding(top = dimensionResource(R.dimen.permanent_drawer_sheet_top_padding))
                                )
                            }
                        }
                    ) {
                        Row {
                            RecommendationsList(
                                recommendationList = recommendationList,
                                onClick = onClick,
                                modifier = Modifier.weight(1f),
                                contentType = contentType
                            )

                            ContentScreen(
                                recommendation = recommendation,
                                contentType = contentType,
                                onBackPressed = onBackPressed,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RecommendationsList(
    recommendationList: List<RecommendationOption>,
    onClick: (RecommendationOption) -> Unit,
    modifier: Modifier = Modifier,
    contentType: ContentType
) {
    LazyColumn(
        contentPadding = if (contentType == ContentType.LIST_ONLY || contentType == ContentType.MAIN_LIST_AND_CONTENT_LIST) {
            PaddingValues(
                vertical = dimensionResource(R.dimen.lazy_column_padding_vertical),
                horizontal = dimensionResource(R.dimen.lazy_column_padding_horizontal)
            )
        } else {
               PaddingValues(
                   vertical = dimensionResource(R.dimen.large_screen_lazy_column_padding_vertical),
                   horizontal = dimensionResource(R.dimen.large_screen_lazy_column_padding_horizontal)
               )
        },
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.lazy_column_padding_vertical)),
        modifier = modifier
    ) {
        items(recommendationList) { recommendation ->
            RecommendationCard(
                recommendation = recommendation,
                onClick = onClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RecommendationCard(
    recommendation: RecommendationOption,
    modifier: Modifier = Modifier,
    onClick: (RecommendationOption) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = { onClick(recommendation) },
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_image_height)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.space_between_image_and_text_in_card))
        ) {
            RecommendationCardImage(
                recommendation = recommendation,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.card_image_height))
            )
            RecommendationCardText(
                recommendation = recommendation,
                modifier = Modifier
                    .widthIn(max = dimensionResource(R.dimen.max_width_of_text_in_list_item))
            )
        }
    }
}

@Composable
private fun RecommendationCardImage(recommendation: RecommendationOption, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(recommendation.imageResourceId),
        contentDescription = null,
        contentScale = ContentScale.FillHeight,
        modifier = modifier
    )
}

@Composable
private fun RecommendationCardText(recommendation: RecommendationOption, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(recommendation.title),
        style = MaterialTheme.typography.headlineLarge,
        modifier = modifier
    )
}

@Composable
private fun BerlinAppNavigationRail(
    currentTab: NavigationItem,
    onTabPressed: (NavigationItem) -> Unit,
    navigationItemContentList: List<NavigationItem>,
    modifier: Modifier = Modifier
) {
    NavigationRail(modifier = modifier) {
        navigationItemContentList.forEach {
            NavigationRailItem(
                selected = currentTab.id == it.id,
                onClick = { onTabPressed(it) },
                icon = {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(it.title)
                    )
                },
                modifier = modifier
            )
        }
    }
}

@Composable
private fun BerlinAppBottomNavigationBar(
    currentTab: NavigationItem,
    onTabPressed: (NavigationItem) -> Unit,
    navigationItemContentList: List<NavigationItem>,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        navigationItemContentList.forEach {
            NavigationRailItem(
                selected = currentTab.id == it.id,
                onClick = { onTabPressed(it) },
                icon = {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(it.title),
                        fontSize = dimensionResource(R.dimen.navigation_font_size).value.sp
                    )
                },
                modifier = modifier
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NavigationDrawerContent(
    currentTab: NavigationItem,
    onTabPressed: (NavigationItem) -> Unit,
    navigationItemContentList: List<NavigationItem>,
    modifier: Modifier = Modifier
) {
    navigationItemContentList.forEach {
        NavigationDrawerItem(
            selected = currentTab.id == it.id,
            label = {
                Text(
                    text = stringResource(it.title),
                    fontSize = dimensionResource(R.dimen.navigation_font_size).value.sp
                )
                    },
            icon = {
                Icon(
                    painter = painterResource(id = it.icon),
                    contentDescription = null
                ) },
            colors = NavigationDrawerItemDefaults.colors(
                unselectedContainerColor = Color.Transparent
            ),
            onClick = { onTabPressed(it) },
            modifier = modifier
        )
    }
}
