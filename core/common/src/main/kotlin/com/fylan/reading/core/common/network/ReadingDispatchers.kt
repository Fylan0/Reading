package com.fylan.reading.core.common.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val niaDispatcher: ReadingDispatchers)

enum class ReadingDispatchers {
    Default,
    IO,
}
