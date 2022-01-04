package com.example.bcsd_android.Task10_MusicPlayer

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bcsd_android.R
import java.text.SimpleDateFormat

class MusicRecyclerAdapter() : RecyclerView.Adapter<MusicRecyclerAdapter.ViewHolder>() {
    val musicList = mutableListOf<MusicData>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var musicUri: Uri? = null
        val imageAlbum = itemView.findViewById<ImageView>(R.id.music_image)
        val textTitle = itemView.findViewById<TextView>(R.id.music_title)
        val textArtist = itemView.findViewById<TextView>(R.id.music_artist)
        val textDuration = itemView.findViewById<TextView>(R.id.music_duration)

        fun setMusic(music: MusicData) {
            musicUri = music.getMusicUri()
            imageAlbum.setImageResource(R.drawable.ic_baseline_music_note_24)
            // setImageUri(music.getAlbumUri()) => 해당 음악파일 id 불러와 앨범 그림 가져옴
            textTitle.text = music.title
            textArtist.text = music.artist
            val duration = SimpleDateFormat("mm:ss")
            textDuration.text = duration.format(music.duration)
        }
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    private lateinit var itemClickListener: OnItemClickListener

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.music_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val music = musicList.get(position)
        holder.setMusic(music)
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int {
        return musicList.size
    }
}


