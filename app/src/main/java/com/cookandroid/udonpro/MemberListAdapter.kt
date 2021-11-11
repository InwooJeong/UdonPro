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
import kotlinx.android.synthetic.main.member_list.*
import kotlinx.android.synthetic.main.mlist_item.*

class MemberListAdapter(private val context: Context): RecyclerView.Adapter<MemberListAdapter.ViewHolder>(){

    var datas = mutableListOf<MemberListItem>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.mlist_item, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemberListAdapter.ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val mlist_memberid: TextView = itemView.findViewById(R.id.mlist_memberid)
        private val mlist_profile: ImageView = itemView.findViewById(R.id.mlist_profile)
        private val btnInfo: ImageButton = itemView.findViewById(R.id.btnInfo)

        init{
            btnInfo.setOnClickListener{
                btnInfo.setImageResource(R.drawable.ic_user_24dp)
            }
        }

        fun bind(item: MemberListItem){
            mlist_memberid.text = item.username
            Glide.with(itemView).load(item.profile).into(mlist_profile)

        }
    }
}