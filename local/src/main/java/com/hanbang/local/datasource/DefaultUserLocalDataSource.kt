package com.hanbang.local.datasource

import com.hanbang.data.datasource.UserLocalDataSource
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
class DefaultUserLocalDataSource @Inject constructor(

) : UserLocalDataSource {

	override suspend fun getDeviceId(): String {
		return "1234"
	}
}