package com.example.larosesociohub.data

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("larose_prefs", Context.MODE_PRIVATE)

    var restaurantName: String
        get() = prefs.getString("restaurant_name", "Larose Sociohub") ?: "Larose Sociohub"
        set(value) = prefs.edit().putString("restaurant_name", value).apply()

    var address: String
        get() = prefs.getString("address", "Jl. Bunga Mawar No. 12, Malang") ?: "Jl. Bunga Mawar No. 12, Malang"
        set(value) = prefs.edit().putString("address", value).apply()

    var description: String
        get() = prefs.getString("description", "Tempat nongkrong aesthetic dengan nuansa bunga-bunga yang bikin betah. Menu kami dibuat dengan bahan pilihan, cocok buat kamu yang mau me-time atau quality time bareng teman. Come as you are, leave with a smile 🌸") ?: "Tempat nongkrong aesthetic dengan nuansa bunga-bunga yang bikin betah. Menu kami dibuat dengan bahan pilihan, cocok buat kamu yang mau me-time atau quality time bareng teman. Come as you are, leave with a smile 🌸"
        set(value) = prefs.edit().putString("description", value).apply()

    var openHours: String
        get() = prefs.getString("open_hours", "Senin - Minggu, 09.00 - 21.00 WIB") ?: "Senin - Minggu, 09.00 - 21.00 WIB"
        set(value) = prefs.edit().putString("open_hours", value).apply()
}
