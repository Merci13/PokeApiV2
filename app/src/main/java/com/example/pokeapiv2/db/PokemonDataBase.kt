package com.example.pokeapiv2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pokeapiv2.api.ApiPokemonRowItem

/**
 * Create the data base that it will contain all the information extracted from the pokeapi
 * */
@Database(entities = [ApiPokemonRowItem::class], version = 1)
abstract class PokemonDataBase : RoomDatabase() {

    abstract fun repoPokemon(): PokemonDao

    companion object {

        @Volatile
        private var INSTANCE: PokemonDataBase? = null

        /**
         * function that get the instance that who is called from
         * @param context:Context, is the context of the call
         * */
        fun getInstance(context: Context): PokemonDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(
                        context
                    ).also { INSTANCE = it }
            }
        /**
         * function that build the data base "Pokemon.db"
         * @param context: Context, context that is called
         *
         * */
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PokemonDataBase::class.java, "Pokemon.db"
            ).build()
    }


}