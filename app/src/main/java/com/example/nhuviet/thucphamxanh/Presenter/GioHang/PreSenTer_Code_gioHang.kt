package com.example.nhuviet.thucphamxanh.Presenter.GioHang

import android.content.Context

import com.example.nhuviet.thucphamxanh.Model.GioHang.Model_GioHang
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Chitiet_SP
import com.example.nhuviet.thucphamxanh.View.GioHang.View_giohang

import java.util.ArrayList

/**
 * Created by nhuvi on 16/06/2017.
 */

class PreSenTer_Code_gioHang(internal var view_giohang: View_giohang) : IPresenter_gioHang {
    internal var model_gioHang: Model_GioHang

    init {
        model_gioHang = Model_GioHang()
    }

    override fun LayDanhSachSP_giohang(context: Context) {
        model_gioHang.MoKetNoiSQL(context)
        val chitiet_sps = model_gioHang.LayDanhSachGioHang()
        if (chitiet_sps.size > 0) {
            view_giohang.hienthiSp_giohang(chitiet_sps)
        }


    }
}
