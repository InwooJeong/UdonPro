package com.cookandroid.udonpro

import android.app.PendingIntent.getActivity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.internal.ContextUtils.getActivity

class MainForm_Request : Fragment() {


    private lateinit var mAdapter: MainFormListAdapter
    val viewModel by lazy { ViewModelProvider(this).get(ViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState) }
    override fun onAttach(context: Context) { super.onAttach(context) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.mainformrecyclerview, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(context,2)

        mAdapter = MainFormListAdapter(requireContext())
        recyclerView.adapter = mAdapter

        observerData()
        return view
    }


    private fun observerData(){
        viewModel.fetchDataRequest().observe(viewLifecycleOwner, Observer {
            mAdapter.setListData(it)
            mAdapter.notifyDataSetChanged()
        })
    }
}