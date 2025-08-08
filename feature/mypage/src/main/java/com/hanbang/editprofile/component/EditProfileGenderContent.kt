package com.hanbang.editprofile.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.radiobutton.HbRadioButton
import com.hanbang.designsystem.theme.Gray1
import com.hanbang.designsystem.theme.LocalSattoTypography
import com.hanbang.domain.model.GenderType

/**
 *
 * @author   JGeun
 * @created  2025/08/08
 */
@Composable
internal fun EditProfileGenderContent(
	genderType: GenderType,
	onGenderSelected: (GenderType) -> Unit,
	modifier: Modifier = Modifier,
	) {
		Column(
			modifier = modifier
				.fillMaxWidth()
				.padding(horizontal = 24.dp),
			verticalArrangement = Arrangement.spacedBy(8.dp)
		) {
			Text(
				text = "성별",
				style = LocalSattoTypography.current.body16Bold,
				color = Gray1
			)

			Row(
				horizontalArrangement = Arrangement.spacedBy(20.dp)
			) {
				HbRadioButton(
					text = "남성",
					onClick = { onGenderSelected(GenderType.MALE) },
					isActive = genderType == GenderType.MALE,
					isEnabled = true
				)

				HbRadioButton(
					text = "여성",
					onClick = { onGenderSelected(GenderType.FEMALE) },
					isActive = genderType == GenderType.FEMALE,
					isEnabled = true
				)
			}
		}
	}