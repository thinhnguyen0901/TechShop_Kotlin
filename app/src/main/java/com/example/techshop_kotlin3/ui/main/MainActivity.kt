package com.example.techshop_kotlin3.ui.main


import android.os.Bundle
import android.widget.Button
import com.example.techshop_kotlin3.R
import dagger.android.support.DaggerAppCompatActivity


class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.container, Man3Fragment()).commit()

        val button1 = findViewById<Button>(R.id.man1_btn)
        val button2 = findViewById<Button>(R.id.man2_btn)
        val button3 = findViewById<Button>(R.id.man3_btn)

        button1.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.container, Man1Fragment())
                .commit()
        }

        button2.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.container, Man2Fragment())
                .commit()
        }

        button3.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.container, Man3Fragment())
                .commit()
        }

    }

}