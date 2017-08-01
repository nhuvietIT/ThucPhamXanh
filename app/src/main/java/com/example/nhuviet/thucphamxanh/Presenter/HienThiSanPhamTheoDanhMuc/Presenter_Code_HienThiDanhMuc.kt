package com.example.nhuviet.thucphamxanh.Presenter.HienThiSanPhamTheoDanhMuc

import android.content.Context
import android.view.View
import android.widget.ProgressBar

import com.example.nhuviet.thucphamxanh.Model.GioHang.Model_GioHang
import com.example.nhuviet.thucphamxanh.Model.HienThiSanPhamTheoDanhMuc.Model_HienThiSanPhamTheoDanhMuc
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Chitiet_SP
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.SanPham_DMUC
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Sanpham
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Timkiem
import com.example.nhuviet.thucphamxanh.View.TrangChu.View_Hienthi_SanPham_TheoDanhMuc

import java.util.ArrayList

/**
 * Created by nhuvi on 06/06/2017.
 */

class Presenter_Code_HienThiDanhMuc(internal var view_hienthi_sanPham_theoDanhMuc: View_Hienthi_SanPham_TheoDanhMuc) : IPresenter_HienThiDanhMuc {
    internal var model_hienThiSanPhamTheoDanhMuc: Model_HienThiSanPhamTheoDanhMuc
    internal var model_gioHang: Model_GioHang

    init {
        model_hienThiSanPhamTheoDanhMuc = Model_HienThiSanPhamTheoDanhMuc()
        model_gioHang = Model_GioHang()
    }

    override fun LaydanhsachsanphamtheomaloaiSP(maLSP: Int, kiemtra: Boolean) {
        var sanphamList: List<SanPham_DMUC> = ArrayList()
        if (kiemtra) {
            sanphamList = model_hienThiSanPhamTheoDanhMuc.LaySanPhamTheoMaLoaiSP(maLSP, "LaydanhsachDanhMucThucPhamHuuCoUC", 0)
        }

        if (sanphamList.size > 0) {
            view_hienthi_sanPham_theoDanhMuc.HienthiDanhSach_SanPhan(sanphamList)
        } else {
            view_hienthi_sanPham_theoDanhMuc.Loi_HienthiDanhSach_SanPhan()
        }

    }

    fun LaydanhsachsanphamtheomaloaiSP_LoadMore(maLSP: Int, kiemtra: Boolean, limit: Int, progressBar: ProgressBar): List<SanPham_DMUC> {
        progressBar.visibility = View.VISIBLE
        var sanphamList: List<SanPham_DMUC> = ArrayList()
        if (kiemtra) {
            sanphamList = model_hienThiSanPhamTheoDanhMuc.LaySanPhamTheoMaLoaiSP(maLSP, "LaydanhsachDanhMucThucPhamHuuCoUC", limit)
        }

        if (sanphamList.size != 0) {
            progressBar.visibility = View.GONE
        }
        return sanphamList
    }

    fun Demsanphamtronggiohang(context: Context): Int {
        model_gioHang.MoKetNoiSQL(context)
        val chitiet_sps = model_gioHang.LayDanhSachGioHang()
        return chitiet_sps.size
    }


}
