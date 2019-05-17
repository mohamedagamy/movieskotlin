package com.arabiait.myapplication.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.arabiait.myapplication.R
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        var fragment:Fragment
        when (item.itemId) {
            R.id.nav_top_rated -> {
                // Handle the camera action
                Toast.makeText(this,"Top Rated",Toast.LENGTH_SHORT).show()
            }
            R.id.nav_now_playing -> {
                Toast.makeText(this,"Now Playing",Toast.LENGTH_SHORT).show()
            }
            R.id.nav_popular -> {
                Toast.makeText(this,"Popular",Toast.LENGTH_SHORT).show()
            }
            R.id.nav_latest -> {
                Toast.makeText(this,"Latest",Toast.LENGTH_SHORT).show()
            }
            R.id.nav_upcoming -> {
                Toast.makeText(this,"Upcoming",Toast.LENGTH_SHORT).show()
                fragment = MoviesFragment()
            }

            else->  {
                fragment = MoviesFragment()
            }
        }

        val ft = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.main_container, MoviesFragment())
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit()

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }



}
