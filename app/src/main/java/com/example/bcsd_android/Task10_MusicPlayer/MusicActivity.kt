package com.example.bcsd_android.Task10_MusicPlayer

import android.annotation.SuppressLint
import android.content.*
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.IBinder
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bcsd_android.R
import com.example.bcsd_android.databinding.ActivityMusicBinding
import java.text.SimpleDateFormat

class MusicActivity : AppCompatActivity() {
    private var mBinding: ActivityMusicBinding? = null
    private val binding get() = mBinding!!
    var musicService: MusicService? = null
    var isService = false
    val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MusicService.MyBinder
            musicService = binder.getService()
            isService = true
            Log.d("BoundService", "Bind Service 연결완료")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isService = false
        }
    }

    val permission = android.Manifest.permission.READ_EXTERNAL_STORAGE
    val REQ_READ = 99
    val adapter = MusicRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
        mBinding = ActivityMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (isPermitted()) {
            startProcess()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(permission), REQ_READ)
        }
    }

    override fun onStart() {
        super.onStart()
        adapter.setItemClickListener(object : MusicRecyclerAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                val intent = Intent(v.context, MusicService::class.java)
                // intent.action = MyService.ACTION_PLAY
                intent.putExtra("uri", getMusicList().get(position).getMusicUri().toString())
                intent.putExtra("title", getMusicList().get(position).title)
                intent.putExtra("artist", getMusicList().get(position).artist)
                // startService(intent)
                bindService(intent, connection, Context.BIND_AUTO_CREATE)
                ContextCompat.startForegroundService(this@MusicActivity, intent)
                binding.bottomImage.setImageURI(getMusicList().get(position).getAlbumUri())
                binding.bottomTitle.text = adapter.musicList[position].title
                binding.bottomArtist.text = adapter.musicList[position].artist
                binding.bottomDuration.text =
                    SimpleDateFormat("mm:ss").format(adapter.musicList[position].duration)
            }
        })
    }

    fun startProcess() {
        adapter.musicList.addAll(getMusicList())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }


    fun getMusicList(): List<MusicData> {
        val musicList = mutableListOf<MusicData>()
        val musicListUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val proj = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.DURATION
        )
        val cursor = contentResolver.query(musicListUri, proj, null, null, null)
        while (cursor?.moveToNext() ?: false) {
            val id = cursor!!.getString(0)
            val title = cursor!!.getString(1)
            val artist = cursor!!.getString(2)
            val albumId = cursor!!.getString(3)
            val duration = cursor!!.getLong(4)

            val music = MusicData(id, title, artist, albumId, duration)
            musicList.add(music)
        }
        return musicList
    }

    fun isPermitted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQ_READ) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startProcess()
            } else {
                Toast.makeText(this, "권한 요청을 승인해야지만 앱을 실행할 수 있습니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }
}
