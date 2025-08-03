package com.hanbang.main.component

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.theme.Gray5
import com.hanbang.designsystem.theme.Gray7
import com.hanbang.designsystem.theme.Primary2
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.util.topBorder
import com.hanbang.main.MainTab

/**
 *
 * @author   JGeun
 * @created  2025/08/03
 */
@Composable
internal fun MainBottomBar(
	visible: Boolean,
	tabs: List<MainTab>,
	currentTab: MainTab?,
	onTabSelected: (MainTab) -> Unit,
	modifier: Modifier = Modifier,
) {
	AnimatedVisibility(
		visible = visible,
		enter = fadeIn() + slideIn { IntOffset(0, it.height) },
		exit = fadeOut() + slideOut { IntOffset(0, it.height) }
	) {
		Row(
			modifier = modifier
				.fillMaxWidth()
				.height(72.dp)
				.topBorder(
					strokeWidth = 0.6.dp,
					color = Gray7,
				)
				.background(
					color = MaterialTheme.colorScheme.surface,
				)
				.padding(horizontal = 4.dp),
			horizontalArrangement = Arrangement.spacedBy(5.dp),
		) {
			tabs.forEach { tab ->
				MainBottomBarItem(
					tab = tab,
					selected = tab == currentTab,
					onClick = { onTabSelected(tab) },
				)
			}
		}
	}
}

@Composable
private fun RowScope.MainBottomBarItem(
	tab: MainTab,
	selected: Boolean,
	onClick: () -> Unit,
	modifier: Modifier = Modifier,
) {
	Column(
		modifier = modifier
			.weight(1f)
			.fillMaxHeight()
			.selectable(
				selected = selected,
				indication = null,
				role = null,
				interactionSource = remember { MutableInteractionSource() },
				onClick = onClick,
			),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		Icon(
			painter = painterResource(tab.iconResId),
			contentDescription = tab.contentDescription,
			tint = if (selected) {
				Primary2
			} else {
				Gray5
			},
			modifier = Modifier.size(24.dp),
		)

		Spacer(Modifier.height(8.dp))

		Text(
			text = tab.contentDescription,
			style = if (selected) {
				SattoTheme.typography.caption12Bold
			} else {
				SattoTheme.typography.caption12Regular
			},
			color = if (selected) {
				Primary2
			} else {
				Gray5
			}
		)
	}
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MainBottomBarPreview() {
	SattoTheme {
		MainBottomBar(
			visible = true,
			tabs = MainTab.entries,
			currentTab = MainTab.HOME,
			onTabSelected = { },
		)
	}
}