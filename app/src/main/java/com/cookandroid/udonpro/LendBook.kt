package com.cookandroid.udonpro

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
        val wish_num = findViewById<TextView>(R.id.wish_number)
        val wish_image = findViewById<ImageView>(R.id.wish_image)
        val hate_num = findViewById<TextView>(R.id.hate_number)
        val hate_image = findViewById<ImageView>(R.id.hate_image)
        val report_text = findViewById<ImageView>(R.id.report_text)
        var counter = 0

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

//        report_image.setOnClickListener {
//            Toast.makeText(this, "신고되었습니다.", Toast.LENGTH_SHORT).show()
//        }

        lend_button.setOnClickListener {
            Toast.makeText(this,"대여완료 되었습니다!", Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext, MainForm::class.java)
            startActivity(intent)
        }

        report_image.setOnClickListener {
            var builder = AlertDialog.Builder(this)
            builder.setMessage("이 게시글을 신고하시겠습니까?")

//            fun tost() {
//                Toast.makeText(this, "신고되었습니다.", Toast.LENGTH_SHORT).show()
//            }

            var listener = object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    when(p1) {
                        DialogInterface.BUTTON_POSITIVE -> ""
                    }
                }
            }
            builder.setPositiveButton("예",null)
            builder.setNegativeButton("아니요", null)
            builder.show()
        }

    }

}