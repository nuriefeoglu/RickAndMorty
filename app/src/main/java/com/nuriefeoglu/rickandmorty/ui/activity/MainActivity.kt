package com.nuriefeoglu.rickandmorty.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.apollographql.apollo.api.Input
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.nuriefeoglu.rickandmorty.R
import com.nuriefeoglu.rickandmorty.graphql.GetCharactersQuery
import com.nuriefeoglu.rickandmorty.graphql.type.FilterCharacter
import com.nuriefeoglu.rickandmorty.ui.fragment.CharactersFragment
import com.nuriefeoglu.rickandmorty.ui.fragment.EpisodesFragment
import com.nuriefeoglu.rickandmorty.ui.fragment.LocationsFragment
import com.nuriefeoglu.rickandmorty.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

/* val client = ApolloClient
            .builder()
            .serverUrl("https://rickandmortyapi.com/graphql")
            .build()
        client.query(EpisodesQuery(Input.optional(1)))
            .enqueue(object : ApolloCall.Callback<EpisodesQuery.Data>(){
                override fun onFailure(e: ApolloException) {
                    Log.d("APOLLO",e.message.toString())
                }

                override fun onResponse(response: Response<EpisodesQuery.Data>) {
                    Log.d("APOLLO",response.data?.episodes?.results?.toString())
                }

            })*/


class MainActivity : AppCompatActivity() {


    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .disallowAddToBackStack()
            .commit()
    }

    private var viewModel: MainViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        btnFilter?.setOnClickListener {
            createCharacterBottomSheet()
        }

        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_character -> {
                    txtTitle?.text = "All Characters"
                    addFragment(CharactersFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.nav_location -> {
                    txtTitle?.text = "All Locations"
                    addFragment(LocationsFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.nav_episodes -> {
                    txtTitle?.text = "All Episodes"
                    addFragment(EpisodesFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                else -> return@setOnNavigationItemSelectedListener false
            }

        }
        bottomNav.selectedItemId =
            R.id.nav_character
    }


    private fun createCharacterBottomSheet() {
        val dialogView: View =
            layoutInflater.inflate(R.layout.fragment_bottom_sheet_character, null)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(dialogView)

        val spinnerStatus = dialog.findViewById<Spinner>(R.id.spinnerStatus)
        val spinnerSpecies = dialog.findViewById<Spinner>(R.id.spinnerSpecies)
        val spinnerGender = dialog.findViewById<Spinner>(R.id.spinnerGender)

        val filterButton = dialog.findViewById<MaterialButton>(R.id.btnFilter)
        filterButton?.setOnClickListener {

            val characterStatus = spinnerStatus?.selectedItem?.toString()
            val characterSpecies = spinnerSpecies?.selectedItem?.toString()
            val characterGender = spinnerGender?.selectedItem?.toString()

            val filter = FilterCharacter(
                status = Input.fromNullable(characterStatus),
                species = Input.fromNullable(characterSpecies),
                gender = Input.fromNullable(characterGender)
            )

            viewModel?.charactersFilter?.value = filter
            dialog.dismiss()

        }
        dialog.show()

    }

}