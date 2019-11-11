package com.example.pokeapiv2.db

import com.example.pokeapiv2.api.GetPokemonResponse
import com.example.pokeapiv2.api.ApiPokemonRowItem
import com.example.pokeapiv2.api.PokeApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonsData {
    //Url base https://pokeapi.co/api/v2/
    // variable to retrofit that consult the pokeapi for data
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    // create the service to make the calls to pokeapi
    var service: PokeApi = retrofit.create<PokeApi>(PokeApi::class.java)


    /**
     * function that get the pokemon page that is consulting in the time that is called
     * @param offset, is the number of page that is consulted
     * @param limit, it will be always 20, becouse the poke api is made in that way.
     * @param onSuccess, return the pokemons  from pokeapi
     * @param onError, return the error message from pokeapi.
     * Unit is the same as saying java void method, that don't return a answer
     * */
    fun getPokemonPage(
        offset: Int,
        limit: Int,
        onSuccess: (rows: List<ApiPokemonRowItem>) -> Unit,
        onError: (error: String) -> Unit
    ) {
        // variable call, that get the information form the pokeapi, get the list of pokemons
        val call = service.getAllPokemons(offset, limit)
        //obtain the response from the pokeapi, even though if is an error or success message
        call.enqueue(object : Callback<GetPokemonResponse> {
            override fun onFailure(call: Call<GetPokemonResponse>, t: Throwable) {
                onError(t.message.toString())//se devuelve el mensaje de error que lanza t
            }

            override fun onResponse(
                call: Call<GetPokemonResponse>, remoteResponse: Response<GetPokemonResponse>
            ) {
                onSuccess(remoteResponse.body()?.results.orEmpty())//obtiene el cuerpo el cual tiene el valor del Onresponde
            }
        })
    }
}