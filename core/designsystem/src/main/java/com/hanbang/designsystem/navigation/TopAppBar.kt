package com.hanbang.designsystem.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.R
import com.hanbang.designsystem.theme.LocalSattoTypography

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surfaceDim,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    navigationType: TopAppBarNavigationType = TopAppBarNavigationType.BACK,
    onNavigationClick: () -> Unit = {},
    titleType: TopAppBarTitleType = TopAppBarTitleType.LEFT,
    title: String = "",
    rightActionContainer: @Composable () -> Unit = {},
) {
    CompositionLocalProvider(LocalContentColor provides contentColor) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(containerColor)
                .padding(horizontal = 8.dp)
                .then(modifier)
        ) {
            when (titleType) {
                TopAppBarTitleType.CENTER -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        TopAppBarLeftActionIcon(
                            modifier = modifier.align(Alignment.CenterStart),
                            type = navigationType,
                            onNavigationClick = onNavigationClick
                        )
                        TopAppBarTitle(
                            modifier = Modifier.align(Alignment.Center),
                            title = title
                        )
                        Box(modifier = Modifier.align(Alignment.CenterEnd)) {
                            rightActionContainer()
                        }
                    }
                }

                TopAppBarTitleType.LEFT -> {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        TopAppBarLeftActionIcon(
                            type = navigationType,
                            onNavigationClick = onNavigationClick
                        )
                        TopAppBarTitle(
                            modifier = Modifier.weight(1f),
                            title = title
                        )
                        rightActionContainer()
                    }
                }
            }
        }

    }
}

@Composable
private fun TopAppBarTitle(
    modifier: Modifier = Modifier,
    title: String
) {
    Text(
        text = title,
        style = LocalSattoTypography.current.body16Bold,
        color = LocalContentColor.current,
        modifier = modifier
    )
}

@Composable
private fun TopAppBarLeftActionIcon(
    modifier: Modifier = Modifier,
    type: TopAppBarNavigationType,
    onNavigationClick: () -> Unit = {}
) {
    val icon: @Composable (Modifier, imageVector: ImageVector) -> Unit =
        { rootModifier, imageVector ->
            IconButton(
                onClick = onNavigationClick,
                modifier = Modifier
                    .size(40.dp)
                    .then(rootModifier)
            ) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = null,
                )
            }
        }

    when (type) {
        TopAppBarNavigationType.BACK -> {
            icon(modifier, ImageVector.vectorResource(R.drawable.icon_arrow_left))
        }

        TopAppBarNavigationType.LOGO -> {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.icon_nav_logo),
                contentDescription = null,
                modifier = Modifier
                    .height(20.dp)
                    .padding(horizontal = 16.dp)
                    .clickable { onNavigationClick() }
                    .then(modifier),
            )
        }

        TopAppBarNavigationType.NONE -> Unit
    }
}

enum class TopAppBarNavigationType { BACK, LOGO, NONE }
enum class TopAppBarTitleType { CENTER, LEFT }

@Preview
@Composable
private fun CenterTopAppBarPreview() {
    TopAppBar(
        title = "Title",
        titleType = TopAppBarTitleType.CENTER
    )
}

@Preview
@Composable
private fun LeftTopAppBarPreview() {
    TopAppBar(
        title = "Title",
        titleType = TopAppBarTitleType.LEFT
    )
}

@Preview
@Composable
private fun LogoTopAppBarPreview() {
    TopAppBar(
        titleType = TopAppBarTitleType.LEFT,
        navigationType = TopAppBarNavigationType.LOGO
    )
}