package com.cookandroid.udonpro

import android.widget.RadioButton
import com.google.android.datatransport.runtime.dagger.multibindings.StringKey
import java.io.File
import java.util.*


data class Book(
    var img : String="",
    var title : String ="",
    var publish : String ="",
    var startDate : String ="",
    var endDate : String ="",
    var uid: String="",
    var bookType: String="",
    var email: String=""
)
