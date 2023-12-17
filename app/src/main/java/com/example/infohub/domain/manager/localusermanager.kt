package com.example.infohub.domain.manager

import kotlinx.coroutines.flow.Flow

interface localusermanager {

    suspend fun saveAppEntry()
    fun readAppEntry(): Flow<Boolean>
}