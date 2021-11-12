package com.cookandroid.udonpro

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.reported_list.*
import kotlinx.android.synthetic.main.rlist_item.*

class ReportedListAdapter(private val context: Context):
    RecyclerView.Adapter<ReportedListAdapter.ViewHolder>(){

    var reportedList = mutableListOf<ReportedListItem>()

    fun setListData(data:MutableList<ReportedListItem>){
        reportedList = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ReportedListAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rlist_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReportedListAdapter.ViewHolder, position: Int) {
        holder.bind(reportedList[position])
    }

    override fun getItemCount(): Int = reportedList.size

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val rlist_btitle: TextView = itemView.findViewById(R.id.rlist_btitle)
        private val rlist_bpic: ImageView = itemView.findViewById(R.id.rlist_bpic)
        private val btnDel: Button = itemView.findViewById(R.id.btnDel)

        init{
            btnDel.setOnClickListener{

            }
        }

        fun bind(item: ReportedListItem){
            rlist_btitle.text = item.title
            Glide.with(itemView).load(item.img).into(rlist_bpic)
        }


    }

}
