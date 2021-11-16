package com.cookandroid.udonpro

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class FavoriteListAdapter(private val context: Context) :
    RecyclerView.Adapter<FavoriteListAdapter.ViewHolder>() {

    var mainformlist = mutableListOf<FavoriteListItem>()

    fun setListData(data: MutableList<FavoriteListItem>) {
        mainformlist = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): FavoriteListAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_request, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteListAdapter.ViewHolder, position: Int) {
        holder.bind(mainformlist[position])
    }

    override fun getItemCount(): Int = mainformlist.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val bookImg: ImageView = itemView.findViewById(R.id.bookImg)
        private val bookName: TextView = itemView.findViewById(R.id.bookName)

        init {
        }

        fun bind(item: FavoriteListItem) {
            bookName.text = item.title

            //Glide.with(itemView).load(item.img).into(rlist_bpic)
            Log.d(
                "태그",
                "주소 : " + Firebase.storage.reference.child("book_img/" + item.img).downloadUrl
            )
            Firebase.storage.reference.child("book_img/" + item.img).downloadUrl.addOnCompleteListener {
                if (it.isSuccessful) {
                    Glide.with(itemView).load(it.result).into(bookImg)
                }
            }

            var mainformlist = MainFormListItem()
            bookImg.setOnClickListener{
                val intent = Intent(context, LendBook::class.java)
                intent.putExtra("uid",mainformlist.uid)
                intent.putExtra("title",mainformlist.title)
                intent.putExtra("publish",mainformlist.publish)
                intent.putExtra("startDate",mainformlist.startDate)
                intent.putExtra("endDate",mainformlist.endDate)
                intent.putExtra("img",mainformlist.img)
                context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
            }
        }

    }
}

