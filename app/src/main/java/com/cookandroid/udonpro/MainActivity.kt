package com.cookandroid.udonpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.chatbutton.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chatbutton)

        chatButton.setOnClickListener() {
            val intent = Intent(applicationContext, ChatRoomActivity::class.java)
            startActivity(intent)
        }

//        val writeBtn = findViewById<Button>(R.id.writeBtn)
//
//        writeBtn.setOnClickListener {
//            val database = Firebase.database
//
//            val myRef = database.getReference("message")
//
//            myRef.setValue("Hello, World!")
    }
}

// hihihihihih1122ggggg