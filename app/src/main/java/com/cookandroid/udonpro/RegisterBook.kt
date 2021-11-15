package com.cookandroid.udonpro

import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.content.ContentResolver
import android.content.Intent
import android.icu.util.Calendar
import android.icu.util.LocaleData
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.registerbook.*
import kotlinx.android.synthetic.main.registerbook.view.*
import java.text.Format
import java.text.SimpleDateFormat

class RegisterBook : Fragment() {
    var user = FirebaseAuth.getInstance().getCurrentUser()
    var uid = if(user!= null) {user!!.getUid()} else {null}

    var getGalleryImg: Int = 200
    lateinit var fileName: String
    var storage: FirebaseStorage = FirebaseStorage.getInstance()
    lateinit var selectedImgUri: Uri

    override fun onStart() {
        super.onStart()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.registerbook, container, false)

        var cal: Calendar = Calendar.getInstance()
        var mYear = cal.get(Calendar.YEAR)
        var mMonth = cal.get(Calendar.MONTH)
        var mDay = cal.get(Calendar.DAY_OF_MONTH)

        var listener = DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
            view.startdate.text = "${i}-${i2+1}-${i3}"
        }
        var listener2 = DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
            view.enddate.text = "${i}-${i2+1}-${i3}"
        }

        view.sdate.setOnClickListener {
            val dialog: DatePickerDialog = DatePickerDialog(requireContext(), listener, mYear, mMonth, mDay)
            dialog.show()

        }

        view.edate.setOnClickListener {
            val dialog: DatePickerDialog = DatePickerDialog(requireContext(), listener2, mYear, mMonth, mDay)
            dialog.show()
        }



        view.imageView2.setOnClickListener(View.OnClickListener {
            var intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent, getGalleryImg)
        })

        view.registerBtn.setOnClickListener {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference()
            fileName = bookname.text.toString()

            val dataInput = Book(
                bookname.text.toString(),
                bookname.text.toString(),
                publish.text.toString(),
                startdate.text.toString(),
                enddate.text.toString(),
                uid.toString()
            )

            var riversRef: StorageReference =
                storage.reference.child("book_img/"+fileName)
            var uploadTask: UploadTask = riversRef.putFile(selectedImgUri!!)


            Toast.makeText(context, "등록되었습니다!", Toast.LENGTH_SHORT).show()
            view.imageView2.setImageResource(R.drawable.addfile)
            view.bookname.setText("")
            view.publish.setText("")
            view.startdate.setText("")
            view.enddate.setText("")
            if(radioBtn1.isChecked) {
                myRef.child(uid!!).child("book").child("공유도서").push().setValue(dataInput)
                myRef.child("book").child("공유도서").push().setValue(dataInput)
            }else if(radioBtn2.isChecked){
                myRef.child(uid!!).child("book").child("요청도서").push().setValue(dataInput)
                myRef.child("book").child("요청도서").push().setValue(dataInput)
            }


        }
        return view
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == getGalleryImg && resultCode == RESULT_OK && data != null && data != null) {
            selectedImgUri = data.data!!
            imageView2.setImageURI(selectedImgUri)

        }
    }
}

