package com.cookandroid.udonpro.Adapter

import com.cookandroid.udonpro.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.chat_left.view.*

class ChatLeftYou(val msg : String) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.left_msg.text = msg
    }

    override fun getLayout(): Int {
        return R.layout.chat_left
    }
}