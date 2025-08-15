package com.hanbang.data.datasource

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
interface UserLocalDataSource {

	suspend fun getUserId(): String

	suspend fun storeUserId(userId: String): String
}