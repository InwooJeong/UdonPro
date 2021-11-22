package com.cookandroid.udonpro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.member_list.*
import kotlinx.android.synthetic.main.mlist_item.*

class MemberListAdapter(private val context: Context):
    RecyclerView.Adapter<MemberListAdapter.ViewHolder>(){

    var memberList = mutableListOf<MemberListItem>()

    fun setListData(data:MutableList<MemberListItem>){
        memberList = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MemberListAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.mlist_item, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemberListAdapter.ViewHolder, position: Int) {
        holder.bind(memberList[position])
    }

    override fun getItemCount(): Int = memberList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val mlist_memberid: TextView = itemView.findViewById(R.id.mlist_memberid)
        private val mlist_profile: ImageView = itemView.findViewById(R.id.mlist_profile)
        private val btnDel: ImageButton = itemView.findViewById(R.id.btnDel)

        /*init{
            btnInfo.setOnClickListener{
                btnInfo.setImageResource(R.drawable.ic_user_24dp)
            }
        }*/

        fun bind(item: MemberListItem){
            mlist_memberid.text = item.email
            //Glide.with(itemView).load(item.profile).into(mlist_profile)
            Firebase.storage.reference.child("member_img/"+item.profile).downloadUrl.addOnCompleteListener{
                if(it.isSuccessful){
                    Glide.with(itemView).load(it.result).into(mlist_profile)
                }
            }
            btnDel.setOnClickListener{
                val database = Firebase.database
                database.getReference("UdonProject/UserAccount/"+item.idToken).removeValue()
            }

        }
    }
}