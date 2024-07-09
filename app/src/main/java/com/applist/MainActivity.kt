package com.applist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.applist.databinding.ActivityMainBinding
import com.applist.ui.adapter.ViewPagerAdapter
import sudipta.`in`.newwaytocode.ui.fragment.tab.ApplicationFragment
import sudipta.`in`.newwaytocode.ui.fragment.tab.SettingsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpTab()

    }

    private fun setUpTab() {
        adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ApplicationFragment(), "Application")
        adapter.addFragment(SettingsFragment(), "Settings")
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}