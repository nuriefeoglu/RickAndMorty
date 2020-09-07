package com.nuriefeoglu.rickandmorty.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nuriefeoglu.rickandmorty.ui.dialog.LoadingView
import com.nuriefeoglu.rickandmorty.viewmodel.BaseViewModel

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    abstract val layRes: Int
    abstract val viewModel: VM?
    private var _loadingView: LoadingView? = null

    private fun createLoadingView() {
        _loadingView = context?.let { LoadingView(it).apply { setCancelable(false) } }
    }

    open fun viewDidLoad() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layRes, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createLoadingView()
        observeVM()
        viewDidLoad()
    }

    private fun showLoading() {
        if (_loadingView?.isShowing == false) {
            _loadingView?.show()
        }

    }

    private fun hideLoading() {

        if (_loadingView?.isShowing == true) {
            _loadingView?.dismiss()
        }

    }

    override fun onDestroyView() {
        _loadingView?.dismiss()
        _loadingView = null
        super.onDestroyView()
    }

    private fun showError(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    open fun observeVM() {
        viewModel?.loadingOutput?.observe(viewLifecycleOwner, Observer {
            if (it) showLoading() else hideLoading()
        })

        viewModel?.errorOutput?.observe(viewLifecycleOwner, Observer {
            it?.let { message -> showError(message) }
        })
    }

}

inline fun <reified T : ViewModel> Fragment.injectViewModel(
    factory: ViewModelProvider.Factory? = null
): T? =
    if (factory != null) {
        ViewModelProvider(this, factory)[T::class.java]
    } else {
        ViewModelProvider(this)[T::class.java]
    }