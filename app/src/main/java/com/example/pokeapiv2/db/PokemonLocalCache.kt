/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.pokeapiv2.db

import com.example.pokeapiv2.api.ApiPokemonRowItem

import java.util.concurrent.Executor

/**
 * Class that handles the DAO local data source. This ensures that methods are triggered on the
 * correct executor.
 */
class PokemonLocalCache(
    private val repoDao: PokemonDao,
    private val ioExecutor: Executor
) {

    /**
     * Insert a list of ApiPokemonRowItem in the database, on a background thread.
     */
    fun insert(rows: List<ApiPokemonRowItem>, insertFinished: () -> Unit) {
        ioExecutor.execute {

            repoDao.insert(rows)
            insertFinished()
        }
    }

}
