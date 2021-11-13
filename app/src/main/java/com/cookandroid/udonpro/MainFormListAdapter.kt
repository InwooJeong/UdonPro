package com.cookandroid.udonpro

import android.content.Context
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class MainFormListAdapter(private val context: Context):
    RecyclerView.Adapter<MainFormListAdapter.ViewHolder>(){

    var mainformlist = mutableListOf<MainFormListItem>()

    fun setListData(data:MutableList<MainFormListItem>){
        mainformlist = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MainFormListAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_request,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainFormListAdapter.ViewHolder, position: Int) {
        holder.bind(mainformlist[position])
    }

    override fun getItemCount(): Int = mainformlist.size

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val bookImg: ImageView = itemView.findViewById(R.id.bookImg)
        private val bookName: TextView = itemView.findViewById(R.id.bookName)
        private val favorite: ImageView = itemView.findViewById(R.id.favorite)

        init{
            favorite.setOnClickListener{

            }
        }

        fun bind(item: MainFormListItem){
            bookName.text = item.title
            //Glide.with(itemView).load(item.img).into(rlist_bpic)
            Log.d("태그", "주소 : "+ Firebase.storage.reference.child("book_img/"+item.img).downloadUrl)
            Firebase.storage.reference.child("book_img/"+item.img).downloadUrl.addOnCompleteListener{
                if(it.isSuccessful){
                    Glide.with(itemView).load(it.result).into(bookImg)
                }
            }
        }

    }
}