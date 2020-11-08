package com.github.sagarabattousai.saltsdk.events

import com.aldebaran.qi.helper.EventCallback

typealias EventHandler<T> = (T) -> Unit

sealed class Event<T, R>(val eventName: String, open val handler: EventHandler<T>) {

    open fun convertToEventCallback(): EventCallback<R> {
        return EventCallback<R> { handler }
    }

    companion object {
        @JvmStatic
        fun touchConverter(handler: EventHandler<Boolean>): EventCallback<Float> {
            return EventCallback<Float> { input ->
                val arg: Boolean = input?.equals(1.0f) ?: false
                handler(arg)
            }
        }
    }
}
/* Cant test without access
class TouchChanged(override val handler: EventHandler<>):
    Event<, ALValue>() {

}
*/

class RightBumperPressed(override val handler: EventHandler<Boolean>):
    Event<Boolean, Float>("RightBumperPressed", handler) {

    override fun convertToEventCallback(): EventCallback<Float> = touchConverter(handler)

}

class LeftBumperPressed(override val handler: EventHandler<Boolean>):
    Event<Boolean, Float>("LeftBumperPressed", handler) {

    override fun convertToEventCallback(): EventCallback<Float> = touchConverter(handler)

}

class BackBumperPressed(override val handler: EventHandler<Boolean>):
    Event<Boolean, Float>("BackBumperPressed", handler) {

    override fun convertToEventCallback(): EventCallback<Float> = touchConverter(handler)

}

class FrontTactileTouched(override val handler: EventHandler<Boolean>):
    Event<Boolean, Float>("FrontTactilTouched", handler) {

    override fun convertToEventCallback(): EventCallback<Float> = touchConverter(handler)

}

class MiddleTactileTouched(override val handler: EventHandler<Boolean>):
    Event<Boolean, Float>("MiddleTactilTouched", handler) {

    override fun convertToEventCallback(): EventCallback<Float> = touchConverter(handler)

}

class RearTactileTouched(override val handler: EventHandler<Boolean>):
    Event<Boolean, Float>("RearTactilTouched", handler) {

    override fun convertToEventCallback(): EventCallback<Float> = touchConverter(handler)

}

class HandRightBackTouched(override val handler: EventHandler<Boolean>):
    Event<Boolean, Float>("HandRightBackTouched", handler) {

    override fun convertToEventCallback(): EventCallback<Float> = touchConverter(handler)

}

class HandRightLeftTouched(override val handler: EventHandler<Boolean>):
    Event<Boolean, Float>("HandRightLeftTouched", handler) {

    override fun convertToEventCallback(): EventCallback<Float> = touchConverter(handler)

}

class HandRightRightTouched(override val handler: EventHandler<Boolean>):
    Event<Boolean, Float>("HandRightRightTouched", handler) {

    override fun convertToEventCallback(): EventCallback<Float> = touchConverter(handler)

}

class HandLeftBackTouched(override val handler: EventHandler<Boolean>):
    Event<Boolean, Float>("HandLeftBackTouched", handler) {

    override fun convertToEventCallback(): EventCallback<Float> = touchConverter(handler)

}

class HandLeftLeftTouched(override val handler: EventHandler<Boolean>):
    Event<Boolean, Float>("HandLeftLeftTouched", handler) {

    override fun convertToEventCallback(): EventCallback<Float> = touchConverter(handler)

}

class HandLeftRightTouched(override val handler: EventHandler<Boolean>):
    Event<Boolean, Float>("HandLeftRightTouched", handler) {

    override fun convertToEventCallback(): EventCallback<Float> = touchConverter(handler)

}