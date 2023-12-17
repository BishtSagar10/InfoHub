package com.example.infohub.domain.usecases.app_entry

import com.example.infohub.domain.manager.localusermanager

class SaveAppEntry (
    private val localusermanager: localusermanager
){

    suspend operator fun invoke(){
        localusermanager.saveAppEntry()
    }
}