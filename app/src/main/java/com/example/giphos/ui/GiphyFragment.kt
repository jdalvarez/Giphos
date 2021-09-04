package com.example.giphos.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.example.giphos.GiphyItem
import com.example.giphos.R
import com.example.giphos.application.MainApplication
import com.example.giphos.core.Resource
import com.example.giphos.data.model.Giph
import com.example.giphos.databinding.FragmentGiphyBinding
import com.example.giphos.presentation.GiphosViewModel
import com.example.giphos.presentation.GiphyViewModelFactory
import com.example.giphos.ui.adapters.GiphyAdapter
import com.google.android.material.snackbar.Snackbar

class GiphyFragment : Fragment(), SearchView.OnQueryTextListener{

    private val viewModel by viewModels<GiphosViewModel> {
        GiphyViewModelFactory((requireActivity().application as MainApplication).repository)
    }

    private lateinit var binding: FragmentGiphyBinding
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
        binding = FragmentGiphyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.searchView.setOnQueryTextListener(this)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
            binding.favoritesBtn.setOnClickListener{
                navController.navigate(R.id.favoriteFragment)
            }
        initRecycler()
        setupObservers()
        if(savedInstanceState == null){
            viewModel.fetch()
        }
    }

    private fun initRecycler() {
        binding.rvMain.layoutManager = GridLayoutManager(mContext, 2)
        adapter = GiphyAdapter(::shareGiphy, ::addFavorites, ::likeAnimation)
        binding.rvMain.adapter = adapter
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModel.fetch(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText.isNullOrEmpty()){
            viewModel.fetch()
        }
        return true
    }

    private fun hideKeyboard() {
        val imm = mContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.searchView.windowToken, 0)
    }

    private fun setupObservers() {
            viewModel.responseLiveData.observe(viewLifecycleOwner, Observer {
                when (it) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        adapter.setData(it.data)
                    }

                    is Resource.Failure -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context,"CODE: ${it.data}, MSG: ${it.data}",Toast.LENGTH_SHORT).show()
                    }
                }
                hideKeyboard()
            })       //esto retorna un Livedata y necesitamos observar
        }


    private fun shareGiphy(giphyUrl: String) {
       val intent = Intent()
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"Ilove this Gifs: \n $giphyUrl")
        intent.action = Intent.ACTION_SEND
        val chooseIntent = Intent.createChooser(intent,"Choose an Option:")
        startActivity(chooseIntent)
    }

    private fun addFavorites(giphy: Giph): Boolean {
        viewModel.addToFavorites(giphy)
        return true
    }


    private fun likeAnimation(imageView: LottieAnimationView, animation: Int):Boolean{
        imageView.visibility = View.VISIBLE
        imageView.setAnimation(animation)
        imageView.playAnimation()
        imageView.addAnimatorListener(object :AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                    imageView.speed = -1f
                    imageView.visibility = View.GONE
                }
        })
        return true
    }

}

