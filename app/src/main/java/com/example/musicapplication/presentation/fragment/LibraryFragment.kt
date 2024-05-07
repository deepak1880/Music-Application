package com.example.musicapplication.presentation.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.musicapplication.databinding.FragmentLibraryBinding
import com.example.musicapplication.presentation.base.BaseFragment

class LibraryFragment : BaseFragment<FragmentLibraryBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLibraryBinding.inflate(layoutInflater, container, false)

}
