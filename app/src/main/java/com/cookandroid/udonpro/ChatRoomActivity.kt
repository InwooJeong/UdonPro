package com.cookandroid.udonpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.cookandroid.udonpro.Adapter.ChatLeftYou
import com.cookandroid.udonpro.Adapter.ChatRightMe
import com.cookandroid.udonpro.Model.ChatNewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.ktx.Firebase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.chatview.*

class ChatRoomActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    private  val TAG = ChatRoomActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chatview)

        //파이어베이스auth 초기화
        auth = FirebaseAuth.getInstance()

        val myUid :String? = auth.uid
        val yourUid :String? = intent.getStringExtra("yourUid")
        val name :String? = intent.getStringExtra("name")

        val adapter = GroupAdapter<GroupieViewHolder>()

        val database = Firebase.database
        val myRef = database.getReference("message")
        val readRef : DatabaseReference = database.getReference("message").child(myUid.toString()).child(yourUid.toString())

        val childEventListener = object : ChildEventListener {
            override fun onChildAdded(p0 : DataSnapshot, p1: String?) {
                Log.d(TAG, "p0 :" + p0)
                val model : ChatNewModel? = p0.getValue(ChatNewModel::class.java)

                val msg :String = model?.message.toString()
                val who :String? = model?.who

                if(who == "me") {
                    adapter.add(ChatRightMe(msg))
                }else{
                    adapter.add(ChatLeftYou(msg))
                }
            }

            override fun onChildChanged(p0 : DataSnapshot, p1: String?) {

            }

            override fun onChildRemoved(p0 : DataSnapshot) {

            }

            override fun onChildMoved(p0 : DataSnapshot, p1: String?) {

            }

            override fun onCancelled(p0: DatabaseError) {

            }

        }
        recyclerView_chat.adapter = adapter
        readRef.addChildEventListener(childEventListener)

        val myRef_list :DatabaseReference = database.getReference("message-user-list")

        button.setOnClickListener {

            val message = editText.text.toString()

            val chat = ChatNewModel(myUid.toString(), yourUid.toString(), message, System.currentTimeMillis(), "me")
            myRef.child(myUid.toString()).child(yourUid.toString()).push().setValue(chat)

            val chat_get = ChatNewModel(yourUid.toString(), myUid.toString(), message, System.currentTimeMillis(), "you")
            myRef.child(yourUid.toString()).child(myUid.toString()).push().setValue(chat_get)

            myRef_list.child(myUid.toString()).child(yourUid.toString()).setValue(chat)

            editText.setText("")

        }
    }
}