package com.example.emptyproject.pokemon.domain.usecase

import com.example.emptyproject.pokemon.domain.PokemonRepository
import com.example.emptyproject.pokemon.domain.Resource
import com.example.emptyproject.pokemon.domain.model.PokemonDetail
import com.example.emptyproject.pokemon.domain.parser.ParserPokemonDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/*
 * Created by Sudhanshu Kumar on 21/09/24.
 */

class GetPokemonDetailUserCase @Inject constructor(
    private val pokemonRepo: PokemonRepository,
    private val parser: ParserPokemonDetail
) {

    operator fun invoke(id: Int): Flow<Resource<PokemonDetail?>> = flow {
        emit(Resource.Loading())
        pokemonRepo.getPokemonDetail(id)?.let {
            val parsedResponse = parser.parse(it)
            emit(Resource.Success(parsedResponse))
        } ?: run {
            emit(Resource.Error("Api error"))
        }
    }

}