package com.example.infohub.domain.usecases.app_entry

import com.example.infohub.domain.manager.localusermanager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localusermanager: localusermanager
){

    operator fun invoke(): Flow<Boolean> {
        return localusermanager.readAppEntry()
    }
}