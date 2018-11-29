package com.green.tictac

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class SoundService: Service() {

    var player : MediaPlayer ?= null

    override fun onBind(intent: Intent?): IBinder? {


        return null
    }

    override fun onCreate() {
        super.onCreate()

        player = MediaPlayer.create(this,R.raw.music)
        player?.start()
        player?.setLooping(true)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)

        player?.start()
        return Service.START_NOT_STICKY

    }

    override fun onDestroy() {
        super.onDestroy()

        player?.stop()
        player?.release()
        stopSelf()
        super.onDestroy()
    }


}