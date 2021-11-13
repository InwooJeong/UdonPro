package com.cookandroid.udonpro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import kotlinx.android.synthetic.main.mypage.*


class newMypage: Fragment() {



    lateinit var tv_userEmail: TextView
    lateinit var btn_logout: Button

    val alert: AlertDialog
        get() {
            TODO()
        }

    var confirmLogout = false
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tv_userEmail = requireView().findViewById<EditText>(R.id.tv_userEmail) as TextView
        val btn_logout = requireView().findViewById<Button>(R.id.btn_logout) as Button



        mAuth = FirebaseAuth.getInstance()
        mAuth!!.addAuthStateListener { firebaseAuth: FirebaseAuth? ->
            mAuth = FirebaseAuth.getInstance()
        }
        val currentUser = mAuth!!.currentUser
        userEmail = currentUser!!.email.toString()
        userUid = currentUser.uid
        tv_userEmail.setText(userEmail)


        btn_logout.setOnClickListener(View.OnClickListener { v: View -> onClick(v) })

        btn_goBoard.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("공지사항 입니다.")
                .setMessage("12월 12일 22:00 ~ 23:00에 ver.1.23 업데이트가 있을 예정입니다.")
                .setPositiveButton("확인") {
                        DialogInterface, i ->
                }.show()

        }
    }


    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_logout -> logout()
        }
    }

    fun logout() {

        val alt_builder = AlertDialog.Builder(requireActivity())
        alt_builder.setTitle("로그아웃 확인")
        alt_builder.setOnDismissListener {
            //dismiss되면서 로그아웃
            if (confirmLogout) {
                FirebaseAuth.getInstance().signOut()

                //로그아웃하면 로그인페이지로 이동.
                val  intent = Intent(getActivity(), loginform::class.java)
                startActivity(intent)


                //로그아웃이 제대로 됐으면 .
                Log.d("Logout", "로그아웃 성공")
            }
        }
        alt_builder.setMessage("로그아웃 하시겠습니까?")
            .setPositiveButton("계속") { dialog, which ->
                confirmLogout = true
                alert!!.dismiss()
                Toast.makeText(
                    getActivity(), "정상적으로 로그아웃 되었습니다",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}