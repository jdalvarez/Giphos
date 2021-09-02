package com.example.giphos.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.example.giphos.GiphyEntity
import com.example.giphos.GiphyItem
import com.example.giphos.R
import com.example.giphos.application.MainApplication
import com.example.giphos.data.model.Giph
import com.example.giphos.databinding.FragmentGiphyBinding
import com.example.giphos.presentation.FavoritesViewModel
import com.example.giphos.presentation.GiphyViewModelFactory
import com.example.giphos.ui.adapters.GiphyAdapter

class FavoriteFragment : Fragment() {
    lateinit var binding: FragmentGiphyBinding
    private val viewModel by viewModels<FavoritesViewModel> {
        GiphyViewModelFactory((requireActivity().application as MainApplication).repository)
    }

    private lateinit var adapter: GiphyAdapter
    lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGiphyBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        viewModel.favoriteGiphyList.observe(viewLifecycleOwner){
           adapter.setData(it)
        }
        val navController = findNavController()
        binding.favoritesBtn.visibility = View.GONE
        val homebtn = binding.homeBtn
        homebtn.visibility = View.VISIBLE
        homebtn.setOnClickListener {
            navController.navigate(R.id.giphyFragment)
        }
    }

    private fun initRecycler() {
        binding.rvMain.layoutManager = GridLayoutManager(mContext, 2)
        adapter = GiphyAdapter(::shareGiphy, ::deleteFromFavorites, ::likeAnimation)
        binding.rvMain.adapter = adapter
    }

    private fun shareGiphy(giphyUrl: String) {
        val intent = Intent()
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"Ilove this Gifs: \n $giphyUrl")
        intent.action = Intent.ACTION_SEND
        val chooseIntent = Intent.createChooser(intent,"Choose an Option:")
        startActivity(chooseIntent)
    }

    private fun deleteFromFavorites(giphy: Giph): Boolean {
        viewModel.deleteFromFavorites(giphy)
        return true
    }

    private fun likeAnimation(imageView: LottieAnimationView, animation: Int):Boolean{
        return true
    }


}