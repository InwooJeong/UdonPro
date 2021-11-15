package com.cookandroid.udonpro
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.auth.FirebaseAuth

class Uploader : Fragment() {

    lateinit var tv_userEmail2 : TextView

    lateinit var mAuth: FirebaseAuth
    lateinit var userEmail:String
    lateinit var userUid : String

    private lateinit var mAdapter: UploaderListAdapter
    val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mAdapter = UploaderListAdapter(requireContext())
        val view = inflater.inflate(R.layout.uploader, container, false)

        val viewPager : ViewPager2 = view.findViewById(R.id.viewPager)
        viewPager.adapter = mAdapter
       GridLayoutManager(context,2)
//        val recyclerView : RecyclerView = view.findViewById(R.id.viewPager)
//        recyclerView.layoutManager = GridLayoutManager(context, 2)
//        recyclerView.adapter = mAdapter

        observerData()

        val tv_userEmail2 : TextView = view.findViewById(R.id.tv_userEmail2)

        mAuth= FirebaseAuth.getInstance()
//        mAuth!!.addAuthStateListener { FirebaseAuth ->
//            mAuth = FirebaseAuth.g
//        }
        val currentUser =mAuth!!.currentUser
        userEmail = currentUser!!.email.toString()
        userUid = currentUser.uid
        tv_userEmail2.setText(userEmail)

        return view
    }

    private fun observerData() {
        viewModel.fetchData().observe(viewLifecycleOwner, Observer {
            mAdapter.setListData(it)
            mAdapter.notifyDataSetChanged()
        })

    }
}