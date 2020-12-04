package com.github.sagarabattousai.saltsdk.proxies

import com.aldebaran.qi.Session
import com.aldebaran.qi.helper.proxies.ALMemory
import com.github.sagarabattousai.saltsdk.events.Event


//Cant extend (cant Workout yet why) must have a
open class SaltMemory(private val session: Session) {

    private var almemory: ALMemory = ALMemory(session);

    fun subscribeToEvent(event: Event<*, *>): Long =
        almemory.subscribeToEvent(event.eventName, event.convertToEventCallback())

    fun unsubscribeToEvent(subscriptionID: Long): Unit = almemory.unsubscribeToEvent(subscriptionID)
}