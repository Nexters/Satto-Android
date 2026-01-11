package com.hanbang.map.search.model

/**
 * @author   JGeun
 * @created  2026/01/11
 */
enum class MapSearchUiExceptionType(
	val title: String,
	val message: String
) {
	NONE(
		title = "",
		message = ""
	),
	EMPTY_RESULT(
		title = "천하에 검색 결과가 없소",
		message = "정확한 지명(구/동) 혹은\n상호명을 입력해 보시게"
	),
	NETWORK_ERROR(
		title = "오프라인 상태라네",
		message = "인터넷 연결을 확인해 주시게"
	),
	UNKNOWN_ERROR(
		title = "알 수 없는 오류가 발생했네",
		message = "다시 한 번 시도해 주시게"
	)
}