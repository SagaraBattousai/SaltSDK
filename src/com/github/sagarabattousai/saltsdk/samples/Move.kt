package com.github.sagarabattousai.saltsdk.samples

import com.aldebaran.qi.Application
import com.aldebaran.qi.Session
import com.aldebaran.qi.helper.proxies.ALMotion
import com.aldebaran.qi.helper.proxies.ALNavigation
import com.aldebaran.qi.helper.proxies.ALRobotPosture
import com.github.sagarabattousai.saltsdk.utils.PEPPER_URI
import kotlin.math.PI


class Move {

    companion object {
        @JvmStatic

        fun main(args: Array<String>) {
            // Create a new application
            val application = Application(args, PEPPER_URI)
            // Start your application
            application.start()
            // Create an ALTextToSpeech object and link it to your current session
            val move = Move()
            move.run(application.session())
        }
    }

    lateinit var navigation: ALNavigation
    lateinit var motion: ALMotion
    lateinit var posture: ALRobotPosture

    fun run(session: Session) {
        //Init
        navigation = ALNavigation(session)
        motion = ALMotion(session)
        posture = ALRobotPosture(session)


        //Wake up robot
        motion.wakeUp()

        //Send robot to Stand Init
        posture.goToPosture("StandInit", 0.5f)

        //Scanning the environement.
        navigation.startFreeZoneUpdate()
        motion.moveTo(0.0f, 0.0f, (2 * PI).toFloat())
        val desiredRadius = 0.6f
        val displacementConstraint = 0.5f
        val result = navigation.findFreeZone(desiredRadius, displacementConstraint) as List<Int>
        val errorCode = result[0]
        if (errorCode != 1) {
            println("Hmmm")
        }
        else {
            println( "Problem during the update of the free zone.")
        }
    }
}