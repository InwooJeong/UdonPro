package com.cookandroid.udonpro

import android.os.Message
import com.google.android.datatransport.runtime.dagger.multibindings.StringKey


data class MainFormListItem(
    val img: String = "",
    val title: String = "",
    val uid: String="",
    val publish: String="",
    val startDate: String="",
    val endDate: String="",
)
