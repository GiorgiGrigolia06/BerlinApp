package com.example.berlinapp.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.berlinapp.R
import com.example.berlinapp.model.RecommendationOption
import com.example.berlinapp.ui.utils.ContentType

@Composable
fun ContentScreen(
    recommendation: RecommendationOption,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    contentType: ContentType
) {
    BackHandler {
        onBackPressed()
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        )
        Column(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.space_between_image_and_text_in_card))
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ContentScreenImage(
                recommendation = recommendation,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.content_image_size))
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.content_image_clip)))
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

            ContentScreenText(
                recommendation = recommendation,
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                contentType = contentType
            )
        }
    }
}


@Composable
fun ContentScreenImage(
    recommendation: RecommendationOption,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(recommendation.imageResourceId),
        contentDescription = null,
        contentScale = ContentScale.FillHeight,
        modifier = modifier
    )
}

@Composable
fun ContentScreenText(
    recommendation: RecommendationOption,
    modifier: Modifier = Modifier,
    contentType: ContentType,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.padding_medium)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(recommendation.category),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.secondary,
                fontSize = if(contentType == ContentType.LIST_ONLY) {
                    dimensionResource(R.dimen.row_font_size_of_content_compact).value.sp
                } else {
                    dimensionResource(R.dimen.row_font_size_of_content_medium).value.sp
                }
            )
            Text(
                text = stringResource(R.string.berlin),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.secondary,
                fontSize = if(contentType == ContentType.LIST_ONLY) {
                    dimensionResource(R.dimen.row_font_size_of_content_compact).value.sp
                } else {
                    dimensionResource(R.dimen.row_font_size_of_content_medium).value.sp
                }
            )
        }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

        Text(
            text = stringResource(recommendation.title),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

        Text(
            text = stringResource(recommendation.content),
            fontWeight = FontWeight.Light,
            lineHeight = dimensionResource(R.dimen.content_line_height).value.sp,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}