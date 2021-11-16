package com.cookandroid.udonpro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class AdminPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_page)

        val btn_bookList = findViewById<Button>(R.id.btn_bookList)
        val button5 = findViewById<Button>(R.id.button5)
        val btn_goBoard = findViewById<Button>(R.id.btn_goBoard)

        btn_bookList.setOnClickListener{
            val intent = Intent(this, ReportedList::class.java)
            startActivity(intent)
        }

        button5.setOnClickListener{
            val intent = Intent(this, MemberList::class.java)
            startActivity(intent)
        }

        btn_goBoard.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("공지사항 입니다.")
                .setMessage("12월 12일 22:00 ~ 23:00에 ver.1.23 업데이트가 있을 예정입니다.")
                .setPositiveButton("확인") {
                        DialogInterface, i ->
                }.show()
        }

    }

}