package com.cookandroid.udonpro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.lend_book.*

class LendBook : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lend_book)

        val userEmail = intent.getStringExtra("userEmail").toString()
        val uid = intent.getStringExtra("uid").toString()
        val title = intent.getStringExtra("title").toString()
        val publish = intent.getStringExtra("publish").toString()
        val startDate = intent.getStringExtra("startDate").toString()
        val endDate = intent.getStringExtra("endDate").toString()
        val img = intent.getStringExtra("img").toString()

        Log.d("userMail",userEmail + "111111111111111" )

        var intent =


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
        val wish_num = findViewById<TextView>(R.id.wish_number)
        val wish_image = findViewById<ImageView>(R.id.wish_image)
        val hate_num = findViewById<TextView>(R.id.hate_number)
        val hate_image = findViewById<ImageView>(R.id.hate_image)
        val report_text = findViewById<ImageView>(R.id.report_text)

        // 데이터 값 받아오기
        userid.text = userEmail
        Firebase.storage.reference.child("book_img/" + img).downloadUrl.addOnCompleteListener {
            if (it.isSuccessful) {
                Glide.with(imageView9).load(it.result).into(imageView9)
            }
        }
        book_title.text = title
        publisher_text.text = publish
        var str = startDate + "~" + endDate
        lend_date.text = str




        like_image.setOnClickListener {
            var counter = 0
            counter++
            like_num.text = counter.toString()
        }

        wish_image.setOnClickListener {
            var counter = 0
            counter++
            wish_num.text = counter.toString()
        }

        hate_image.setOnClickListener {
            var counter = 0
            counter++
            hate_num.text = counter.toString()
        }

        report_image.setOnClickListener {
            Toast.makeText(this, "신고되었습니다.", Toast.LENGTH_SHORT).show()
        }

        lend_button.setOnClickListener {
            Toast.makeText(this,"대여완료 되었습니다!", Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext, MainForm::class.java)
            startActivity(intent)
        }

        report_image.setOnClickListener {

        }

        userid.setOnClickListener {
            val intent = Intent(this, Uploader::class.java)
            intent.putExtra("uid", uid)
            intent.putExtra("title", title)
            intent.putExtra("img", img)
            intent.putExtra("userEmail", userEmail)
            startActivity(intent)
        }
    }

}