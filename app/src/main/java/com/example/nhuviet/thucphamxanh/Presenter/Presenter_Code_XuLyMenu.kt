package com.example.nhuviet.thucphamxanh.Presenter

import com.example.nhuviet.thucphamxanh.ConnectInternet.Download_Json
import com.example.nhuviet.thucphamxanh.Model.Model_DangNhap_DangKi.Model_DangNhap
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.LoaiSanPham
import com.example.nhuviet.thucphamxanh.Model.TrangChu.XulyMenu.XuLy_JsonMenu
import com.example.nhuviet.thucphamxanh.View.TrangChu.Activity_TrangChu
import com.example.nhuviet.thucphamxanh.View.TrangChu.View_XuLyMenu
import com.facebook.AccessToken

import java.util.ArrayList
import java.util.HashMap
import java.util.concurrent.ExecutionException

/**
 * Created by nhuvi on 16/05/2017.
 */

class Presenter_Code_XuLyMenu(internal var view_xuLyMenu: View_XuLyMenu) : Presenter_XuLyMenu {

    override fun LayDanhSachMenu() {
        val loaiSanPhamList: List<LoaiSanPham>
        var datajson = ""
        val attrs = ArrayList<HashMap<String, String>>()
        // get
        //        String duongdan="http://192.168.218.2:8082/data_zalada/loaisanpham.php?maLoaiCha=1";
        //        Download_Json download_json = new Download_Json(duongdan);
        //        download_json.execute();
        //post
        val duongdan = Activity_TrangChu.Server_Name

        val shham = HashMap<String, String>()
        shham.put("ham", "Laydanhsachmenu")

        val mapmaloaicha = HashMap<String, String>()
        mapmaloaicha.put("maLoaiCha", "1")

        attrs.add(mapmaloaicha)
        attrs.add(shham)

        val download_json = Download_Json(duongdan, attrs)
        download_json.execute()

        try {
            datajson = download_json.get()
            val xuLy_jsonMenu = XuLy_JsonMenu()
            loaiSanPhamList = xuLy_jsonMenu.parseJsonMenu(datajson)
            view_xuLyMenu.HienThiDanhSachMenu(loaiSanPhamList)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }

    }

    override fun Laytennguoidung_facebook(): AccessToken {
        val model_dangNhap = Model_DangNhap()
        val accessToken = model_dangNhap.Laytoken_facebook()
        return accessToken
    }
}
