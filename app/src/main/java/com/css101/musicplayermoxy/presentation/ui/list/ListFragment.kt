package com.css101.musicplayermoxy.presentation.ui.list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.css101.musicplayermoxy.presentation.base.BaseFragment
import com.css101.musicplayermoxy.databinding.FragmentListBinding
import com.css101.musicplayermoxy.domain.models.AudioFile
import moxy.ktx.moxyPresenter
import org.koin.android.ext.android.inject

class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate), ListView {

    private val presenterProvider: ListPresenter by inject()
    private val presenter by moxyPresenter { presenterProvider }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        showLoading()
        requestPermission()
    }

    override fun setAdapter(musicList: List<AudioFile>) = with(binding) {
        val adapter = ListSongAdapter(musicList) {
            val action = ListFragmentDirections.actionListToPlayer(it)
            val navOptions = NavOptions.Builder()
                .setPopUpTo(navController.graph.startDestinationId, true)
                .build()
            navController.navigate(action, navOptions)
        }
        rvList.adapter = adapter
        adapter.notifyItemRangeChanged(0, adapter.itemCount)
    }

    override fun showEmpty() = with(binding) {
        llNoData.visibility = View.VISIBLE
        llNoPermission.visibility = View.GONE
        rvList.visibility = View.GONE
        llLoading.visibility = View.GONE
        btnRefresh.setOnClickListener { presenter.getMusicList() }
    }

    override fun showList() = with(binding) {
        llNoData.visibility = View.GONE
        llNoPermission.visibility = View.GONE
        llLoading.visibility = View.GONE
        rvList.visibility = View.VISIBLE
    }

    override fun showNoPermission() = with(binding) {
        llNoData.visibility = View.GONE
        llNoPermission.visibility = View.VISIBLE
        rvList.visibility = View.GONE
        llLoading.visibility = View.GONE
        btnGiveAccess.setOnClickListener { requestPermission() }
        btnGoToSettings.setOnClickListener { permissionsHelper.toAppSettings(requireContext()) }
    }

    override fun showLoading() = with(binding) {
        llNoData.visibility = View.GONE
        llNoPermission.visibility = View.GONE
        rvList.visibility = View.GONE
        llLoading.visibility = View.VISIBLE
    }

    override fun requestPermission() {
        permissionsHelper.request(
            whenReceived = { presenter.getMusicList() },
            whenNotReceived = { showNoPermission() })
    }

    override fun onResume() {
        Log.e("TAG", "onResume: ")
        super.onResume()
    }
}