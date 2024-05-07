package com.example.musicapplication.domain.modal

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

data class Song(
    val title: String,
    val subtitle: String,
    val imageUrl: String,
    val songUrl: String,
    val mediaId: Int
) : Serializable {
    constructor() : this("", "", "", "", 0)

    companion object {
        val SongDiffCallback = object : DiffUtil.ItemCallback<Song>() {
            override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
                return oldItem.mediaId == newItem.mediaId
            }

            override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
                return oldItem == newItem
            }
        }
    }
}


