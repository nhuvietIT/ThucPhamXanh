package com.example.nhuviet.thucphamxanh.Presenter.Rauanla

import com.example.nhuviet.thucphamxanh.Model.HienThiSanPhamTheoDanhMuc.Model_HienThiSanPhamTheoDanhMuc
import com.example.nhuviet.thucphamxanh.Model.Model_Trangchu_SanPham.Model_SanPham
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.CacLoai_Rau
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.G_SanPham_Tren
import com.example.nhuviet.thucphamxanh.View.RauAnLa.RauAnLa
import com.example.nhuviet.thucphamxanh.View.RauAnLa.ViewRauAnLa

import java.util.ArrayList

/**
 * Created by nhuvi on 22/06/2017.
 */

class Presenter_code_rauanla(internal var view: ViewRauAnLa) : IPresenter_rauanla {
    internal var model_sanPham: Model_HienThiSanPhamTheoDanhMuc

    init {
        model_sanPham = Model_HienThiSanPhamTheoDanhMuc()
    }

    override fun laydanhsach_Rauanla(malsp: Int) {
        val cacLoai_rauList = model_sanPham.LaySanPhammaloairau(malsp, "Laydanhsachrauanla")

        if (cacLoai_rauList.size > 0) {
            view.hienthidanhsach_Rauanla(cacLoai_rauList)

        }
    }
}
