package com.example.nhuviet.thucphamxanh.View.TrangChu

import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Chitiet_SP
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.SanPham_DMUC

/**
 * Created by nhuvi on 12/06/2017.
 */

interface View_ChiTietSanpham {
    fun HienthiDanhSachChiTiec_SanPhan(chitietSps: Chitiet_SP)
    fun Loi_HienthiDanhSachChiTiec_SanPhan()
    fun Themgiohangthanhcong()
    fun Themgiohangthatbai()
    fun HienSliderSanPham(linkhinhsanpham: Array<String>)
}
