package com.cookandroid.udonpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.navigation.NavigationBarView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),
    NavigationBarView.OnItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_navigation.setOnItemSelectedListener(this)


//        chatButton.setOnClickListener() {
//            val intent = Intent(applicationContext, ChatRoomActivity::class.java)
//            startActivity(intent)
//        }

//        val writeBtn = findViewById<Button>(R.id.writeBtn)
//
//        writeBtn.setOnClickListener {
//            val database = Firebase.database
//
//            val myRef = database.getReference("message")
//
//            myRef.setValue("Hello, World!")
    }


    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.btn_home ->{
                var page1 = MainForm()
                supportFragmentManager.beginTransaction().replace(R.id.main_content, page1).commit()
                return true
            }
            R.id.btn_favorites ->{
                var page2 = MainForm_Request()
                supportFragmentManager.beginTransaction().replace(R.id.main_content, page2).commit()
                return true
            }
            R.id.btn_registerBook ->{
                var page3 = newMypage()
                supportFragmentManager.beginTransaction().replace(R.id.main_content, page3).commit()
                return true
            }
            R.id.btn_mypage ->{
                var page4 = newMypage()
                supportFragmentManager.beginTransaction().replace(R.id.main_content, page4).commit()
                return true
            }
        }
        return false
    }


}





