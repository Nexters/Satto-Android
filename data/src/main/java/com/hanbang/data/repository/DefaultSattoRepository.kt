package com.hanbang.data.repository

import com.hanbang.data.datasource.UserRemoteDataSource
import com.hanbang.domain.repository.SattoRepository
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
class DefaultSattoRepository @Inject constructor(
	private val userRemoteDataSource: UserRemoteDataSource,
	private val userLocalDataSource: com.hanbang.data.datasource.UserLocalDataSource
) : SattoRepository {

}