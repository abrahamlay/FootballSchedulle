package com.abrahamlay.footballschedule.repo

import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class TestContextProvider : CoroutineContextProvider(){
    override val main: CoroutineContext =Unconfined
}