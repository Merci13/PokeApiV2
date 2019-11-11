package com.example.pokeapiv2.api

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Make the table pokemon in the data base
 * */
@Entity(tableName = "pokemon")
data class ApiPokemonRowItem(
    @PrimaryKey(autoGenerate = false)
    var name: String = "",
    var url: String? = null
)
