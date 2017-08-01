package com.example.nhuviet.thucphamxanh.Presenter.TrangChu_SanPham

import android.util.Log

import com.example.nhuviet.thucphamxanh.Model.Model_Trangchu_SanPham.Model_SanPham
import com.example.nhuviet.thucphamxanh.Model.Model_Trangchu_home.Model_Home
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.CacLoai_Rau
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.CacLoai_Thit
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.G_SanPham_Tren
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.G_Thuonghieu_Rau_duoi
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.QuangCao
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.SanPhamUaChuong_SP
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.ThuongHieu
import com.example.nhuviet.thucphamxanh.Model.QuangCao.Model_QuangCao
import com.example.nhuviet.thucphamxanh.View.TrangChu.View_SanPham

import java.util.ArrayList

/**
 * Created by nhuvi on 02/06/2017.
 */

class Presenter_code_SanPham(internal var view_sanPham: View_SanPham) : IPresenter_SanPham {
    internal var model_sanPham: Model_SanPham
    internal var model_quangCao: Model_QuangCao

    init {
        model_sanPham = Model_SanPham()
        model_quangCao = Model_QuangCao()
    }

    override fun LaydanhsachSanPham_tren() {
        val sanPhamList = ArrayList<G_SanPham_Tren>()

        val quangCaos = model_quangCao.LayDanhSach_quangcao("Laydanhsachquangcao")
        val sanPham_UaChuongs = model_sanPham.LayDanhSachSanPham_UC("LaydanhsachThucPhamUuchuong")
        val cacLoai_thits = model_sanPham.LayDanhSachLoai_thit("Laydanhsachcacloaithit")
        val cacLoai_rauList = model_sanPham.LayDanhSachLoai_rau("Laydanhsachcacloairau")


        val g_sanPhamsTren = G_SanPham_Tren()
        g_sanPhamsTren.quangCaos = quangCaos
        g_sanPhamsTren.sanPhamUaChuong_sps = sanPham_UaChuongs
        g_sanPhamsTren.cacLoai_thits = cacLoai_thits
        g_sanPhamsTren.cacLoai_raus = cacLoai_rauList
        g_sanPhamsTren.ten_SPTen = "Các loại thực phẩm ưa chuộng nhất"
        g_sanPhamsTren.ten_giua = "Các loại rau - củ - quả"
        g_sanPhamsTren.ten_SPDuoi = "Các loại thịt"
        g_sanPhamsTren.isSP_UaChuong = true
        g_sanPhamsTren.isLoaiRau = true
        g_sanPhamsTren.isLoaithit = true
        sanPhamList.add(g_sanPhamsTren)

        //        List<SanPhamUaChuong_SP> sanPham_UaChuongs1 = model_sanPham.LayDanhSachSanPham_UC(" ");
        //        List<CacLoai_Thit> cacLoai_thits1 = model_sanPham.LayDanhSachLoai_thit("Laydanhsachcacloaithit");
        //        List<CacLoai_Rau> cacLoai_rauList1 = model_sanPham.LayDanhSachLoai_rau("Laydanhsachcacloairau");
        //
        //        G_SanPham_Tren g_sanPhamsTrena = new G_SanPham_Tren();
        //        g_sanPhamsTrena.setSanPhamUaChuong_sps(sanPham_UaChuongs1);
        //        g_sanPhamsTrena.setCacLoai_thits(cacLoai_thits1);
        //        g_sanPhamsTrena.setCacLoai_raus(cacLoai_rauList1);
        //        g_sanPhamsTrena.setTen_SPTen("");
        //        g_sanPhamsTrena.setTen_giua("Các loại ca");
        //        g_sanPhamsTrena.setTen_SPDuoi("Các loại thịá");
        //        g_sanPhamsTrena.setSP_UaChuong(true);
        //        sanPhamList.add(g_sanPhamsTrena);


        if (sanPham_UaChuongs.size > 0 && cacLoai_thits.size > 0 && cacLoai_rauList.size > 0) {
            view_sanPham.Hienthidanhsach(sanPhamList)
        }

    }

    //    @Override
    //    public void LaydanhsachSanPham_duoi() {
    //        List<G_Thuonghieu_Rau_duoi> thuonghieu_rau_duoiList = new ArrayList<>();
    //
    //
    //        List<ThuongHieu> thuongHieuList = model_home.LayDanhSachThuongHieuThucpham("Laydanhsachthuonghieu");
    //        List<CacLoai_Rau> cacLoai_rauList = model_sanPham.LayDanhSachLoai_rau("Laydanhsachcacloairau");
    //
    //        G_Thuonghieu_Rau_duoi g_thuonghieu_rau_duoi = new G_Thuonghieu_Rau_duoi();
    //        g_thuonghieu_rau_duoi.setThuongHieus(thuongHieuList);
    //        g_thuonghieu_rau_duoi.setCacLoai_raus(cacLoai_rauList);
    //        g_thuonghieu_rau_duoi.setTen_SPTen("Thực Phẩm Thương Hiệu");
    //        g_thuonghieu_rau_duoi.setTen_SPDuoi("Các loại rau");
    //        thuonghieu_rau_duoiList.add(g_thuonghieu_rau_duoi);
    //
    //
    //
    //        if(thuongHieuList.size() > 0 && cacLoai_rauList.size() > 0 )
    //        {
    //            view_sanPham.Hienthidanhsach_duoi(thuonghieu_rau_duoiList);
    //        }
    //
    //
    //    }
}
