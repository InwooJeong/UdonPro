package com.cookandroid.udonpro

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth


class newMypage: Fragment() {



    lateinit var tv_userEmail: TextView
    lateinit var btn_logout: Button


    lateinit var mAuth: FirebaseAuth
    lateinit var userEmail: String
    lateinit var userUid: String



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
         savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.mypage, container, false)
        return view
    }

    //내정보표시.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tv_userEmail = requireView().findViewById<EditText>(R.id.tv_userEmail2) as TextView
        val btn_logout = requireView().findViewById<Button>(R.id.btn_logout) as Button



        mAuth = FirebaseAuth.getInstance()
        mAuth!!.addAuthStateListener { firebaseAuth: FirebaseAuth? ->
            mAuth = FirebaseAuth.getInstance()
        }

        val currentUser = mAuth!!.currentUser
        userEmail = currentUser!!.email.toString()
        userUid = currentUser.uid
        tv_userEmail.setText(userEmail)

//로그아웃.
    btn_logout.setOnClickListener {
        val intent = Intent(getActivity(), loginform::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        mAuth.signOut()
    }
    }
}