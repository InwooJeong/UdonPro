package com.cookandroid.udonpro

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.mypage.*
import kotlinx.android.synthetic.main.uploadmypage.*
import java.lang.reflect.Member


class newMypage : Fragment() {

    lateinit var mAuth: FirebaseAuth
    lateinit var userEmail: String
    lateinit var userUid: String
    lateinit var dialogView: View
    lateinit var spinner: Spinner


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val view = inflater.inflate(R.layout.mypage, container, false)
        return view
    }

    //내정보표시.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tv_userEmail = requireView().findViewById<EditText>(R.id.tv_userEmail2) as TextView
        val btn_logout = requireView().findViewById<Button>(R.id.btn_logout) as Button
        val btnAdmin = requireView().findViewById<Button>(R.id.adminPage) as Button

        mAuth = FirebaseAuth.getInstance()
        mAuth!!.addAuthStateListener { firebaseAuth: FirebaseAuth? ->
            mAuth = FirebaseAuth.getInstance()
        }

        val currentUser = mAuth!!.currentUser


        if(currentUser!=null) {

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

            btnAdmin.setOnClickListener {
                val intent = Intent(getActivity(), AdminPage::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                mAuth.signOut()
            }

            //공지사항
            btn_goBoard.setOnClickListener{
                val builder = getActivity()?.let { it1 -> AlertDialog.Builder(it1) }
                builder?.setTitle("공지사항 입니다.")
                    ?.setMessage("12월 12일 22:00 ~ 23:00에 ver.1.23 업데이트가 있을 예정입니다.")
                    ?.setPositiveButton("확인") { DialogInterface, i ->
                    }?.show()
            }

            button.setOnClickListener {
                dialogView = View.inflate(requireContext(), R.layout.uploadmypage, null)
                spinner = dialogView.findViewById<Spinner>(R.id.spinner_upload)
                var phoneNumber = dialogView.findViewById<EditText>(R.id.phoneNum)
                var userid = dialogView.findViewById<EditText>(R.id.userid)

                mAuth = FirebaseAuth.getInstance()
                mAuth!!.addAuthStateListener { firebaseAuth: FirebaseAuth? ->
                    mAuth = FirebaseAuth.getInstance()
                }

                val currentUser = mAuth!!.currentUser
                userEmail = currentUser!!.email.toString()
                userUid = currentUser.uid
                userid.setText(userEmail)

                phoneNumber.addTextChangedListener(PhoneNumberFormattingTextWatcher())


                var addressItem = arrayOf("사상구", "부산진구")
                val myAdapter = ArrayAdapter<String>(
                    requireContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    addressItem
                )
                spinner.adapter = myAdapter


                var dlg = AlertDialog.Builder(requireContext())
                dlg.setView(dialogView)
                dlg.setPositiveButton("확인") { dialog, which ->
                    var database = FirebaseDatabase.getInstance()
                    var myRef = database.getReference("UdonProject").child("UserAccount")

                    myRef.child(userUid).child("phoneNum").setValue(phoneNumber.text.toString())

                    var address = spinner.selectedItem.toString()
                    myRef.child(userUid).child("address").setValue(address)
                    Toast.makeText(requireContext(), "수정이 완료되었습니다!", Toast.LENGTH_SHORT).show()
                }
                dlg.setNegativeButton("취소", null)
                dlg.show()

            }
        }else{
            var dlglogin = AlertDialog.Builder(requireContext())
            dlglogin.setTitle("로그인")
            dlglogin.setMessage("로그인 하시겠습니까?")
            dlglogin.setPositiveButton("예"){dialog, which ->
                val intent = Intent(context, loginform::class.java)
                startActivity(intent)
            }
            dlglogin.setNegativeButton("아니요"){dialog, which ->
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            }
            dlglogin.show()
        }


    }


}
