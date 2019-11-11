package com.example.pokeapiv2.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.pokeapiv2.db.PokemonsData
import com.example.pokeapiv2.api.ApiPokemonRowItem
import com.example.pokeapiv2.db.PokemonDao
import com.example.pokeapiv2.db.PokemonDataBase
import com.example.pokeapiv2.db.PokemonLocalCache
import com.example.pokeapiv2.model.PokemonSearchResult

/**
 * Class PokemonRepository
 * @param application : Context from it's called
 * @param service: PokemonData that is the PokemonData that is received
 * @param cache: PokemonLocalCache is the local cache of pokemons from the dao
 *
 *
 */

class PokemonRepository(
    application: Context,
    private val service: PokemonsData,

    private val cache: PokemonLocalCache
) {

    private val pokemonDao: PokemonDao // instance for PokemonDao

    /**
     *
     *DATABASE_PAGE_SIZE is the number of items that it will be include en the first load
     */

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }


    init {
        val database: PokemonDataBase =
            PokemonDataBase.getInstance(application)
        pokemonDao = database.repoPokemon()

    }

    /**
     * function that search pokemons to show it
     * return PokemonSearchResult that it contain the data
     */
    fun search(): PokemonSearchResult {
        //Get data source factory from the local cache
        val dataSourceFactory: DataSource.Factory<Int, ApiPokemonRowItem> =
            pokemonDao.getAllPokemon()
        //Construct the boundary callback
        val boundaryCallback = PokemonRowBoundaryCallback(service, cache)
        val networkErrors: LiveData<String> = boundaryCallback.networkErrors
        //Get the pagedList
        val data: LiveData<PagedList<ApiPokemonRowItem>> = LivePagedListBuilder(
            dataSourceFactory,
            DATABASE_PAGE_SIZE
        ).setBoundaryCallback(boundaryCallback).build()

        return PokemonSearchResult(data, networkErrors)
    }


}