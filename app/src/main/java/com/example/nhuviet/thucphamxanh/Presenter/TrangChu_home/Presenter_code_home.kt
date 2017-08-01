package com.example.nhuviet.thucphamxanh.Presenter.TrangChu_home

import com.example.nhuviet.thucphamxanh.Model.Model_Trangchu_SanPham.Model_SanPham
import com.example.nhuviet.thucphamxanh.Model.Model_Trangchu_home.Model_Home
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.QuangCao
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.SanPhamUaChuong_SP
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Sanpham
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.ThuongHieu
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.ThuongHieuvaSanpham
import com.example.nhuviet.thucphamxanh.Model.QuangCao.Model_QuangCao
import com.example.nhuviet.thucphamxanh.View.TrangChu.View_home

import java.util.ArrayList

/**
 * Created by nhuvi on 06/06/2017.
 */

class Presenter_code_home(internal var view_home: View_home) : IPersenter_Home {
    internal var model_home: Model_Home
    internal var model_sanPham: Model_SanPham
    internal var model_quangCao: Model_QuangCao

    init {
        model_home = Model_Home()
        model_sanPham = Model_SanPham()
        model_quangCao = Model_QuangCao()
    }

    override fun LaydanhsachthuonghieusanPham() {

        val thuongHieuvaSanphams = ArrayList<ThuongHieuvaSanpham>()

        val quangCaos = model_quangCao.LayDanhSach_quangcao("Laydanhsachquangcao")
        val thuongHieuList = model_home.LayDanhSachThuongHieuThucpham("Laydanhsachthuonghieu")
        val sanPham_UaChuongs = model_sanPham.LayDanhSachSanPham_UC("LaydanhsachThucPhamUuchuong")


        val thuongHieuvaSanpham = ThuongHieuvaSanpham()

        thuongHieuvaSanpham.thuongHieus = thuongHieuList
        thuongHieuvaSanpham.quangCaos = quangCaos
        thuongHieuvaSanpham.sanPhamUaChuongs = sanPham_UaChuongs
        thuongHieuvaSanpham.tenSP = "Các thương hiệu sản phẩm Thực Phẩm Xanh"
        thuongHieuvaSanpham.tenLoaiSP = "Các loại thực phẩm Mới"
        thuongHieuvaSanpham.isSP_UaChuong = true
        thuongHieuvaSanphams.add(thuongHieuvaSanpham)



        if (thuongHieuList.size > 0) {
            view_home.hienthidanhsachthuonghieu(thuongHieuvaSanphams)
        }


    }
}
