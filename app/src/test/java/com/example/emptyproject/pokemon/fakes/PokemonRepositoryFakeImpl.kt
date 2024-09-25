package com.example.emptyproject.pokemon.fakes

import com.example.emptyproject.pokemon.data.model.detail.Cries
import com.example.emptyproject.pokemon.data.model.detail.DreamWorld
import com.example.emptyproject.pokemon.data.model.detail.Home
import com.example.emptyproject.pokemon.data.model.detail.OfficialArtwork
import com.example.emptyproject.pokemon.data.model.detail.Other
import com.example.emptyproject.pokemon.data.model.detail.ResPokemonDetail
import com.example.emptyproject.pokemon.data.model.detail.Showdown
import com.example.emptyproject.pokemon.data.model.detail.Species
import com.example.emptyproject.pokemon.data.model.detail.Sprites
import com.example.emptyproject.pokemon.data.model.list.ResPokemonListing
import com.example.emptyproject.pokemon.data.model.list.Result
import com.example.emptyproject.pokemon.domain.PokemonRepository

/*
 * Created by Sudhanshu Kumar on 25/09/24.
 */

class PokemonRepositoryFakeImpl : PokemonRepository {

    override suspend fun getPokemonListing(offset: Int): ResPokemonListing? {
        when(offset) {
            -1 -> {
                return null
            }
            0 -> {
                return ResPokemonListing(
                    1,
                    "",
                    "",
                    emptyList()
                )
            }
            1 -> {
                return ResPokemonListing(
                    1,
                    "",
                    "",
                    listOf(Result(name = "Chalizard", ""))
                )
            }
        }
        return null
    }

    override suspend fun getPokemonDetail(id: Int): ResPokemonDetail? {
        return when(id) {
            -1 -> {
                return null
            }
            0 -> {
                return null
            }
            1 -> {
                return ResPokemonDetail(
                    listOf(),
                    1,
                    Cries("", ""),
                    listOf(),
                    listOf(),
                    0,
                    listOf(),
                    1,
                    false,
                    "",
                    listOf(),
                    "",
                    1,
                    listOf(),
                    listOf(),
                    Species("", ""),
                    Sprites(
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        Other(
                            DreamWorld("", ""),
                            Home("", "", "", ""),
                            OfficialArtwork("", ""),
                            Showdown("", "", "", "", "", "", "", "")
                        ),
                        null
                    ),
                    listOf(),
                    listOf(),
                    1
                )
            }
            else -> null
        }
    }
}