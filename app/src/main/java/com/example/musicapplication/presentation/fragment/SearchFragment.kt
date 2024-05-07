package com.example.musicapplication.presentation.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.musicapplication.databinding.FragmentSearchBinding
import com.example.musicapplication.presentation.base.BaseFragment


class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSearchBinding.inflate(layoutInflater, container, false)
}