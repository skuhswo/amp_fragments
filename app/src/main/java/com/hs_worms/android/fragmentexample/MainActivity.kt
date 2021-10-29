package com.hs_worms.android.fragmentexample

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager

private const val BUTTONS_TAG = "buttons_fragment"
private const val DETAILS_TAG = "details_fragment"

class MainActivity : AppCompatActivity(), ButtonsFragment.ActivityCallbacks {

    private var fragmentContainer1: FragmentContainerView? = null
    private var fragmentContainer2: FragmentContainerView? = null

    private var landscapeMode = false

    private fun removeCurrentFragments() {
        val currentButtonsFragment =
            supportFragmentManager.findFragmentByTag(BUTTONS_TAG) as? ButtonsFragment
        val currentDetailsFragment =
            supportFragmentManager.findFragmentByTag(DETAILS_TAG) as? DetailsFragment
        currentButtonsFragment?.let {
            supportFragmentManager
                .beginTransaction()
                .remove(it)
                .commit()
        }
        currentDetailsFragment?.let {
            supportFragmentManager
                .beginTransaction()
                .remove(it)
                .commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentContainer1 = findViewById(R.id.fragment_container1)
        fragmentContainer2 = findViewById(R.id.fragment_container2)

        landscapeMode = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        supportFragmentManager.popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        removeCurrentFragments()

        val fragment = ButtonsFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container1, fragment, BUTTONS_TAG)
            .commit()

        if (landscapeMode) {
            val fragment = DetailsFragment.newInstance(0)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container2, fragment, DETAILS_TAG)
                .commit()
        }
    }

    override fun onNavigationSelected(elementNr: Int) {
        val currentDetailsFragment =
            supportFragmentManager.findFragmentByTag(DETAILS_TAG) as? DetailsFragment
        if (landscapeMode) {
            currentDetailsFragment?.update(elementNr)
        } else {
            if (currentDetailsFragment == null) {
                val fragment = DetailsFragment.newInstance(elementNr)
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container1, fragment, DETAILS_TAG)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

}
