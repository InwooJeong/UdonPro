package com.cookandroid.udonpro

import android.content.Context
import android.content.Intent
import android.media.ImageReader
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class MainFormListAdapter(private val context: Context) :
    RecyclerView.Adapter<MainFormListAdapter.ViewHolder>() {


    var mainformlist = mutableListOf<MainFormListItem>()

    fun setListData(data: MutableList<MainFormListItem>) {
        mainformlist = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MainFormListAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_request, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainFormListAdapter.ViewHolder, position: Int) {
        holder.bind(mainformlist[position])
    }

    override fun getItemCount(): Int = mainformlist.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val bookImg: ImageView = itemView.findViewById(R.id.bookImg)
        private val bookName: TextView = itemView.findViewById(R.id.bookName)
        private val favorite: ImageView = itemView.findViewById(R.id.favorite)
        var user = FirebaseAuth.getInstance().getCurrentUser()
        var uid = if (user != null) {
            user!!.getUid()
        } else {
            null
        }
        val database = Firebase.database
        val myRef = database.getReference(uid.toString()).child("favoritecnt")

        init {

        }

        fun bind(item: MainFormListItem) {
            var count = 0
            bookName.text = item.title

            favorite.setOnClickListener {
                if (count % 2 == 0) {
                    favorite.setImageResource(R.drawable.ic_heart_gray_12dp)
                    myRef.child(item.title).child("count").setValue(count)
                    count++
                } else {
                    favorite.setImageResource(R.drawable.ic_heart_red_12dp)
                   var favoritelistitem = FavoriteListItem(
                        count,
                        item.img,
                        item.title
                    )
                    myRef.child(item.title).setValue(favoritelistitem)
                    count++
                }
            }



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

            bookImg.setOnClickListener {
                val intent = Intent(context, LendBook::class.java)
                intent.putExtra("uid", item.uid)
                intent.putExtra("title", item.title)
                intent.putExtra("publish", item.publish)
                intent.putExtra("startDate", item.startDate)
                intent.putExtra("endDate", item.endDate)
                intent.putExtra("img", item.img)
                context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
            }
        }


    }
}

