package com.github.sagarabattousai.saltsdk.samples

import com.aldebaran.qi.Application
import com.aldebaran.qi.Session
import com.aldebaran.qi.helper.proxies.ALFaceCharacteristics
import com.aldebaran.qi.helper.proxies.ALFaceDetection
import com.aldebaran.qi.helper.proxies.ALLeds
import com.aldebaran.qi.helper.proxies.ALTextToSpeech
import com.github.sagarabattousai.saltsdk.events.FrontTactileTouched
import com.github.sagarabattousai.saltsdk.events.HandRightBackTouched
import com.github.sagarabattousai.saltsdk.events.RearTactileTouched
import com.github.sagarabattousai.saltsdk.proxies.SaltMemory
import com.github.sagarabattousai.saltsdk.utils.PEPPER_URI


//TODO: Test!
class ReactToEvents {
    lateinit var memory: SaltMemory
    lateinit var tts: ALTextToSpeech
    lateinit var fc: ALFaceCharacteristics
    lateinit var fd: ALFaceDetection
    lateinit var leds: ALLeds


    var frontTactilSubscriptionId: Long = 0


    fun run(session: Session) {
        memory = SaltMemory(session)
        tts = ALTextToSpeech(session)
        fd = ALFaceDetection(session)
        fc = ALFaceCharacteristics(session)
        leds = ALLeds(session)

        frontTactilSubscriptionId = 0

        // Subscribe to FrontTactilTouched event,
        // create an EventCallback expecting a Float.
        frontTactilSubscriptionId = memory.subscribeToEvent(
            FrontTactileTouched { touched ->
                if (touched) {
                    tts.say("That's my head!!")
                    println(leds.listLEDs())
                    leds.on("AllGreenLEDS")
                    //fd.learnFace("James")
                    //tts.say("I have Learnt your face")
                    //(fd.learnedFacesList)
                   // fc.analyzeFaceCharacteristics(1)

                }
            })

        // Subscribe to RearTactilTouched event,
        // create an EventCallback expecting a Float.
        memory.subscribeToEvent(
            RearTactileTouched { touched ->

                if (touched) {
                    if (frontTactilSubscriptionId > 0) {
                        tts.say("I'll no longer say ouch")
                        // Unsubscribing from FrontTactilTouched event
                        memory.unsubscribeToEvent(frontTactilSubscriptionId)
                    }
                }
            })
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            // Create a new application
            val application = Application(args, PEPPER_URI)
            // Start your application
            application.start()
            println("Successfully connected to the robot")
            // Subscribe to selected ALMemory events
            val reactor = ReactToEvents()
            reactor.run(application.session())
            println("Subscribed to FrontTactilTouched and RearTactilTouched.")
            // Run your application
            application.run()
        }
    }
}