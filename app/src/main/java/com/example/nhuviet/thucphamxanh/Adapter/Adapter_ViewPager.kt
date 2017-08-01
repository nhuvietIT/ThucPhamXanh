package com.example.nhuviet.thucphamxanh.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import com.example.nhuviet.thucphamxanh.View.HienThiSanPhamTheoDanhMuc.HienThiSanPhamTheoDanhMuc
import com.example.nhuviet.thucphamxanh.View.TrangChu.Frangment.Fragment_CuaHang
import com.example.nhuviet.thucphamxanh.View.TrangChu.Frangment.Fragment_Home
import com.example.nhuviet.thucphamxanh.View.TrangChu.Frangment.Fragment_KhuyenMai
import com.example.nhuviet.thucphamxanh.View.TrangChu.Frangment.Fragment_SanPham

import java.util.ArrayList

/**
 * Created by nhuvi on 16/05/2017.
 */

class Adapter_ViewPager(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    internal var fragmentList: MutableList<Fragment> = ArrayList()
    internal var titlefragmentList: MutableList<String> = ArrayList()

    init {
        fragmentList.add(Fragment_Home())
        fragmentList.add(Fragment_SanPham())
        fragmentList.add(Fragment_CuaHang())
        fragmentList.add(Fragment_KhuyenMai())


        titlefragmentList.add("Home")
        titlefragmentList.add("Sản Phẩm")
        titlefragmentList.add("Cửa Hàng")
        titlefragmentList.add("Khuyến Mãi")

    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titlefragmentList[position]
    }
}

