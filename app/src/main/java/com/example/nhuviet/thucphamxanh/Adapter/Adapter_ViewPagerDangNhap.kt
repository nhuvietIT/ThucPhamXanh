package com.example.nhuviet.thucphamxanh.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import com.example.nhuviet.thucphamxanh.View.DangNhap_DangKi_Activity.Fragment.Fragment_DangNhap
import com.example.nhuviet.thucphamxanh.View.DangNhap_DangKi_Activity.Fragment.Fragment_Dangky

/**
 * Created by nhuvi on 16/05/2017.
 */

class Adapter_ViewPagerDangNhap(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> {
                val fragment_dangNhap = Fragment_DangNhap()
                return fragment_dangNhap
            }
            1 -> {
                val frament_dangky = Fragment_Dangky()
                return frament_dangky
            }

            else -> return null
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Đăng Nhập"
            1 -> return "Đăng Ký"

            else -> return null
        }
    }
}

