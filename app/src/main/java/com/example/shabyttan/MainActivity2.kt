package com.example.shabyttan

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch


class MainActivity2 : AppCompatActivity() {
    lateinit var bottomNav: BottomNavigationView
    private val searchFragment = SearchFragment()
    private val refreshFragment = MainFragment()
    private val profileFragment = ProfileFragment()

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        hideSystemUI()
        loadFragment(MainFragment())
        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.search -> loadFragment(searchFragment)
                R.id.home -> loadFragment(refreshFragment)
                R.id.profile -> loadFragment(profileFragment)
                else -> {loadFragment(profileFragment)}
            }
        }

//        val db = Room.databaseBuilder(
//            applicationContext,
//            FavoriteDatabase::class.java, "favorites_database"
//        ).allowMainThreadQueries().build()
//        val favoriteDao = db.favoriteDao()
//        lifecycleScope.launch {
//            favoriteDao.getFavorites().collect { favoriteList ->
//                if (favoriteList.isNotEmpty()) {
//                    Log.d("TAG", favoriteList.toString())
//                }
//            }
//        }
    }




    private fun loadFragment(fragment: Fragment): Boolean {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        return true
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(
            window,
            window.decorView.findViewById(android.R.id.content)
        ).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())

            // When the screen is swiped up at the bottom
            // of the application, the navigationBar shall
            // appear for some time
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}
