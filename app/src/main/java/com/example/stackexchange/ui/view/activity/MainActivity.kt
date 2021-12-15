package com.example.stackexchange.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.stackexchange.R
import com.example.stackexchange.data.model.Item
import com.example.stackexchange.ui.view.fragment.SearchFragment
import com.example.stackexchange.ui.view.fragment.UserDetailsFragment

class MainActivity : AppCompatActivity() {

    private val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment()
    }

    fun loadFragment() {
        manager.beginTransaction().replace(R.id.container, SearchFragment()).commit()
    }

   fun loadFragment(item: Item) {
        manager.beginTransaction().replace(R.id.container,
            UserDetailsFragment.newInstance(item)).commit()
   }
}