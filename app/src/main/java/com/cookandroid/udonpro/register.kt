package com.cookandroid.udonpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import android.widget.EditText
import android.widget.Button
import android.os.Bundle
import android.view.View
import com.cookandroid.udonpro.R
import com.google.firebase.database.FirebaseDatabase
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.cookandroid.udonpro.UserAccount
import android.widget.Toast
import com.google.firebase.firestore.auth.User

class register : AppCompatActivity() {
    lateinit var mFirebaseAuth : FirebaseAuth //파이어베이스 인증처리


    lateinit var mDatabaseRef : DatabaseReference// 실시간 데이터베이스

    lateinit var mEtEmail: EditText
    lateinit var mEtPw: EditText
    lateinit var mEtNumber : EditText //회원가입 입력 필드 , (주소 나중에처리할것)
    lateinit var mBtnRegister: Button
    lateinit var mBtnMain : Button //회원가입 입력버튼



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registerform)



        mFirebaseAuth = FirebaseAuth.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("UdonProject")
        mEtEmail = findViewById(R.id.et_email)
        mEtPw = findViewById(R.id.et_pw)
        mEtNumber = findViewById(R.id.et_num)
        mBtnRegister = findViewById<Button>(R.id.btn_register)


        mBtnRegister.setOnClickListener(View.OnClickListener {
            //회원가입 처리 시작
            val strEmail = mEtEmail.text.toString() //값을 가저와서, 문자열로 변환
            val strpw = mEtPw.text.toString()
            //String inNum = mEtNumber.getText().toString();


            val mBtnMain = findViewById<Button>(R.id.btn_main)
            mBtnMain.setOnClickListener {
                intent = Intent(this@register, MainActivity::class.java)
                startActivity(intent)
            }


            //firebaseAutho 진행
            mFirebaseAuth!!.createUserWithEmailAndPassword(strEmail, strpw)
                .addOnCompleteListener(this@register) { task ->
                    //가입이 이루어진뒤 처리

                    if (task.isSuccessful) {
                        val firebaseUser = mFirebaseAuth!!.currentUser
                        val account = UserAccount()

                        account.idToken = firebaseUser!!.uid //파이어베이스에서 가저옴 (고유)

                        val fireEmail = firebaseUser.email
                        account.eMail = fireEmail.toString()

//                      val firePassword = firebaseUser.password
//                        account.password = firePassword.toString()

                        //account.eMail(firebaseUser.email)
                        account.password = strpw //사용자가 입력한곳에서 가저옴
                        //account.setNumber(firebaseUser.getPhoneNumber());

                        //saveUserToFirebaseDatabase()


                        // setValue : database에서 insert 해준
                        mDatabaseRef.child("UserAccount").child(firebaseUser.uid)
                            .setValue(account)



                        intent = Intent(this@register, MainActivity::class.java)
                        startActivity(intent)

                        Toast.makeText(this@register, "회원가입에 성공하셨습니다", Toast.LENGTH_SHORT).show()


                    } else {
                        Toast.makeText(this@register, "회원가입에 실패하셨습니다", Toast.LENGTH_SHORT).show()
                    }
                }

        })

    }

//    private fun saveUserToFirebaseDatabase() {
//        val uid = FirebaseAuth.getInstance().uid
//        val ref = FirebaseDatabase.getInstance().getReference("/UserAccount/$uid")
//
//        val user = User(uid, mEtEmail.text.toString())
//    }
}