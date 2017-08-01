package com.example.nhuviet.thucphamxanh.Presenter.ChiTietSanPham

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.example.nhuviet.thucphamxanh.R
import com.squareup.picasso.Picasso

/**
 * Created by nhuvi on 15/06/2017.
 */

class Frament_SliderSanPham : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.layout_frament_sliderchitiecsanpham, container, false)

        val bundle = arguments
        val linhhinh = bundle.getString("linhhinh")

        val imageView = v.findViewById(R.id.im_frament) as ImageView

        Picasso.with(context).load(linhhinh).into(imageView)

        return v
    }
}
