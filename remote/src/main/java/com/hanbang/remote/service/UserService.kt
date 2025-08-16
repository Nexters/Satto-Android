package com.hanbang.remote.service

import com.hanbang.remote.model.CreateLottoRecommendationResponse
import com.hanbang.remote.model.CreateUserResponse
import com.hanbang.remote.model.GetDailyFortuneDetailResponse
import com.hanbang.remote.model.GetDailyFortuneResponse
import com.hanbang.remote.model.GetLottoRecommendationResponse
import com.hanbang.remote.model.GetUserForPillarResponse
import com.hanbang.remote.model.GetUserResponse
import com.hanbang.remote.model.UpdateUserResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
interface UserService {

	@POST("/users")
	suspend fun createUser(@Body createUserBody: RequestBody) : CreateUserResponse

	@GET("/users/{user_id}")
	suspend fun getUser(
		@Path("user_id") userId: String
	) : GetUserResponse

	@PUT("/users/{user_id}")
	suspend fun updateUser(
		@Path("user_id") userId: String,
		@Body updateUserBody: RequestBody
	): UpdateUserResponse

	@GET("/users/{user_id}/four-pillar")
	suspend fun getUserFourPillar(
		@Path("user_id") userId: String
	): GetUserForPillarResponse

	@POST("/users/{user_id}/lotto-recommendation")
	suspend fun createLottoRecommendation(
		@Path("user_id") userId: String
	): CreateLottoRecommendationResponse

	@GET("/users/{user_id}/lotto-recommendation")
	suspend fun getLottoRecommendation(
		@Path("user_id") userId: String
	): GetLottoRecommendationResponse

	@GET("/users/{user_id}/daily-fortunes")
	suspend fun getDailyFortunes(
		@Path("user_id") userId: String,
		@Query("fortune_date") fortuneDate: String
	): GetDailyFortuneResponse

	@GET("/users/{user_id}/daily-fortune-details")
	suspend fun getDailyFortuneDetail(
		@Path("user_id") userId: String,
		@Query("fortune_date") fortuneDate: String
	): GetDailyFortuneDetailResponse
}