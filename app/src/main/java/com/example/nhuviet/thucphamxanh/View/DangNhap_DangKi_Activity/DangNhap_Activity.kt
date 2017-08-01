package com.example.nhuviet.thucphamxanh.View.DangNhap_DangKi_Activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.Button

import com.example.nhuviet.thucphamxanh.Adapter.Adapter_ViewPagerDangNhap
import com.example.nhuviet.thucphamxanh.R

/**
 * Created by nhuvi on 16/05/2017.
 */

class DangNhap_Activity : AppCompatActivity() {
    internal var tabLayout: TabLayout
    internal var viewPager: ViewPager
    internal var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_dangnhap)

        tabLayout = findViewById(R.id.tab_dangnhap) as TabLayout
        viewPager = findViewById(R.id.vp_dangnhap) as ViewPager
        toolbar = findViewById(R.id.toolbar_dangnhap) as Toolbar

        setSupportActionBar(toolbar)

        val adapter_viewPagerDangNhap = Adapter_ViewPagerDangNhap(supportFragmentManager)
        viewPager.adapter = adapter_viewPagerDangNhap
        adapter_viewPagerDangNhap.notifyDataSetChanged()

        tabLayout.setupWithViewPager(viewPager)

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
