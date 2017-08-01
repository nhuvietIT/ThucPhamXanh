package com.example.nhuviet.thucphamxanh.Presenter.ThanhToan

import android.content.Context

import com.example.nhuviet.thucphamxanh.Model.GioHang.Model_GioHang
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Chitiet_SP
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.HoaDon
import com.example.nhuviet.thucphamxanh.Model.ThanhToan.ModeThanhToan
import com.example.nhuviet.thucphamxanh.View.ThanhToan.View_ThanhToan

/**
 * Created by nhuvi on 17/06/2017.
 */

class Presenter_code_ThanhToan(internal var view_thanhToan: View_ThanhToan, context: Context) : Ipresenter_ThanhToan {
    internal var modeThanhToan: ModeThanhToan
    internal var model_gioHang: Model_GioHang
    internal var chitiet_sps: List<Chitiet_SP>

    init {
        modeThanhToan = ModeThanhToan()
        model_gioHang = Model_GioHang()
        model_gioHang.MoKetNoiSQL(context)
    }

    override fun ThemHoaDon(hoaDon: HoaDon) {
        val kiemtra = modeThanhToan.ThemHoaDon(hoaDon)
        if (kiemtra) {
            view_thanhToan.DatHangThanhCong()
            val dem = chitiet_sps.size
            for (i in 0..dem - 1) {
                model_gioHang.XoaSanPham_GioHang(chitiet_sps[i].maSanPham)
            }
        } else {
            view_thanhToan.DatHangThatBai()
        }

    }

    override fun LayDanhSachSP_GoiHang() {

        chitiet_sps = model_gioHang.LayDanhSachGioHang()
        view_thanhToan.HienThiDanhSachSP_GoiHang(chitiet_sps)

    }
}
