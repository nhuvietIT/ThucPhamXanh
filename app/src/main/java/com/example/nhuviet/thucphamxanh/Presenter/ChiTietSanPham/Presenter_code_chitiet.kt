package com.example.nhuviet.thucphamxanh.Presenter.ChiTietSanPham

import android.content.Context
import android.util.Log

import com.example.nhuviet.thucphamxanh.Model.GioHang.Model_GioHang
import com.example.nhuviet.thucphamxanh.Model.Model_ChiTiecSanPham.Model_ChiTietSanPham

import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Chitiet_SP
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Sanpham
import com.example.nhuviet.thucphamxanh.View.TrangChu.View_ChiTietSanpham

/**
 * Created by nhuvi on 13/06/2017.
 */

class Presenter_code_chitiet : IPresenter_chitiet {
    internal var view_chiTietSanpham: View_ChiTietSanpham
    internal var model_chiTietSanPham: Model_ChiTietSanPham
    internal var model_gioHang: Model_GioHang

    constructor(view_chiTietSanpham: View_ChiTietSanpham) {
        this.view_chiTietSanpham = view_chiTietSanpham
        model_chiTietSanPham = Model_ChiTietSanPham()
        model_gioHang = Model_GioHang()
    }

    constructor() {
        model_gioHang = Model_GioHang()
    }

    override fun LayDanhSachChiTiet_SP(maSP: Int) {
        val chitiet_sp = model_chiTietSanPham.LayDanhSach_ChiTietTheoMaloaiSP("Laydanhsachchitiecsanpham", maSP)

        if (chitiet_sp.maSanPham >= 0) {
            val linkhinhSP = chitiet_sp.hinhsanphannho.split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
            view_chiTietSanpham.HienSliderSanPham(linkhinhSP)
            view_chiTietSanpham.HienthiDanhSachChiTiec_SanPhan(chitiet_sp)
        }
    }

    override fun Themgiohang(sanpham: Chitiet_SP, context: Context) {
        model_gioHang.MoKetNoiSQL(context)
        val kiemtra = model_gioHang.ThemGioHang(sanpham)
        if (kiemtra) {
            view_chiTietSanpham.Themgiohangthanhcong()

        } else {
            view_chiTietSanpham.Themgiohangthatbai()

        }

    }

    fun Demsanphamtronggiohang(context: Context): Int {
        model_gioHang.MoKetNoiSQL(context)
        val chitiet_sps = model_gioHang.LayDanhSachGioHang()
        return chitiet_sps.size
    }
}
