package com.example.nhuviet.thucphamxanh.View.TrangChu.Frangment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.nhuviet.thucphamxanh.Adapter.Adapter_CR_H_Home
import com.example.nhuviet.thucphamxanh.Adapter.Adapter_RC_H_ThuongHieu
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Sanpham
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.ThuongHieu
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.ThuongHieuvaSanpham
import com.example.nhuviet.thucphamxanh.Presenter.TrangChu_home.Presenter_code_home
import com.example.nhuviet.thucphamxanh.R
import com.example.nhuviet.thucphamxanh.View.TrangChu.View_home

import java.util.ArrayList

/**
 * Created by nhuvi on 16/05/2017.
 */

class Fragment_Home : Fragment(), View_home {
    internal var recyclerView: RecyclerView
    internal var thuongHieuvaSanphamList: List<ThuongHieuvaSanpham>
    internal var presenter_code_home: Presenter_code_home
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.layout_fragment_home, container, false)

        recyclerView = view.findViewById(R.id.rc_home) as RecyclerView

        presenter_code_home = Presenter_code_home(this)

        thuongHieuvaSanphamList = ArrayList<ThuongHieuvaSanpham>()

        presenter_code_home.LaydanhsachthuonghieusanPham()

        return view
    }

    override fun hienthidanhsachthuonghieu(thuongHieuvaSanphams: List<ThuongHieuvaSanpham>) {

        thuongHieuvaSanphamList = thuongHieuvaSanphams

        val adapter_cr_h_home = Adapter_CR_H_Home(context, thuongHieuvaSanphamList)
        val layoutManager = LinearLayoutManager(context)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter_cr_h_home
        adapter_cr_h_home.notifyDataSetChanged()

    }
}
