package com.example.pft.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Medicion(
    var id: Int,
    var medicion1: String,
    var medicion2: String,
    var medicion3: String,
    var medicion4: String
) : Parcelable

