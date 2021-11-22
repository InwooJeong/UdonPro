package com.cookandroid.udonpro

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class FavoriteList : Fragment() {

    lateinit var fAdapter: FavoriteListAdapter
    val viewModel by lazy { ViewModelProvider(this).get(ViewModel::class.java) }
    lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mAuth = FirebaseAuth.getInstance()
        mAuth!!.addAuthStateListener { firebaseAuth: FirebaseAuth? ->
            mAuth = FirebaseAuth.getInstance()
        }
        val currentUser = mAuth!!.currentUser

        if (currentUser != null) {
            var view: View = inflater.inflate(R.layout.favoritebooklist, container, false)


            val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

            fAdapter = FavoriteListAdapter(requireContext())
            recyclerView.adapter = fAdapter

            observerData()
            return view
        } else {
            var dlglogin = AlertDialog.Builder(requireContext())
            dlglogin.setTitle("로그인")
            dlglogin.setMessage("로그인 하시겠습니까?")
            dlglogin.setPositiveButton("예") { dialog, which ->
                val intent = Intent(context, loginform::class.java)
                startActivity(intent)
            }
            dlglogin.setNegativeButton("아니요") { dialog, which ->
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            }
            dlglogin.show()
        }
        return null
    }


    fun observerData() {
        viewModel.fetchDataFavorite().observe(viewLifecycleOwner, Observer {
            fAdapter.setListData(it)
            fAdapter.notifyDataSetChanged()
        })
    }

}