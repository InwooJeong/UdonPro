package com.cookandroid.udonpro

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import android.widget.EditText
import android.os.Bundle
import com.cookandroid.udonpro.R
import com.google.firebase.database.FirebaseDatabase
import android.widget.Button
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import android.content.Intent
import com.cookandroid.udonpro.MainActivity
import android.widget.Toast
import com.cookandroid.udonpro.register

class loginform : AppCompatActivity() {
    lateinit var mFirebaseAuth : FirebaseAuth//파이어베이스 인증처리
    lateinit var mDatabaseRef : DatabaseReference// 실시간 데이터베이스

    lateinit var mEtEmail: EditText
    lateinit var mEtPw: EditText
    //lateinit val mEtNumber : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        //
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginform)
        mFirebaseAuth = FirebaseAuth.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("UdonProject")
        mEtEmail = findViewById(R.id.et_email1)
        mEtPw = findViewById(R.id.et_pwd)


        val btn_login = findViewById<Button>(R.id.btn_login)
        btn_login.setOnClickListener {
            // 로그인 요청
            val strEmail = mEtEmail.getText().toString() //값을 가저와서, 문자열로 변환
            val strpw = mEtPw.getText().toString()
            mFirebaseAuth!!.signInWithEmailAndPassword(strEmail, strpw)
                .addOnCompleteListener(this@loginform) { task ->
                    if (task.isSuccessful) {
                        //로그인 성공
                        val intent = Intent(this@loginform, MainActivity::class.java)
                        startActivity(intent)
                        //finish() // 현재 로그인 엑티비티 제거
                    } else {
                        Toast.makeText(this@loginform, "로그인 실패 ..", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        val btn_regi = findViewById<Button>(R.id.btn_regi)
        btn_regi.setOnClickListener { //회원가입 화면이동한다
            val intent = Intent(this@loginform, register::class.java)
            startActivity(intent)
        }
    }
}