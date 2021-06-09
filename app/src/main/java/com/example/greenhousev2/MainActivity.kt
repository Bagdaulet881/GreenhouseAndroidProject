package com.example.greenhousev2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val fragment: Fragment = StartFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment)
            .commitAllowingStateLoss()
    }

}


