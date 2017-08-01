package com.example.nhuviet.thucphamxanh.Presenter.DangKi

import com.example.nhuviet.thucphamxanh.Model.Model_DangNhap_DangKi.Model_DangKi
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.NhanVien
import com.example.nhuviet.thucphamxanh.View.DangNhap_DangKi_Activity.Fragment.Fragment_Dangky
import com.example.nhuviet.thucphamxanh.View.DangNhap_DangKi_Activity.View_DangKi

/**
 * Created by nhuvi on 30/05/2017.
 */

class Presenter_Code_DangKi(internal var view_dangKi: View_DangKi) : Ipresenter_DangKi {
    internal var model_dangKi: Model_DangKi

    init {
        model_dangKi = Model_DangKi()
    }

    override fun ThucHienDangKi(nhanVien: NhanVien) {
        val kiemtra = model_dangKi.Dangkithanhvien(nhanVien)
        if (kiemtra!!) {
            view_dangKi.DangKiThanhCong()
        } else {
            view_dangKi.DAngKiThatBai()
        }


    }


}

