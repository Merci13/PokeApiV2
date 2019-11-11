package com.example.pokeapiv2.model

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapiv2.R
import com.example.pokeapiv2.api.ApiPokemonRowItem

class ViewHolder(private var view: View) : RecyclerView.ViewHolder(view) {
    private val textViewPokemon: TextView = this.view.findViewById(R.id.infoRowPokemon)
    private var rowPokemon: ApiPokemonRowItem? = null
    private var item: ApiPokemonRowItem? = null

    init {
        rowPokemon?.name = ""
        rowPokemon?.url = ""
    }

    fun bind(item: ApiPokemonRowItem?) {
        if (item != null) {
            showData(item)
        }
    }

    private fun showData(item: ApiPokemonRowItem?) {
        this.item = item
        textViewPokemon.text = item?.name.orEmpty()
        rowPokemon?.name = item?.name.orEmpty()
    }

}