package com.cookandroid.udonpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.View
import android.widget.*
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.registerform.*

class register : AppCompatActivity() {
    lateinit var mFirebaseAuth : FirebaseAuth //파이어베이스 인증처리
    lateinit var mDatabaseRef : DatabaseReference// 실시간 데이터베이스
    val account = UserAccount()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registerform)



        mFirebaseAuth = FirebaseAuth.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("UdonProject")


        var addressItem = arrayOf("사상구", "부산진구")
        val myAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, addressItem)
        spinner_address.adapter = myAdapter
        spinner_address.prompt="주소를 선택하세요"

        et_num.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        btn_main.setOnClickListener {
            intent = Intent(this@register, MainActivity::class.java)
            startActivity(intent)
        }


        btn_register.setOnClickListener(View.OnClickListener {

            //회원가입 처리 시작
            val strEmail = et_email.text.toString() //값을 가저와서, 문자열로 변환
            val strpw = et_pw.text.toString()


            //firebaseAutho 진행
            mFirebaseAuth!!.createUserWithEmailAndPassword(strEmail, strpw)
                .addOnCompleteListener(this@register) { task ->
                    //가입이 이루어진뒤 처리

                    if (task.isSuccessful) {
                        val firebaseUser = mFirebaseAuth!!.currentUser


                        account.idToken = firebaseUser!!.uid //파이어베이스에서 가저옴 (고유)

                        val fireEmail = firebaseUser.email
                        account.eMail = fireEmail.toString()


                        account.password = strpw //사용자가 입력한곳에서 가저옴
                        account.phoneNum = et_num.text.toString()

                        spinner_address.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                when(position){
                                    0->{}
                                    1->{account.address="사상구"}
                                    2->{account.address="부산진구"}
                                }
                            }

                            override fun onNothingSelected(p0: AdapterView<*>?) {
                                Toast.makeText(applicationContext,"주소를 선택해 주세요!", Toast.LENGTH_SHORT).show()
                            }
                        }


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