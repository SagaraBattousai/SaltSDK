package com.github.sagarabattousai.saltsdk.proxies

import com.aldebaran.qi.Session
import com.aldebaran.qi.helper.proxies.ALMemory
import com.github.sagarabattousai.saltsdk.events.Event

open class SaltMemory(private val session: Session) : ALMemory(session) {

    fun subscribeToEvent(event: Event<*, *>): Long =
        subscribeToEvent(event.eventName, event.convertToEventCallback())
}