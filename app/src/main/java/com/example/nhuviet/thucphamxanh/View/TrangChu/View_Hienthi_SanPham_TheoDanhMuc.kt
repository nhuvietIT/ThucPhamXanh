package com.example.nhuviet.thucphamxanh.View.TrangChu

import com.example.nhuviet.thucphamxanh.Model.ObjectClass.SanPham_DMUC
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Sanpham

/**
 * Created by nhuvi on 09/06/2017.
 */

interface View_Hienthi_SanPham_TheoDanhMuc {
    fun HienthiDanhSach_SanPhan(sanphamList: List<SanPham_DMUC>)
    fun Loi_HienthiDanhSach_SanPhan()
}
