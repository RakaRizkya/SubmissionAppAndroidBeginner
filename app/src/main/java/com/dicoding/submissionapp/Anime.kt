package com.dicoding.submissionapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime(
    val title: String,
    val genre: String,
    val rating: String,
    val synopsis: String,
    val picture: Int
): Parcelable
