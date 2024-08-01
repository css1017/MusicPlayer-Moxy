package com.css101.musicplayermoxy.presentation.ui.list

import com.css101.musicplayermoxy.domain.models.AudioFile
import com.css101.musicplayermoxy.domain.usecase.GetMusicListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpPresenter
import moxy.presenterScope

class ListPresenter(private val getMusicListUseCase: GetMusicListUseCase) :
    MvpPresenter<ListView>() {

    private var musicList: List<AudioFile>? = null

    fun getMusicList() {
        if (musicList == null) {
            presenterScope.launch(Dispatchers.Default) {
                val data = getMusicListUseCase.execute()
                withContext(Dispatchers.Main) {
                    musicList = data
                    updateUi(data)
                }
            }
        }
    }

    private fun updateUi(data: List<AudioFile>){
        if (data.isEmpty()) {
            viewState.showEmpty()
        } else {
            viewState.showList()
            viewState.setAdapter(data)
        }
    }
}