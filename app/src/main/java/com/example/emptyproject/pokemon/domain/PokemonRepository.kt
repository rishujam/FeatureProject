package com.example.emptyproject.pokemon.domain

import com.example.emptyproject.pokemon.data.model.detail.ResPokemonDetail
import com.example.emptyproject.pokemon.data.model.list.ResPokemonListing

/*
 * Created by Sudhanshu Kumar on 20/09/24.
 */

interface PokemonRepository {

    suspend fun getPokemonListing(offset: Int): ResPokemonListing?

    suspend fun getPokemonDetail(id: Int): ResPokemonDetail?

}