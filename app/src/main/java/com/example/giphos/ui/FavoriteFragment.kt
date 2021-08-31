package com.example.giphos.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.giphos.databinding.FragmentGiphyBinding

class FavoriteFragment : Fragment() {
  lateinit var binding: FragmentGiphyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGiphyBinding.inflate(inflater,container,false)
        return binding.root
    }

}