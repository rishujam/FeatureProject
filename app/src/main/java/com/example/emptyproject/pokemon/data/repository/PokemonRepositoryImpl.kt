package com.example.emptyproject.pokemon.data.repository

import com.example.emptyproject.pokemon.data.remote.PokemonApi
import com.example.emptyproject.pokemon.data.model.detail.ResPokemonDetail
import com.example.emptyproject.pokemon.data.model.list.ResPokemonListing
import com.example.emptyproject.pokemon.domain.PokemonRepository
import javax.inject.Inject

/*
 * Created by Sudhanshu Kumar on 20/09/24.
 */

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApi
) : PokemonRepository {

    companion object {
        private const val OFFSET_LIMIT = 20
    }

    override suspend fun getPokemonListing(offset: Int): ResPokemonListing? {
        return try {
            val res = api.getPokemonListing(OFFSET_LIMIT, offset)
            if(res.isSuccessful) {
                res.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getPokemonDetail(id: Int): ResPokemonDetail? {
        return try {
            val res = api.getPokemonDetail(id)
            if(res.isSuccessful) {
                res.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}