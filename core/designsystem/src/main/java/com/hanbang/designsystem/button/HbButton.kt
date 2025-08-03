package com.hanbang.designsystem.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.R
import com.hanbang.designsystem.button.model.ButtonState
import com.hanbang.designsystem.button.model.HbButtonColors
import com.hanbang.designsystem.button.model.HbButtonStyleProperties
import com.hanbang.designsystem.util.clickableSingle

/**
 *
 * @author   JGeun
 * @created  2025/07/22
 */
@Composable
fun HbBoxButton(
	text: String,
	onClick: () -> Unit,
	colors: HbButtonColors,
	styles: HbButtonStyleProperties,
	modifier: Modifier = Modifier,
	containerPadding: PaddingValues = PaddingValues(),
	isEnabled: Boolean = true,
	leftContent: @Composable () -> Unit = {},
	rightContent: @Composable () -> Unit = {}
) {
	var buttonState by remember { mutableStateOf(ButtonState.Idle) }

	val interactionContainerColor by animateColorAsState(
		targetValue = if (buttonState == ButtonState.Pressed) colors.pressedContainerColor else colors.containerColor,
		animationSpec = tween(100, 0),
		label = "button-interaction",
	)

	val containerColor = if (isEnabled) interactionContainerColor else colors.disabledContainerColor
	val contentColor = if (isEnabled) colors.contentColor else colors.disabledContentColor

	Box(
		modifier = modifier
			.padding(containerPadding)
			.clip(shape = styles.shape)
			.height(styles.height)
	) {
		Box(
			modifier = Modifier
				.matchParentSize()
				.background(containerColor)
				.border(width = 1.dp, color = colors.strokeColor, shape = styles.shape)
				.clickableSingle(activeRippleEffect = false) {
					if(isEnabled) onClick()
				}
				.pointerInput(buttonState) {
					awaitPointerEventScope {
						buttonState = if (buttonState == ButtonState.Pressed) {
							waitForUpOrCancellation()
							ButtonState.Idle
						} else {
							awaitFirstDown(false)
							ButtonState.Pressed
						}
					}
				}
		)

		Box(
			modifier = Modifier
				.fillMaxHeight()
				.padding(styles.contentPadding),
			contentAlignment = Alignment.Center
		) {
			Row(
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.spacedBy(styles.spacing),
			) {

				leftContent()

				Text(
					modifier = Modifier.fillMaxWidth(),
					text = text,
					textAlign = TextAlign.Center,
					color = contentColor,
					style = styles.textStyle
				)

				rightContent()
			}
		}
	}
}

@Composable
@Preview
private fun HbBoxButtonPrimaryPreview() {
	// Button/Primary
	Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
		HbBoxButton(
			styles = HbButtonStyles.xLarge,
			text = "Button",
			onClick = { },
			colors = HbButtonColorType.primary,
			leftContent = {

			},
			rightContent = {

			}
		)

		HbBoxButton(
			styles = HbButtonStyles.xLarge,
			text = "Button",
			onClick = { },
			colors = HbButtonColorType.primary,
			leftContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.primary.iconColor
				)
			},
			rightContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.primary.iconColor
				)
			}
		)

		HbBoxButton(
			styles = HbButtonStyles.large,
			text = "Button",
			onClick = { },
			colors = HbButtonColorType.primary,
			leftContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.primary.iconColor
				)
			},
			rightContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.primary.iconColor
				)
			}
		)

		HbBoxButton(
			styles = HbButtonStyles.medium,
			text = "Button",
			onClick = { },
			colors = HbButtonColorType.primary,
			leftContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.primary.iconColor
				)
			},
			rightContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.primary.iconColor
				)
			}
		)

		HbBoxButton(
			styles = HbButtonStyles.small,
			text = "Button",
			onClick = { },
			colors = HbButtonColorType.primary,
			leftContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.primary.iconColor
				)
			},
			rightContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.primary.iconColor
				)
			}
		)

		HbBoxButton(
			styles = HbButtonStyles.xSmall,
			text = "Button",
			onClick = { },
			colors = HbButtonColorType.primary,
			leftContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.primary.iconColor
				)
			},
			rightContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.primary.iconColor
				)
			}
		)
	}
}

@Composable
@Preview
private fun HbBoxButtonTintedPreview() {
	// Button/Tinted
	Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
		HbBoxButton(
			styles = HbButtonStyles.xLarge,
			text = "Button",
			onClick = { },
			colors = HbButtonColorType.tinted,
			leftContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.tinted.iconColor
				)
			},
			rightContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.tinted.iconColor
				)
			}
		)

		HbBoxButton(
			styles = HbButtonStyles.large,
			text = "Button",
			onClick = { },
			colors = HbButtonColorType.tinted,
			leftContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.tinted.iconColor
				)
			},
			rightContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.tinted.iconColor
				)
			}
		)

		HbBoxButton(
			styles = HbButtonStyles.medium,
			text = "Button",
			onClick = { },
			colors = HbButtonColorType.tinted,
			leftContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.tinted.iconColor
				)
			},
			rightContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.tinted.iconColor
				)
			}
		)

		HbBoxButton(
			styles = HbButtonStyles.small,
			text = "Button",
			onClick = { },
			colors = HbButtonColorType.tinted,
			leftContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.tinted.iconColor
				)
			},
			rightContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.tinted.iconColor
				)
			}
		)

		HbBoxButton(
			styles = HbButtonStyles.xSmall,
			text = "Button",
			onClick = { },
			colors = HbButtonColorType.tinted,
			leftContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.tinted.iconColor
				)
			},
			rightContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.tinted.iconColor
				)
			}
		)
	}
}

@Composable
@Preview
private fun HbBoxButtonBorderPreview() {
	// Button/Border
	Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
		HbBoxButton(
			styles = HbButtonStyles.xLarge,
			text = "Button",
			onClick = { },
			colors = HbButtonColorType.border,
			leftContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.border.iconColor
				)
			},
			rightContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.border.iconColor
				)
			}
		)

		HbBoxButton(
			styles = HbButtonStyles.large,
			text = "Button",
			onClick = { },
			colors = HbButtonColorType.border,
			leftContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.border.iconColor
				)
			},
			rightContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.border.iconColor
				)
			}
		)

		HbBoxButton(
			styles = HbButtonStyles.medium,
			text = "Button",
			onClick = { },
			colors = HbButtonColorType.border,
			leftContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.border.iconColor
				)
			},
			rightContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.border.iconColor
				)
			}
		)

		HbBoxButton(
			styles = HbButtonStyles.small,
			text = "Button",
			onClick = { },
			colors = HbButtonColorType.border,
			leftContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.border.iconColor
				)
			},
			rightContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.border.iconColor
				)
			}
		)

		HbBoxButton(
			styles = HbButtonStyles.xSmall,
			text = "Button",
			onClick = { },
			colors = HbButtonColorType.border,
			leftContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.border.iconColor
				)
			},
			rightContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.border.iconColor
				)
			}
		)
	}
}

@Preview
@Composable
private fun HbButtonBorderGrayPreview() {
	// Button/borderGray
	Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
		HbBoxButton(
			styles = HbButtonStyles.xLarge,
			text = "Button",
			onClick = { },
			colors = HbButtonColorType.borderGray,
			leftContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.borderGray.iconColor
				)
			},
			rightContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.borderGray.iconColor
				)
			}
		)

		HbBoxButton(
			styles = HbButtonStyles.large,
			text = "Button",
			onClick = { },
			colors = HbButtonColorType.borderGray,
			leftContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.borderGray.iconColor
				)
			},
			rightContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.borderGray.iconColor
				)
			}
		)

		HbBoxButton(
			styles = HbButtonStyles.medium,
			text = "Button",
			onClick = { },
			colors = HbButtonColorType.borderGray,
			leftContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.borderGray.iconColor
				)
			},
			rightContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.borderGray.iconColor
				)
			}
		)

		HbBoxButton(
			styles = HbButtonStyles.small,
			text = "Button",
			onClick = { },
			colors = HbButtonColorType.borderGray,
			leftContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.borderGray.iconColor
				)
			},
			rightContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.borderGray.iconColor
				)
			}
		)

		HbBoxButton(
			styles = HbButtonStyles.xSmall,
			text = "Button",
			onClick = { },
			colors = HbButtonColorType.borderGray,
			leftContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.borderGray.iconColor
				)
			},
			rightContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.borderGray.iconColor
				)
			}
		)
	}
}

@Preview
@Composable
private fun HbTextButtonPreview() {
	// Button/Text
	Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
		HbBoxButton(
			styles = HbButtonStyles.xLarge,
			text = "Button",
			onClick = { },
			colors = HbButtonColorType.text,
			leftContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.text.iconColor
				)
			},
			rightContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.text.iconColor
				)
			}
		)

		HbBoxButton(
			styles = HbButtonStyles.large,
			text = "Button",
			onClick = { },
			colors = HbButtonColorType.text,
			leftContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.text.iconColor
				)
			},
			rightContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.text.iconColor
				)
			}
		)

		HbBoxButton(
			styles = HbButtonStyles.medium,
			text = "Button",
			onClick = { },
			colors = HbButtonColorType.text,
			leftContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.text.iconColor
				)
			},
			rightContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.text.iconColor
				)
			}
		)

		HbBoxButton(
			styles = HbButtonStyles.small,
			text = "Button",
			onClick = { },
			colors = HbButtonColorType.text,
			leftContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.text.iconColor
				)
			},
			rightContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.text.iconColor
				)
			}
		)

		HbBoxButton(
			styles = HbButtonStyles.xSmall,
			text = "Button",
			onClick = { },
			colors = HbButtonColorType.text,
			leftContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.text.iconColor
				)
			},
			rightContent = {
				Icon(
					painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
					contentDescription = null,
					modifier = Modifier.size(20.dp),
					tint = HbButtonColorType.text.iconColor
				)
			}
		)
	}
}