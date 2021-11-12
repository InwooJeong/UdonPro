package com.cookandroid.udonpro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.lend_book.*

class LendBook : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lend_book)

//        val chatButton = findViewById<ImageView>(R.id.chatButton)
//        chatButton.setOnClickListener {
//            startActivity(Intent(this, ChatRoomActivity::class.java))
//        }

        chatButton.setOnClickListener {
            val intent = Intent(applicationContext, ChatRoomActivity::class.java)
            startActivity(intent)
        }

        val like_num = findViewById<TextView>(R.id.like_number)
        val like_image = findViewById<ImageView>(R.id.like_image)
        var counter = 0

        like_image.setOnClickListener {
            counter++
            like_num.text = counter.toString()
        }

//        lend_button.setOnClickListener {
//            val intent = Intent(applicationContext,)
//        }



    }

}