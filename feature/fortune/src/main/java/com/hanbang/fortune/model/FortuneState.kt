package com.hanbang.fortune.model

import androidx.compose.runtime.Stable
import com.hanbang.domain.model.PillarDetail
import com.hanbang.domain.model.PillarElement

/**
 *
 * @author   JGeun
 * @created  2025/08/16
 */
@Stable
data class FortuneState(
	val isLoading: Boolean = false,
	val todayDate: String = "",
	val fortuneScore: Int = 0,
	val fortuneComment: String = "",
	val userName: String = "",
	val userDateOfBirth: String = "",
	val userBirthTime: String = "",
	val timePillarDetail: PillarDetailUiModel = PillarDetailUiModel(),
	val dayPillarDetail: PillarDetailUiModel = PillarDetailUiModel(),
	val monthPillarDetail: PillarDetailUiModel = PillarDetailUiModel(),
	val yearPillarDetail: PillarDetailUiModel = PillarDetailUiModel(),
	val strongElement: String = "",
	val weakElement: String = "",
	val elementDescription: String = ""
)

@Stable
data class PillarDetailUiModel(
	val stem: PillarElement = PillarElement.NONE,
	val branch: PillarElement = PillarElement.NONE,
	val stemTenGod: String = "",
	val branchTenGod: String = "",
	val stemElement: String = "",
	val branchElement: String = ""
)

fun PillarDetail.toUiModel() = PillarDetailUiModel(
	stem = stem,
	branch = branch,
	stemTenGod = stemTenGod,
	branchTenGod = branchTenGod,
)
