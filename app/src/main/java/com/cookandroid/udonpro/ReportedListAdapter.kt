package com.cookandroid.udonpro

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.model.mutation.ArrayTransformOperation
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
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
        private val btnDel: ImageButton = itemView.findViewById(R.id.btnDel)

        /*init{
            btnDel.setOnClickListener{
                val database = Firebase.database
                val myRef = database.getReference("book").child(item.)
            }
        }*/

        fun bind(item: ReportedListItem){
            rlist_btitle.text = item.title
            //Glide.with(itemView).load(item.img).into(rlist_bpic)
            //Log.d("태그", "주소 : "+ Firebase.storage.reference.child("book_img/"+item.img).downloadUrl)
            Firebase.storage.reference.child("book_img/"+item.img).downloadUrl.addOnCompleteListener{
                if(it.isSuccessful){
                    Glide.with(itemView).load(it.result).into(rlist_bpic)
                }
            }
            btnDel.setOnClickListener{
                val databse = Firebase.database
                //val myRef = databse.getReference("book").orderByChild("title").equalTo(item.title)
                //Log.d("태그", "선택 : "+item.title)

                databse.getReference("book/"+item.title).removeValue()

            }
        }

    }

}
