package com.nuriefeoglu.rickandmorty.ui.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.nuriefeoglu.rickandmorty.R
import com.nuriefeoglu.rickandmorty.ui.adapter.CharacterAdapter
import com.nuriefeoglu.rickandmorty.viewmodel.CharactersFragmentViewModel
import com.nuriefeoglu.rickandmorty.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_characters.*

class CharactersFragment : BaseFragment<CharactersFragmentViewModel>() {

    override val layRes: Int
        get() = R.layout.fragment_characters
    override val viewModel: CharactersFragmentViewModel?
        get() = injectViewModel()

    val cAdapter = CharacterAdapter()

    var mainViewModel: MainViewModel? = null

    override fun viewDidLoad() {
        super.viewDidLoad()
        rvCharacters?.adapter = cAdapter
        val layoutManager = GridLayoutManager(context, 2)
        rvCharacters?.layoutManager = layoutManager
        activity?.let {
            mainViewModel = ViewModelProvider(it)[MainViewModel::class.java]
        }
        mainViewModel?.charactersFilter?.observe(viewLifecycleOwner, Observer {
            it?.let { filter ->
                viewModel?.getAllCharacters(filter)
                mainViewModel?.charactersFilter?.value = null
            }

        })
    }

    override fun observeVM() {
        super.observeVM()

        viewModel?.charactersData?.observe(viewLifecycleOwner, Observer {
            cAdapter.setData(it)
        })
    }


}