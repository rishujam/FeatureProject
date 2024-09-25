package com.example.emptyproject.pokemon.domain.parser

import com.example.emptyproject.pokemon.data.model.list.ResPokemonListing
import com.example.emptyproject.pokemon.domain.model.Pokemon
import javax.inject.Inject

/*
 * Created by Sudhanshu Kumar on 23/09/24.
 */

class ParserPokemonList @Inject constructor() {

    fun parse(apiResponse: ResPokemonListing): List<Pokemon> {
        val list = mutableListOf<Pokemon>()
        for(i in apiResponse.results) {
            val id = i.url.dropLast(1).takeLastWhile { it.isDigit() }.toIntOrNull()
            list.add(
                Pokemon(
                    id = id,
                    i.name,
                    url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
                )
            )
        }
        return list
    }

}