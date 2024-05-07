package com.example.musicapplication.presentation.fragment

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.musicapplication.databinding.FragmentDetailsBinding
import com.example.musicapplication.domain.modal.Song
import com.example.musicapplication.domain.service.MediaService
import com.example.musicapplication.presentation.base.BaseFragment


class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {


    private var isPlaying = false
    private val TAG = "DetailsFragment"

    private lateinit var number: String


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDetailsBinding.inflate(inflater, container, false)

    override fun initialSetUp() {
        super.initialSetUp()

        binding.backPressIv.setOnClickListener {
            findNavController().popBackStack()
        }

        val bundle = arguments
        if (bundle != null) {
            val song = bundle.getSerializable("songs") as? Song
            if (song != null) {
                with(binding) {
                    titleTv.text = song.title
                    singerTv.text = song.subtitle
                    Glide.with(binding.root)
                        .load(song.imageUrl)
                        .into(binding.songIv)

                    playIv.setOnClickListener {
                        if (!isPlaying) {
                            val intent = Intent(binding.root.context, MediaService::class.java)
                            intent.putExtra("audioUrl", song.songUrl)
                            binding.root.context.startService(intent)
                            Log.e("DetailsFragment", song.songUrl)
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

                }

            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()

    }
}