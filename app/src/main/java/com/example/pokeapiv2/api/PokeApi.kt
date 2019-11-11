package com.example.pokeapiv2.api


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *Interface that will consult the url https://pokeapi.co/api/v2/ and get all pokemons that it found,
 * obtain the data and returned through the function getAllPokemons()
 */
interface PokeApi {


    // https://pokeapi.co/api/v2/pokemon/?offset=20&limit=20"
    /**
     * Obtain all pokemons form the pokeapi in chunks of 20 pokemons at time
     * @param offset is the number of page that it will be consulted
     * @param limit it will be always 20 because the pokeapi was made in that way.
     * */
    @GET("pokemon/")
    fun getAllPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<GetPokemonResponse>


}