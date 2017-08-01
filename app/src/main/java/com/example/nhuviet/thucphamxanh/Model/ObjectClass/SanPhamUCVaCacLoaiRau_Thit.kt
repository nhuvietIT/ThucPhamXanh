package com.example.nhuviet.thucphamxanh.Model.ObjectClass

/**
 * Created by nhuvi on 02/06/2017.
 */

class SanPhamUCVaCacLoaiRau_Thit {

    var cacLoairau_ListUC: List<CacloaiRauVacacloaiThit>
    var tenSP: String
    internal var taopnoibat: String
    var sanpham_ListUC: List<Sanpham>

    fun gettenloaiSP(): String {
        return taopnoibat
    }

    fun setToptenloaiSP(taopnoibat: String) {
        this.taopnoibat = taopnoibat
    }


}
