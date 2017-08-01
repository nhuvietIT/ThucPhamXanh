package com.example.nhuviet.thucphamxanh.View.TrangChu.Frangment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.nhuviet.thucphamxanh.Adapter.Adapter_CR_SP_SanPham
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.G_SanPham_Tren
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.G_Thuonghieu_Rau_duoi
import com.example.nhuviet.thucphamxanh.Presenter.TrangChu_SanPham.Presenter_code_SanPham
import com.example.nhuviet.thucphamxanh.R
import com.example.nhuviet.thucphamxanh.View.TrangChu.View_SanPham

import java.util.ArrayList

/**
 * Created by nhuvi on 16/05/2017.
 */

class Fragment_SanPham : Fragment(), View_SanPham {
    internal var recyclerView_tren: RecyclerView//recyclerView_duoi;
    internal var gg_sanPhamTrenList: List<G_SanPham_Tren>
    internal var g_thuonghieu_rau_duoiList: List<G_Thuonghieu_Rau_duoi>
    internal var presenter_code_sanPham: Presenter_code_SanPham

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.layout_fragment_sanpham, container, false)

        recyclerView_tren = view.findViewById(R.id.recycler_sanphamtren) as RecyclerView

        presenter_code_sanPham = Presenter_code_SanPham(this)

        gg_sanPhamTrenList = ArrayList<G_SanPham_Tren>()
        g_thuonghieu_rau_duoiList = ArrayList<G_Thuonghieu_Rau_duoi>()

        presenter_code_sanPham.LaydanhsachSanPham_tren()

        return view

    }

    override fun Hienthidanhsach(g_sanPhamTrens: List<G_SanPham_Tren>) {

        gg_sanPhamTrenList = g_sanPhamTrens
        // RC Tren
        val adapter_cr_SP_sanPhamTren = Adapter_CR_SP_SanPham(context, g_sanPhamTrens)
        val layoutManager = LinearLayoutManager(context)

        recyclerView_tren.layoutManager = layoutManager
        recyclerView_tren.adapter = adapter_cr_SP_sanPhamTren
        adapter_cr_SP_sanPhamTren.notifyDataSetChanged()

    }

}
