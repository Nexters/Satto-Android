package com.hanbang.onboarding.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.hanbang.designsystem.R
import com.hanbang.designsystem.bottomsheet.HbBottomSheet
import com.hanbang.designsystem.bottomsheet.HbBottomSheetItem
import com.hanbang.designsystem.button.HbBoxButton
import com.hanbang.designsystem.button.HbButtonColorType
import com.hanbang.designsystem.button.HbButtonStyles
import com.hanbang.designsystem.checkbox.HbCheckbox
import com.hanbang.designsystem.theme.Gray1
import com.hanbang.designsystem.theme.Gray5
import com.hanbang.designsystem.theme.LocalSattoTypography
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.util.clickableSingle
import com.hanbang.domain.extension.toggle

/**
 *
 * @author   JGeun
 * @created  2025/08/03
 */
@Composable
fun OnboardingAgreementBottomSheetDialog(
	onDismissRequest: () -> Unit,
	onConfirmAgreement: () -> Unit,
	openServiceAgreementInfo: () -> Unit,
	openPersonalInfoAgreementInfo: () -> Unit
) {
	var checkAllAgree by remember { mutableStateOf(false) }
	var checkServiceAgree by remember { mutableStateOf(false) }
	var checkPersonalInfoAgree by remember { mutableStateOf(false) }

	LaunchedEffect(checkAllAgree) {
		snapshotFlow { checkAllAgree }
			.collect { agree ->
				if (agree) {
					checkServiceAgree = true
					checkPersonalInfoAgree = true
				}
			}

	}
	HbBottomSheet(
		onDismissRequest = onDismissRequest,
		headerContents = {
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(vertical = 8.dp)) {
				Text(
					modifier = Modifier.weight(1f),
					text = "약관에 동의해주세요",
					style = LocalSattoTypography.current.body16Bold,
					color = Gray1
				)

				Icon(
					modifier = Modifier
						.size(24.dp)
						.clickableSingle(activeRippleEffect = false) { onDismissRequest() },
					painter = painterResource(R.drawable.ic_close),
					contentDescription = "close_icon"
				)
			}
		},
		contents = {
			Column(
				modifier = Modifier.fillMaxWidth()
			) {
				Column(modifier = Modifier.fillMaxWidth()) {
					Row(
						modifier = Modifier
							.fillMaxWidth()
							.padding(vertical = 10.dp),
						verticalAlignment = Alignment.CenterVertically
					) {
						HbCheckbox(
							text = "전체 동의",
							onClick = { checkAllAgree = checkAllAgree.toggle() },
							isActive = checkAllAgree,
							isEnabled = true,
							textStyle = SattoTheme.typography.body16Medium
						)
					}

					Row(
						modifier = Modifier
							.fillMaxWidth()
							.clickableSingle(activeRippleEffect = true) { openServiceAgreementInfo() }
							.padding(vertical = 10.dp),
						verticalAlignment = Alignment.CenterVertically
					) {
						HbCheckbox(
							modifier = Modifier.weight(1f),
							text = "(필수) 서비스 이용 약관",
							onClick = { checkServiceAgree = checkServiceAgree.toggle() },
							isActive = checkServiceAgree,
							isEnabled = true,
							textStyle = SattoTheme.typography.body16Medium
						)

						Icon(
							modifier = Modifier.size(16.dp),
							painter = painterResource(R.drawable.ic_chevron_right_16),
							contentDescription = "chevron_right",
							tint = Gray5
						)
					}

					Row(
						modifier = Modifier
							.fillMaxWidth()
							.clickableSingle(activeRippleEffect = true) { openPersonalInfoAgreementInfo() }
							.padding(vertical = 10.dp),
						verticalAlignment = Alignment.CenterVertically
					) {
						HbCheckbox(
							modifier = Modifier.weight(1f),
							text = "(필수) 개인정보 수집 및 이용",
							onClick = { checkPersonalInfoAgree = checkPersonalInfoAgree.toggle() },
							isActive = checkPersonalInfoAgree,
							isEnabled = true,
							textStyle = SattoTheme.typography.body16Medium
						)

						Icon(
							modifier = Modifier.size(16.dp),
							painter = painterResource(R.drawable.ic_chevron_right_16),
							contentDescription = "chevron_right",
							tint = Gray5
						)
					}
				}

				Spacer(Modifier.height(28.dp))

				HbBoxButton(
					text = "확인",
					onClick = {
						onConfirmAgreement()
						onDismissRequest()
					},
					colors = HbButtonColorType.primary,
					styles = HbButtonStyles.xLarge,
				)
			}
		}
	)
}