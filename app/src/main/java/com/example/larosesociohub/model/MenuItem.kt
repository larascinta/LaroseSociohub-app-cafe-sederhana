package com.example.larosesociohub.model

import androidx.annotation.DrawableRes

data class MenuItem(
    val id: Int,
    val name: String,
    val price: Int,
    val description: String,
    @DrawableRes val imageRes: Int
)
