package com.css101.musicplayermoxy.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCaller
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.css101.musicplayermoxy.presentation.utils.PermissionsHelper
import moxy.MvpAppCompatFragment
import moxy.MvpPresenter
import moxy.MvpView

abstract class BaseFragment<VB: ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : MvpAppCompatFragment(), ActivityResultCaller {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    protected lateinit var navController: NavController
    protected lateinit var permissionsHelper: PermissionsHelper
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        permissionsHelper = PermissionsHelper(this)

        navController = findNavController()
        _binding = bindingInflater(inflater, container, false)
        return binding.root
    }
}