package com.css101.musicplayermoxy.domain.usecase

import com.css101.musicplayermoxy.domain.models.AudioFile
import com.css101.musicplayermoxy.domain.repository.MusicRepo

class GetMusicListUseCase(private val musicRepo: MusicRepo) {
    suspend fun execute(): List<AudioFile> {
        return musicRepo.getMusicList()
    }
}