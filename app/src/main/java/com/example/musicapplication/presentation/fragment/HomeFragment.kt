package com.example.musicapplication.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapplication.R
import com.example.musicapplication.databinding.FragmentHomeBinding
import com.example.musicapplication.domain.modal.Song
import com.example.musicapplication.presentation.adapter.SongListAdapter
import com.example.musicapplication.presentation.base.BaseFragment
import com.google.firebase.firestore.FirebaseFirestore


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var songListAdapter: SongListAdapter? = null

    private val TAG = "HomeFragment"

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun initialSetUp() {
        super.initialSetUp()

        binding.songRv.layoutManager = LinearLayoutManager(context)
        songListAdapter = SongListAdapter(onClick = {
            val bundle = Bundle()
            bundle.putSerializable("songs", it)
            findNavController().navigate(
                R.id.navigateFromHomeToDetailsFragment,
                bundle
            )
        })
        binding.songRv.adapter = songListAdapter
        binding.progressBar.visibility = View.VISIBLE

        db.collection("songs")
            .get()
            .addOnCompleteListener { task ->
                val songList = mutableListOf<Song>()
                if (task.isSuccessful) {
                    for (document in task.result) {
                        val song = document.toObject(Song::class.java)
                        songList.add(song)

                        Log.e(TAG, songList.toString())
                    }
                    songListAdapter!!.submitList(songList)
                } else {
                    Log.w(TAG, "Error getting documents.", task.exception)
                }
                binding.progressBar.visibility = View.GONE
            }
    }

}
