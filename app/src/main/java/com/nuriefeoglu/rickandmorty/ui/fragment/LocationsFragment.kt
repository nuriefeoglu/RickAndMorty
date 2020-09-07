package com.nuriefeoglu.rickandmorty.ui.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nuriefeoglu.rickandmorty.R
import com.nuriefeoglu.rickandmorty.ui.adapter.LocationsAdapter
import com.nuriefeoglu.rickandmorty.viewmodel.LocationsFragmentViewModel
import kotlinx.android.synthetic.main.fragment_locations.*

class LocationsFragment : BaseFragment<LocationsFragmentViewModel>() {
    override val layRes: Int
        get() = R.layout.fragment_locations
    override val viewModel: LocationsFragmentViewModel?
        get() = injectViewModel()

    private val locationsAdapter = LocationsAdapter()

    override fun viewDidLoad() {
        super.viewDidLoad()
        rvLocations?.adapter = locationsAdapter
        rvLocations?.layoutManager = LinearLayoutManager(context)
        rvLocations?.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }

    override fun observeVM() {
        super.observeVM()
        viewModel?.locationData?.observe(viewLifecycleOwner, Observer {
            locationsAdapter.setData(it)
        })

    }


}