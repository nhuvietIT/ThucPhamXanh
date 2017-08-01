package com.example.nhuviet.thucphamxanh.View.TrangChu

import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Sanpham
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.ThuongHieu
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.ThuongHieuvaSanpham

/**
 * Created by nhuvi on 06/06/2017.
 */

interface View_home {
    fun hienthidanhsachthuonghieu(thuongHieuvaSanphams: List<ThuongHieuvaSanpham>)
}
