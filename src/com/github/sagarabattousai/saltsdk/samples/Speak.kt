@file:JvmName("Speak")
package com.github.sagarabattousai.saltsdk.samples

import com.aldebaran.qi.Application
import com.aldebaran.qi.helper.proxies.ALTextToSpeech
import com.github.sagarabattousai.saltsdk.utils.PEPPER_URI

fun main(args: Array<String>) {
    // Create a new application
    val application = Application(args, PEPPER_URI)
    // Start your application
    application.start()
    // Create an ALTextToSpeech object and link it to your current session
    val tts = ALTextToSpeech(application.session())
    // Make your robot say something
    tts.say("Hello")
}