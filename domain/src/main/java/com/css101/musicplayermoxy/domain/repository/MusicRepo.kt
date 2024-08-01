package com.css101.musicplayermoxy.domain.repository

import com.css101.musicplayermoxy.domain.models.AudioFile

interface MusicRepo {
    suspend fun getMusicList(): List<AudioFile>
}