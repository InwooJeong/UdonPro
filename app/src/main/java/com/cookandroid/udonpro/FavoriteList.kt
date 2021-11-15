package com.cookandroid.udonpro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavoriteList : Fragment() {

    lateinit var fAdapter: FavoriteListAdapter
    val viewModel by lazy { ViewModelProvider(this).get(ViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view: View = inflater.inflate(R.layout.favoritebooklist, container, false)


        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)

        fAdapter = FavoriteListAdapter(requireContext())
        recyclerView.adapter = fAdapter

        observerData()
        return view
    }


    fun observerData(){
        viewModel.fetchDataFavorite().observe(viewLifecycleOwner, Observer {
            fAdapter.setListData(it)
            fAdapter.notifyDataSetChanged()
        })
    }

}