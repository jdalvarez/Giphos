package com.example.giphos.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.giphos.application.AppConstants
import com.example.giphos.GiphAdapter
import com.example.giphos.GiphyItem
import com.example.giphos.databinding.FragmentGiphyBinding
import com.example.giphos.repository.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GiphyFragment : Fragment(){//, SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentGiphyBinding
    private lateinit var adapter: GiphAdapter
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
        //binding.searchView.setOnQueryTextListener(this)
        initRecyclerView()
        search()
        return binding.root
    }

    private fun initRecyclerView() {
        adapter = GiphAdapter()
        binding.rvMain.layoutManager = GridLayoutManager(mContext,2)
        binding.rvMain.adapter = adapter
    }

    //Obtengo mi instancia de retrofit
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun search(){
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val response = getRetrofit().create(ApiService::class.java).getSearchGiphy( query = "a")
                if (response.isSuccessful) {
                    val giphyResponse = response.body()
                    val giphys: List<GiphyItem> = giphyResponse?.data ?: emptyList()

                    CoroutineScope(Dispatchers.Main).launch {
                        adapter.setData(giphys)
                    }
                }
            } catch (e:Exception){
                Log.d("exception","${e.message}")
            }




        }
    }

 //   override fun onQueryTextSubmit(query: String?): Boolean {
   //     if (!query.isNullOrEmpty()){
     //       search(query.toLowerCase())
  //      }
   //    return true
    //}

   // override fun onQueryTextChange(newText: String?): Boolean {
   //     return true
   // }

}