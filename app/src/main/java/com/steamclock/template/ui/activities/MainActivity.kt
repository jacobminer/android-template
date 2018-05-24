package com.steamclock.template.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.steamclock.template.R

/* Acts as the main entry point to the app, but does little else.
 * Navigation library takes care of showing the right content at the right time
 * It also handles back vs up for us. */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        // setup shared navigation elements like a hamburger menu or bottom bar
        // navigation will be handled by the resource/nav_graph and fragments
    }

    // necessary override for navigation library
    override fun onSupportNavigateUp() = findNavController(R.id.host_fragment).navigateUp()

}
