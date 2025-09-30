package com.example.platzicalories.Data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchDto (
    val products: List<Product>
)
