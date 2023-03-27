package com.juanduzac.yapechallenge.data.remote.api

interface Paginator<Key, Item> {

    suspend fun loadNextItems()

    fun reset()
}