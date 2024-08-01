package com.css101.musicplayermoxy.presentation.ui.player

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.css101.musicplayermoxy.R
import com.css101.musicplayermoxy.presentation.base.BaseFragment
import com.css101.musicplayermoxy.databinding.FragmentPlayerBinding
import com.css101.musicplayermoxy.domain.models.AudioFile
import moxy.ktx.moxyPresenter
import org.koin.android.ext.android.inject

class PlayerFragment : BaseFragment<FragmentPlayerBinding>(FragmentPlayerBinding::inflate), PlayerView {

    private val args: PlayerFragmentArgs by navArgs()
    private val presenterProvider: PlayerPresenter by inject()
    private val presenter by moxyPresenter { presenterProvider }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.saveAudio(args.audioFile)
        showEmpty()
    }

    override fun setControls(isPlaying: Boolean) = with(binding) {
        btnPlayPause.setOnClickListener {
            presenter.playPause()
        }
        when (isPlaying) {
            false -> btnPlayPause.setImageResource(R.drawable.ic_play)
            true -> btnPlayPause.setImageResource(R.drawable.ic_pause)
        }
    }

    override fun setAudio(audio: AudioFile?) = with(binding) {
        Glide.with(requireContext())
            .load(audio?.coverUri)
            .placeholder(R.drawable.img_no_album)
            .error(R.drawable.img_no_album)
            .into(ivCover)
        tvArtistPlayer.text = audio?.artist
        tvTitlePlayer.text = audio?.title
    }

    override fun showEmpty() = with(binding) {
        btnPickSong.setOnClickListener {
            val action = PlayerFragmentDirections.actionPlayerToList()
            navController.navigate(action)
        }
        llEmpty.visibility = View.VISIBLE
        ivCover.visibility = View.GONE
        llTitle.visibility = View.GONE
        btnPlayPause.visibility = View.GONE
    }

    override fun showPlayer() = with(binding) {
        llEmpty.visibility = View.GONE
        ivCover.visibility = View.VISIBLE
        llTitle.visibility = View.VISIBLE
        btnPlayPause.visibility = View.VISIBLE
    }
}