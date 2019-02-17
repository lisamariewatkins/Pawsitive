package com.example.petmatcher

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.petmatcher.favorites.FavoritesFragment
import com.example.petmatcher.home.HomeFragment
import com.example.petmatcher.info.InfoFragment
import com.example.petmatcher.petdetails.DetailsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * @author Lisa Watkins
 *
 * Main activity that hosts navigation graph
 */
class MainActivity : AppCompatActivity(), HomeFragment.OnFragmentInteractionListener,
    InfoFragment.OnFragmentInteractionListener,
    FavoritesFragment.OnFragmentInteractionListener,
    DetailsFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpBottomNavMenu(findNavController(R.id.nav_host_fragment))
    }

    private fun setUpBottomNavMenu(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav?.setupWithNavController(navController)
    }
}
