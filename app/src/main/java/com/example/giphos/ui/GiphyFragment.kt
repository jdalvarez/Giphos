package com.example.giphos.ui

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
import androidx.recyclerview.widget.GridLayoutManager
import com.example.giphos.GiphyItem
import com.example.giphos.core.Resource
import com.example.giphos.data.remote.GiphyDataSource
import com.example.giphos.databinding.FragmentGiphyBinding
import com.example.giphos.presentation.GiphosViewModel
import com.example.giphos.presentation.GiphyViewModelFactory
import com.example.giphos.repository.GiphyRepositoryImpl
import com.example.giphos.repository.RetrofiClient
import com.example.giphos.ui.adapters.GiphyAdapter

class GiphyFragment : Fragment(), SearchView.OnQueryTextListener{
    private val viewModel by viewModels<GiphosViewModel> {
        GiphyViewModelFactory(
            GiphyRepositoryImpl(
                GiphyDataSource(RetrofiClient.apiservice)
            )
        )
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
        binding.searchView.setOnQueryTextListener(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        runSearch()
    }

    private fun initRecycler() {
        binding.rvMain.layoutManager = GridLayoutManager(mContext, 2)
        adapter = GiphyAdapter(::shareGiphy, ::addFavorites)
        binding.rvMain.adapter = adapter
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        runSearch(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText.isNullOrEmpty()){
            runSearch()
        }
        return true
    }

    private fun hideKeyboard() {
        val imm = mContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.searchView.windowToken, 0)
    }

    private fun runSearch(query: String? = null) {
        viewModel.fetchSearchGiphy(query).observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val giphyResponse = it.data.body()
                    val giphys: List<GiphyItem> = giphyResponse?.data ?: emptyList()
                    adapter.setData(giphys)

                }

                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context,"CODE: ${it.data.code()}, MSG: ${it.data.raw()}",Toast.LENGTH_SHORT).show()
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

    private fun addFavorites(giphyUrl: String): Boolean {
        Toast.makeText(mContext,"PUTO: $giphyUrl ", Toast.LENGTH_LONG).show()
        return true
    }

}

