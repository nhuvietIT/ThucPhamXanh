package com.example.nhuviet.thucphamxanh.Presenter.ChiTietSanPham

import android.content.Context

import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Chitiet_SP
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Sanpham

/**
 * Created by nhuvi on 13/06/2017.
 */

interface IPresenter_chitiet {
    fun LayDanhSachChiTiet_SP(maSP: Int)
    fun Themgiohang(sanpham: Chitiet_SP, context: Context)
}
