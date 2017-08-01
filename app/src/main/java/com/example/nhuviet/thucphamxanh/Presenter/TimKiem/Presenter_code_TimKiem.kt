package com.example.nhuviet.thucphamxanh.Presenter.TimKiem

import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Timkiem
import com.example.nhuviet.thucphamxanh.Model.TimKiem.Model_TimKiem
import com.example.nhuviet.thucphamxanh.View.TimKiem.View_Timkiem

import java.util.ArrayList

/**
 * Created by nhuvi on 17/06/2017.
 */

class Presenter_code_TimKiem(internal var view_timkiem: View_Timkiem) : IPresenter_TimKiem {
    internal var model_timKiem: Model_TimKiem

    init {
        model_timKiem = Model_TimKiem()
    }

    override fun Timkiem_TenSanPham(tenSanPham: String, limit: Int) {
        val timkiemList = model_timKiem.LayDanhSachtimKiemSP(tenSanPham, "TimKiemSanPham", limit)

        if (timkiemList.size > 0) {
            view_timkiem.TimkiemThanhCong(timkiemList)
        } else {
            view_timkiem.timkiemThatBai()
        }
    }

}
