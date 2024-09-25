package com.example.emptyproject.pokemon.domain.usecase

import com.example.emptyproject.pokemon.domain.parser.ParserPokemonDetail
import com.example.emptyproject.pokemon.fakes.PokemonRepositoryFakeImpl
import kotlinx.coroutines.test.runTest
import org.junit.Test

/*
 * Created by Sudhanshu Kumar on 25/09/24.
 */

class GetPokemonDetailUserCaseTest {

    private val getPokemonDetail = GetPokemonDetailUserCase(
        PokemonRepositoryFakeImpl(),
        ParserPokemonDetail()
    )

    @Test
    fun `when response is not null emit success` () = runTest {

    }

    @Test
    fun `when response is null emit error` () {

    }

}