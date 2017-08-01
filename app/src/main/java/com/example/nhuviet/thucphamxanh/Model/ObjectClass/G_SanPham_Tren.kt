package com.example.nhuviet.thucphamxanh.Model.ObjectClass

/**
 * Created by nhuvi on 08/06/2017.
 */

class G_SanPham_Tren {
    var sanPhamUaChuong_sps: List<SanPhamUaChuong_SP>
    var cacLoai_thits: List<CacLoai_Thit>
    var cacLoai_raus: List<CacLoai_Rau>
    var quangCaos: List<QuangCao>
    var ten_SPTen: String
    var ten_giua: String
    var ten_SPDuoi: String
    var isLoaiRau: Boolean = false
    var isSP_UaChuong: Boolean = false
    var isLoaithit: Boolean = false

}
