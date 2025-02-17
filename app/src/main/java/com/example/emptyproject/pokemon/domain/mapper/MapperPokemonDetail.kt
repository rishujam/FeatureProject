package com.example.emptyproject.pokemon.domain.mapper

import com.example.emptyproject.pokemon.data.model.detail.ResPokemonDetail
import com.example.emptyproject.pokemon.domain.model.PokemonDetail
import javax.inject.Inject

/*
 * Created by Sudhanshu Kumar on 23/09/24.
 */

class MapperPokemonDetail @Inject constructor() {

    fun map(apiResponse: ResPokemonDetail?): PokemonDetail? {
        apiResponse?.let {
            return PokemonDetail(
                id = apiResponse.id,
                name = apiResponse.name,
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${apiResponse.id}.png",
                description = "description"
            )
        } ?: run {
            return null
        }
    }

}