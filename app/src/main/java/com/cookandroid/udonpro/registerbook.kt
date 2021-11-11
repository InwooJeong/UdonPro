package com.cookandroid.udonpro

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.io.FileOutputStream

class registerbook : AppCompatActivity() {
    var getGalleryImg: Int = 200
    lateinit var imageView2: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registerbook)

        imageView2 = findViewById<ImageView>(R.id.imageView2)

        imageView2.setOnClickListener(View.OnClickListener {
            var intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent, getGalleryImg)
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == getGalleryImg && resultCode == RESULT_OK && data != null && data != null){

            var selectedImgUri : Uri? = data?.data
            imageView2.setImageURI(selectedImgUri)
        }
    }
}


