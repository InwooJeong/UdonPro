package com.cookandroid.udonpro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AdminPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_page)

        val btn_bookList = findViewById<Button>(R.id.btn_bookList)
        val button5 = findViewById<Button>(R.id.button5)

        btn_bookList.setOnClickListener{
            val intent = Intent(this, ReportedList::class.java)
            startActivity(intent)
        }

        button5.setOnClickListener{
            val intent = Intent(this, MemberList::class.java)
            startActivity(intent)
        }

    }

}