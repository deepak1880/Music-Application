package com.example.musicapplication.presentation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicapplication.databinding.SongListItemBinding
import com.example.musicapplication.domain.modal.Song
import com.example.musicapplication.domain.modal.Song.Companion.SongDiffCallback
import com.example.musicapplication.domain.service.MediaService

class SongListAdapter(private val onClick: (Song) -> Unit) :
    ListAdapter<Song, SongListAdapter.SongViewHolder>(SongDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding =
            SongListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SongViewHolder(binding)

    }

    inner class SongViewHolder(private val binding: SongListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var isPlaying = false

        fun bind(song: Song) {
            with(binding) {
                songNameTv.text = song.title
                singerNameTv.text = song.subtitle
                Glide.with(binding.root)
                    .load(song.imageUrl)
                    .into(binding.songIv)

                playIv.setOnClickListener {
                    if (!isPlaying) {
                        val intent = Intent(binding.root.context, MediaService::class.java)
                        intent.putExtra("audioUrl", song.songUrl)
                        binding.root.context.startService(intent)
                        isPlaying = true
                        playIv.visibility = View.GONE
                        pauseIv.visibility = View.VISIBLE
                    }
                }
                pauseIv.setOnClickListener {
                    if (isPlaying) {
                        val intent = Intent(binding.root.context, MediaService::class.java)
                        binding.root.context.stopService(intent)
                        isPlaying = false
                        playIv.visibility = View.VISIBLE
                        pauseIv.visibility = View.GONE
                    }
                }
                parentCard.setOnClickListener {
                    onClick.invoke(song)
                }
            }
        }
    }


    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}