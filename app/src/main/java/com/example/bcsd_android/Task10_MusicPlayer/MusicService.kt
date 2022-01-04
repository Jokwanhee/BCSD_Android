package com.example.bcsd_android.Task10_MusicPlayer

import android.app.*
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import com.example.bcsd_android.R
import java.util.*

class MusicService() : Service(), MediaPlayer.OnPreparedListener {
    private val CHANNEL_ID = "ForegroundChannel"
    val NOTI_ID = 1
    val binder = MyBinder()
    var mediaPlayer: MediaPlayer? = null

    inner class MyBinder : Binder() {
        fun getService(): MusicService {
            return this@MusicService
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        val notificationTitle = intent?.getStringExtra("title")
        val notificationArtist = intent?.getStringExtra("artist")
        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(notificationTitle)
            .setContentText(notificationArtist)
            .setSmallIcon(R.drawable.ic_baseline_music_note_24)
            .setContentIntent(
                PendingIntent.getActivity(
                    this, 0,
                    Intent(this, MusicActivity::class.java), 0
                )
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        startForeground(NOTI_ID, notification)

        if (mediaPlayer != null) {
            mediaPlayer?.release()
            mediaPlayer = null
        }
        val serviceUri = intent?.getStringExtra("uri")!!.toUri()

        mediaPlayer = MediaPlayer()
        mediaPlayer?.apply {
            setOnPreparedListener(this@MusicService)
            setDataSource(this@MusicService, serviceUri)
            prepareAsync()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mp?.start()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager.createNotificationChannel(serviceChannel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer != null) {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }
}