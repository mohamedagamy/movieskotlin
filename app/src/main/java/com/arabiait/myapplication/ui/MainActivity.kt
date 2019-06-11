package com.arabiait.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.arabiait.myapplication.R
import com.arabiait.myapplication.pojo.PojoTest
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import io.realm.Realm
import io.realm.DynamicRealm
import io.realm.DynamicRealmObject
import io.realm.RealmConfiguration
import io.realm.kotlin.createObject
import io.realm.kotlin.delete




class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var realm: Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        replaceFragment(R.id.nav_upcoming)    }

    private fun testRealm() {
        //val realmConfig = RealmConfiguration.Builder().build()
        //val realm = DynamicRealm.getInstance(realmConfig)
        realm = Realm.getDefaultInstance()


        val pojoTestList = listOf(
            PojoTest("100",100),
            PojoTest("100",200),
            PojoTest("100",300),
            PojoTest("100",400),
            PojoTest("100",500),
            PojoTest("100",600),
            PojoTest("100",700),
            PojoTest("100",800),
            PojoTest("100",900),
            PojoTest("100",1000))

        realm.executeTransaction { realm ->

            for (item in pojoTestList){
                val person = realm.createObject<PojoTest>()
                person.name = item.name
                person.age = item.age
            }

        }
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
        replaceFragment(item.itemId)
        return true
    }

    private fun replaceFragment(item: Int) {
        var fragment = MoviesFragment()
        val ft = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.main_container, fragment, item.toString())
        // Complete the changes added above
        ft.commit()

        drawer_layout.closeDrawer(GravityCompat.START)
    }

    override fun onDestroy() {
        realm.close()
        super.onDestroy()

    }


}
