package com.example.emptyproject.pokemon.data.remote

import com.example.emptyproject.pokemon.data.model.detail.ResPokemonDetail
import com.example.emptyproject.pokemon.data.model.list.ResPokemonListing
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/*
 * Created by Sudhanshu Kumar on 20/09/24.
 */

interface PokemonApi {


    @GET("api/v2/pokemon/")
    suspend fun getPokemonListing(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<ResPokemonListing>

    @GET("api/v2/pokemon/{id}")
    suspend fun getPokemonDetail(
        @Path("id") id: Int
    ): Response<ResPokemonDetail>

}