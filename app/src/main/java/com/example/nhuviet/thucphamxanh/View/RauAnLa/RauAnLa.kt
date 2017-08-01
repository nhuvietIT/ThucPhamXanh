package com.example.nhuviet.thucphamxanh.View.RauAnLa

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.Toast

import com.example.nhuviet.thucphamxanh.Adapter.Adapter_CR_H_Home
import com.example.nhuviet.thucphamxanh.Adapter.Adapter_Rauanla
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.CacLoai_Rau
import com.example.nhuviet.thucphamxanh.Presenter.Rauanla.Presenter_code_rauanla
import com.example.nhuviet.thucphamxanh.R

/**
 * Created by nhuvi on 22/06/2017.
 */

class RauAnLa : AppCompatActivity(), ViewRauAnLa {

    internal var recyclerView: RecyclerView
    internal var presenter_code_rauanla: Presenter_code_rauanla
    internal var toobar_ral: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_rauanla)

        recyclerView = findViewById(R.id.RC_raanla) as RecyclerView
        toobar_ral = findViewById(R.id.toobar_ral) as Toolbar


        val masp = intent.getIntExtra("malsp", 0)
        val tenSP = intent.getStringExtra("tenSP")


        toobar_ral.title = tenSP
        setSupportActionBar(toobar_ral)
        presenter_code_rauanla = Presenter_code_rauanla(this)
        presenter_code_rauanla.laydanhsach_Rauanla(masp)

        // hien thị nut back
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }

    }

    // su kien nut back
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            finish() // đóng hoạt động này và quay trở lại hoạt động xem trước (nếu có)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun hienthidanhsach_Rauanla(rau: List<CacLoai_Rau>) {

        val adapter_rauanla = Adapter_Rauanla(this, rau)
        val layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter_rauanla
        adapter_rauanla.notifyDataSetChanged()

    }
}
