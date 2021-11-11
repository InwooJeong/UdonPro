package com.cookandroid.udonpro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cookandroid.udonpro.databinding.RlistItemBinding
import kotlinx.android.synthetic.main.reported_list.*
import kotlinx.android.synthetic.main.rlist_item.*

class ReportedListAdapter(private val context: Context):
    RecyclerView.Adapter<ReportedListAdapter.ViewHolder>(){

    var datas = mutableListOf<ReportedListItem>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rlist_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReportedListAdapter.ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val rlist_btitle: TextView = itemView.findViewById(R.id.rlist_btitle)
        private val rlist_bpic: ImageView = itemView.findViewById(R.id.rlist_bpic)
        private val btnDel: Button = itemView.findViewById(R.id.btnDel)

        /*init{
            btnDel.setOnClickListener{

            }
        }*/

        fun bind(item: ReportedListItem){
            rlist_btitle.text = item.bookname
            Glide.with(itemView).load(item.bookpic).into(rlist_bpic)
        }


    }

}