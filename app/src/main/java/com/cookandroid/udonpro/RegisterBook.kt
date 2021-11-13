package com.cookandroid.udonpro

import android.app.DatePickerDialog
import android.content.ContentResolver
import android.content.Intent
import android.icu.util.Calendar
import android.icu.util.LocaleData
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.registerbook.*
import java.text.Format
import java.text.SimpleDateFormat

class RegisterBook : AppCompatActivity() {
    var getGalleryImg: Int = 200
    lateinit var fileName: String
    var storage: FirebaseStorage = FirebaseStorage.getInstance()
    lateinit var selectedImgUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registerbook)


        var cal: Calendar = Calendar.getInstance()
        var mYear = cal.get(Calendar.YEAR)
        var mMonth = cal.get(Calendar.MONTH)
        var mDay = cal.get(Calendar.DAY_OF_MONTH)

        var listener = DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
            startdate.text = "${i}-${i2+1}-${i3}"
        }
        var listener2 = DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
            enddate.text = "${i}-${i2+1}-${i3}"
        }

        sdate.setOnClickListener {
            val dialog: DatePickerDialog = DatePickerDialog(this, listener, mYear, mMonth, mDay)
            dialog.show()

        }

        edate.setOnClickListener {
            val dialog: DatePickerDialog = DatePickerDialog(this, listener2, mYear, mMonth, mDay)
            dialog.show()
        }



        imageView2.setOnClickListener(View.OnClickListener {
            var intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent, getGalleryImg)
        })

        registerBtn.setOnClickListener {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference()
            fileName = bookname.text.toString()

            val dataInput = Book(
                bookname.text.toString(),
                bookname.text.toString(),
                publish.text.toString(),
                startdate.text.toString(),
                enddate.text.toString(),
                if (radioBtn1.isChecked) {
                    radioBtn1.text.toString()
                } else {
                    radioBtn2.text.toString()
                }
            )

            var riversRef: StorageReference =
                storage.reference.child("book_img/"+fileName)
            var uploadTask: UploadTask = riversRef.putFile(selectedImgUri!!)


            Toast.makeText(applicationContext, "등록되었습니다!", Toast.LENGTH_SHORT).show()
            imageView2.setImageResource(R.drawable.addfile)
            bookname.setText("")
            publish.setText("")
            startdate.setText("")
            enddate.setText("")
            myRef.child("book").push().setValue(dataInput)


        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == getGalleryImg && resultCode == RESULT_OK && data != null && data != null) {
            selectedImgUri = data.data!!
            imageView2.setImageURI(selectedImgUri)

        }
    }
}

