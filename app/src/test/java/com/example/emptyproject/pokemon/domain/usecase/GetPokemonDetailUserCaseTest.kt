package com.example.emptyproject.pokemon.domain.usecase

import com.example.emptyproject.pokemon.domain.Resource
import com.example.emptyproject.pokemon.domain.model.PokemonDetail
import com.example.emptyproject.pokemon.domain.mapper.MapperPokemonDetail
import com.example.emptyproject.pokemon.fakes.PokemonRepositoryFakeImpl
import kotlinx.coroutines.test.runTest
import org.junit.Test

/*
 * Created by Sudhanshu Kumar on 25/09/24.
 */

class GetPokemonDetailUserCaseTest {

    private val getPokemonDetail = GetPokemonDetailUserCase(
        PokemonRepositoryFakeImpl(),
        MapperPokemonDetail()
    )

    @Test
    fun `when response is not null emit success` () = runTest {
        val events = mutableListOf<Resource<PokemonDetail?>>()
        getPokemonDetail.invoke(1).collect {
            events.add(it)
        }
        if(events.getOrNull(0) is Resource.Loading && events.getOrNull(1) is Resource.Success) {
            assert(true)
        } else {
            assert(false)
        }
    }

    @Test
    fun `when response is null emit error` () {

    }

}