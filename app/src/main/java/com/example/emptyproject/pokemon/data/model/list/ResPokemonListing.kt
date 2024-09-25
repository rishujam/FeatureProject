package com.example.emptyproject.pokemon.data.model.list

data class ResPokemonListing(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Result>
)