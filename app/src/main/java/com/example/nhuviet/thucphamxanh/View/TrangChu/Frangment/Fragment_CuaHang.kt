package com.example.nhuviet.thucphamxanh.View.TrangChu.Frangment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.nhuviet.thucphamxanh.R

/**
 * Created by nhuvi on 16/05/2017.
 */

class Fragment_CuaHang : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.layout_fragment_cuahang, container, false)
        return view
    }
}
