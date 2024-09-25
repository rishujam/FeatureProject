package com.example.emptyproject.pokemon.domain.model

import java.io.Serializable

/*
 * Created by Sudhanshu Kumar on 19/09/24.
 */

data class Pokemon(
    val id: Int?,
    val name: String,
    val url: String
) : Serializable
