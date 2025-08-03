package com.hanbang.navigation.navigator

import kotlinx.coroutines.channels.Channel

/**
 *
 * @author   JGeun
 * @created  2025/08/03
 */
internal interface InternalNavigator {

	val channel: Channel<InternalRoute>
}