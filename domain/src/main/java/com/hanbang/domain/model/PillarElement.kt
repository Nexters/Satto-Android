package com.hanbang.domain.model

/**
 *
 * @author   JGeun
 * @created  2025/08/16
 */
//甲(갑), 乙(을) , 寅(인), 卯(묘)→ 木(목)
//丙(병), 丁(정), 巳(사), 午(오)  → 火(화)
//戊(무), 己(기), 辰(진), 戌(술), 丑(축), 未(미) → 土(토)
//庚(경), 辛(신), 申(신), 酉(유)  → 金(금)
//壬(임), 癸(계), 亥(해), 子(자) → 水(수)
enum class PillarElement(
	val hanja: String,
	val korean: String
) {
	NONE("-", "-"),
	WOOD("木", "목"),
	FIRE("火", "화"),
	EARTH("土", "토"),
	METAL("金", "금"),
	WATER("水", "수");

	companion object {
		val hanjaSet = setOf(
			"甲", "乙", "寅", "卯", "木",
			"丙", "丁", "巳", "午", "火",
			"戊", "己", "辰",
			"戌", "丑", "未", "土",
			"庚", "辛", "申", "酉", "金",
			"壬", "癸", "亥", "子", "水"
		)
		val hanjaToElement = mapOf(
			// 木
			"甲" to WOOD, "乙" to WOOD, "寅" to WOOD, "卯" to WOOD, "木" to WOOD,
			// 火
			"丙" to FIRE, "丁" to FIRE, "巳" to FIRE, "午" to FIRE, "火" to FIRE,
			// 土
			"戊" to EARTH, "己" to EARTH, "辰" to EARTH, "土" to EARTH,
			"戌" to EARTH, "丑" to EARTH, "未" to EARTH,
			// 金
			"庚" to METAL, "辛" to METAL, "申" to METAL, "酉" to METAL, "金" to METAL,
			// 水
			"壬" to WATER, "癸" to WATER, "亥" to WATER, "子" to WATER, "水" to WATER
		)

		// 오행 → 간지 리스트
		val hanjaToPillarElementMap: Map<PillarElement, List<String>> =
			hanjaToElement.entries.groupBy({ it.value }, { it.key })

		// 오행 찾기 함수
		fun findElement(hanji: String): PillarElement? = hanjaToElement[hanji]

		// 해당 오행에 속하는 간지 리스트 반환
		fun getHanjiOf(element: PillarElement): List<String> = hanjaToPillarElementMap[element].orEmpty()

		fun parsePillarElementInString(str: String): PillarElement {
			val stemBranch = hanjaSet.find { pillar -> str.contains(pillar) } ?: return NONE
			return hanjaToElement[stemBranch] ?: NONE
		}
	}
}

