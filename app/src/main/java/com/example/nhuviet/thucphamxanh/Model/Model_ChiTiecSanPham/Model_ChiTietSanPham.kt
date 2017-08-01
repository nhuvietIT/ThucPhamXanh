package com.example.nhuviet.thucphamxanh.Model.Model_ChiTiecSanPham

import com.example.nhuviet.thucphamxanh.ConnectInternet.Download_Json
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Chitiet_SP
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.SanPham_DMUC
import com.example.nhuviet.thucphamxanh.View.TrangChu.Activity_TrangChu

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList
import java.util.HashMap
import java.util.concurrent.ExecutionException

/**
 * Created by nhuvi on 13/06/2017.
 */

class Model_ChiTietSanPham {

    fun LayDanhSach_ChiTietTheoMaloaiSP(tenham: String, masp: Int): Chitiet_SP {

        val chitiet_sp = Chitiet_SP()
        val chitiet_sps = ArrayList<Chitiet_SP>()

        val attrs = ArrayList<HashMap<String, String>>()
        var datajson = ""
        //post
        val duongdan = Activity_TrangChu.Server_Name

        val shham = HashMap<String, String>()
        shham.put("ham", tenham)

        val shmasp = HashMap<String, String>()
        shmasp.put("maSanPham", masp.toString())

        attrs.add(shham)
        attrs.add(shmasp)

        val download_json = Download_Json(duongdan, attrs)
        download_json.execute()

        try {
            datajson = download_json.get()
            val jsonObject = JSONObject(datajson)
            val jsonArraysanpham = jsonObject.getJSONArray("ThucPhamXanh")

            val dem = jsonArraysanpham.length()

            for (i in 0..dem - 1) {
                val `object` = jsonArraysanpham.getJSONObject(i)

                chitiet_sp.maSanPham = `object`.getInt("maSanPham")
                chitiet_sp.tenLoaiSanPham = `object`.getString("tenLoaiSanPham")
                chitiet_sp.tenSanPham = `object`.getString("tenSanPham")
                chitiet_sp.tenThuongHieu = `object`.getString("tenThuongHieu")
                chitiet_sp.gia = `object`.getInt("gia")
                chitiet_sp.thongtin = `object`.getString("thongTin")
                chitiet_sp.hinhSanPham = `object`.getString("hinhLon")
                chitiet_sp.hinhsanphannho = `object`.getString("hinhNho")
                chitiet_sp.soluong = `object`.getInt("soLuong")

                chitiet_sps.add(chitiet_sp)
            }

        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return chitiet_sp


    }

}
