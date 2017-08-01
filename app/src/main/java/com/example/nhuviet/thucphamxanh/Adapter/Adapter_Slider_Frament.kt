package com.example.nhuviet.thucphamxanh.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by nhuvi on 15/06/2017.
 */

class Adapter_Slider_Frament(fm: FragmentManager, internal var fragmentList: List<Fragment>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }
}
