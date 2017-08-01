package com.example.nhuviet.thucphamxanh.View.GioHang

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView

import com.example.nhuviet.thucphamxanh.Adapter.Adapter_CR_GioHang
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Chitiet_SP
import com.example.nhuviet.thucphamxanh.Presenter.ChiTietSanPham.Presenter_code_chitiet
import com.example.nhuviet.thucphamxanh.Presenter.GioHang.PreSenTer_Code_gioHang
import com.example.nhuviet.thucphamxanh.R
import com.example.nhuviet.thucphamxanh.View.ThanhToan.View_ThanhToanActivity

/**
 * Created by nhuvi on 16/06/2017.
 */

class View_GioHang_Activity : AppCompatActivity(), View_giohang, View.OnClickListener {
    internal var recyclerView: RecyclerView
    internal var onPause = false
    internal var preSenTer_code_gioHang: PreSenTer_Code_gioHang
    internal var presenter_code_chitiet: Presenter_code_chitiet
    internal var toolbar: Toolbar
    internal var muangay_giohang: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_giohang)

        recyclerView = findViewById(R.id.Rc_giohang) as RecyclerView
        muangay_giohang = findViewById(R.id.muangay_giohang) as Button

        muangay_giohang.setOnClickListener(this)
        toolbar = findViewById(R.id.toolbar_gh) as Toolbar
        toolbar.title = " Giỏ hàng của bạn"
        setSupportActionBar(toolbar)
        preSenTer_code_gioHang = PreSenTer_Code_gioHang(this)
        preSenTer_code_gioHang.LayDanhSachSP_giohang(this)

        presenter_code_chitiet = Presenter_code_chitiet()

        // hien thị nut back
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }
    }

    override fun hienthiSp_giohang(chitietSps: MutableList<Chitiet_SP>) {
        val layoutManager = LinearLayoutManager(this)
        val gioHang = Adapter_CR_GioHang(this, chitietSps)
        recyclerView.adapter = gioHang
        recyclerView.layoutManager = layoutManager
    }


    override fun onClick(v: View) {
        val id = v.id
        when (id) {
            R.id.muangay_giohang -> {
                val intentthanhtoan = Intent(this, View_ThanhToanActivity::class.java)
                startActivity(intentthanhtoan)
            }
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
}
