package com.example.emptyproject.pokemon.domain.usecase

import com.example.emptyproject.pokemon.domain.PokemonRepository
import com.example.emptyproject.pokemon.domain.Resource
import com.example.emptyproject.pokemon.domain.model.Pokemon
import com.example.emptyproject.pokemon.domain.parser.ParserPokemonList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/*
 * Created by Sudhanshu Kumar on 20/09/24.
 */

class GetPokemonListUseCase @Inject constructor(
    private val pokemonRepo: PokemonRepository,
    private val parser: ParserPokemonList
) {

    operator fun invoke(offset: Int): Flow<Resource<List<Pokemon>>> = flow {
        emit(Resource.Loading())
        pokemonRepo.getPokemonListing(offset)?.let {
            val parsedResponse = parser.parse(it)
            emit(Resource.Success(parsedResponse))
        } ?: run {
            emit(Resource.Error("Api error"))
        }

    }

}