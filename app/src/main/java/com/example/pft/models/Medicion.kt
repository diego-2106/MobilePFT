package com.example.pft.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Medicion(
    var id: Int,
    var medicion1: String,
    var medicion2: String
) : Parcelable

