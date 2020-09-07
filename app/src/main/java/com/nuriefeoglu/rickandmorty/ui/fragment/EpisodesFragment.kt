package com.nuriefeoglu.rickandmorty.ui.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nuriefeoglu.rickandmorty.R
import com.nuriefeoglu.rickandmorty.ui.activity.MainActivity
import com.nuriefeoglu.rickandmorty.ui.adapter.EpisodesAdapter
import com.nuriefeoglu.rickandmorty.viewmodel.EpisodesFragmentViewModel
import com.nuriefeoglu.rickandmorty.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_episodes.*

class EpisodesFragment : BaseFragment<EpisodesFragmentViewModel>() {

    override val layRes: Int
        get() = R.layout.fragment_episodes
    override val viewModel: EpisodesFragmentViewModel?
        get() = injectViewModel()

    private val episodesAdapter = EpisodesAdapter()

    var mainViewModel : MainViewModel? = null


    override fun viewDidLoad() {
        super.viewDidLoad()
        rvEpisodes?.adapter = episodesAdapter
        rvEpisodes?.layoutManager = LinearLayoutManager(context)
        rvEpisodes?.addItemDecoration(DividerItemDecoration(context,LinearLayoutManager.VERTICAL))
        activity?.let {
            mainViewModel = ViewModelProvider(it)[MainViewModel::class.java]
        }
        mainViewModel?.episodesFilter?.observe(this, Observer {
            it?.let {filter ->
                viewModel?.getAllEpisodes(filter)
                mainViewModel?.episodesFilter?.value = null
            }
        })
    }

    override fun observeVM() {
        super.observeVM()
        viewModel?.episodesData?.observe(viewLifecycleOwner, Observer {
            episodesAdapter.setData(it)
        })
    }
}