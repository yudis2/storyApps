package com.yudisdwi.submission1.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoryModel(
    val name: String,
    val photoUrl: String? = null,
    val id: String,
    val description: String? = null,
) : Parcelable
