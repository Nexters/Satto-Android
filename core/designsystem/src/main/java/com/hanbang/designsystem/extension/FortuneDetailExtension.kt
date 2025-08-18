package com.hanbang.designsystem.extension

import com.hanbang.designsystem.R
import com.hanbang.domain.model.FortuneDetailType

/**
 *
 * @author   JGeun
 * @created  2025/08/19
 */
fun FortuneDetailType.getImageRes() = when(this) {
	FortuneDetailType.MONEY -> R.drawable.img_fortune_money
	FortuneDetailType.JOB -> R.drawable.img_fortune_job
	FortuneDetailType.LOVE -> R.drawable.img_fortune_love
	FortuneDetailType.NONE -> R.drawable.img_satto_update_wait
}