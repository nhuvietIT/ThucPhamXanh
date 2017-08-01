package com.example.nhuviet.thucphamxanh.View.Manhinhchao

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.example.nhuviet.thucphamxanh.R
import com.example.nhuviet.thucphamxanh.View.TrangChu.Activity_TrangChu

/**
 * Created by nhuvi on 16/05/2017.
 */

class Activity_Manhinhchao : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_manhinhchao)

        val thread = Thread(Runnable {
            try {
                Thread.sleep(2200)
            } catch (e: Exception) {

            } finally {
                val intentmanhinhcho = Intent(this@Activity_Manhinhchao, Activity_TrangChu::class.java)
                startActivity(intentmanhinhcho)
            }
        })
        thread.start()
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}