package com.cookandroid.udonpro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.udonpro.Adapter.UserItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.chatbutton.*

class ChatButton : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chatbutton)

//        val chatButton = findViewById<Button>(R.id.chatButton)
//        chatButton.setOnClickListener {
//            startActivity(Intent(this, ChatRoomActivity::class.java ))
//        }

//        chatButton.setOnClickListener() {
//            val intent = Intent(applicationContext, ChatRoomActivity::class.java)
//            startActivity(intent)
//        }

//        val adapter = GroupAdapter<GroupieViewHolder>()
//
//        adapter.setOnItemClickListener {item, view ->
//
//            val  uid :String = (item as UserItem).uid
//            val  name :String = (item as UserItem).name
//
//            val intent = Intent(this, ChatRoomActivity::class.java)
//            intent.putExtra("yourUid", uid)
//            intent.putExtra("name", name)
//            startActivity(intent)

//        }
    }
}