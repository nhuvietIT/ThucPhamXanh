package com.example.nhuviet.thucphamxanh.Model.ObjectClass

/**
 * Created by nhuvi on 06/06/2017.
 */

class ThuongHieuvaSanpham {
    var tenSP: String
    var tenLoaiSP: String
    var thuongHieus: List<ThuongHieu>
    var sanphams: List<Sanpham>
    var quangCaos: List<QuangCao>
    var sanPhamUaChuongs: List<SanPhamUaChuong_SP>
    var isSP_UaChuong: Boolean = false


}
