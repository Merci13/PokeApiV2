package com.example.pokeapiv2.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.pokeapiv2.db.PokemonLocalCache
import com.example.pokeapiv2.db.PokemonsData

import com.example.pokeapiv2.api.ApiPokemonRowItem


//estos dos devuelven el ApiPokemonRowItem como un factor comun para los datos
/**
 *This two ones parameters
 * @param service:PokemonsData
 * @param cache
 * are like one common factor for the data
 **/
class PokemonRowBoundaryCallback(
    private val service: PokemonsData,
    private val cache: PokemonLocalCache
) : PagedList.BoundaryCallback<ApiPokemonRowItem>() {
    companion object {
        private const val NETWORK_PAGE_SIZE = 20

    }

    /**
     * function that load the first data
     * */
    override fun onZeroItemsLoaded() {
        requestAndSaveData()
    }

    /**
     * function that load data when it is called
     * @param itemAtEnd: ApiPokemonRowItem is the last item that it was loaded.
     * */
    override fun onItemAtEndLoaded(itemAtEnd: ApiPokemonRowItem) {
        requestAndSaveData()
    }

    // keep the last requested page. When the request is successful, increment the page number.
    private var lastRequestedPage = 0

    // LiveData of _network errors.
    private val _networkErrors = MutableLiveData<String>()
    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    /**
     * Ask for the data and saved in the dao
     *return the row that it will show or the error messages that it comes from de pokeapi
     * */
    private fun requestAndSaveData() {
        if (isRequestInProgress) return

        isRequestInProgress = true
        service.getPokemonPage(lastRequestedPage * 20,
            NETWORK_PAGE_SIZE, { rows ->
                cache.insert(rows) {
                    lastRequestedPage++
                    isRequestInProgress = false
                }
            }, { error ->
                _networkErrors.postValue(error)
                isRequestInProgress = false
            })
    }
}


