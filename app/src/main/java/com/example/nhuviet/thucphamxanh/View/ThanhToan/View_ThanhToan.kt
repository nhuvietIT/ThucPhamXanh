package com.example.nhuviet.thucphamxanh.View.ThanhToan

import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Chitiet_SP

/**
 * Created by nhuvi on 17/06/2017.
 */

interface View_ThanhToan {
    fun DatHangThanhCong()
    fun DatHangThatBai()
    fun HienThiDanhSachSP_GoiHang(chitietSpList: List<Chitiet_SP>)
}
