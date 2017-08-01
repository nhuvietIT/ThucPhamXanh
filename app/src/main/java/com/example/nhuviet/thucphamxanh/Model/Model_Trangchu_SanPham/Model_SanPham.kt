package com.example.nhuviet.thucphamxanh.Model.Model_Trangchu_SanPham

import com.example.nhuviet.thucphamxanh.ConnectInternet.Download_Json
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.CacLoai_Rau
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.CacLoai_Thit
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.CacloaiRauVacacloaiThit
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.SanPhamUaChuong_SP
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Sanpham
import com.example.nhuviet.thucphamxanh.View.TrangChu.Activity_TrangChu

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList
import java.util.HashMap
import java.util.concurrent.ExecutionException

/**
 * Created by nhuvi on 01/06/2017.
 */

class Model_SanPham {

    // San pham
    fun LayDanhSachSanPhamTop(tenham: String): List<Sanpham> {
        val sanphamList = ArrayList<Sanpham>()

        val attrs = ArrayList<HashMap<String, String>>()
        var datajson = ""
        //post
        val duongdan = Activity_TrangChu.Server_Name

        val shham = HashMap<String, String>()
        shham.put("ham", tenham)

        attrs.add(shham)

        val download_json = Download_Json(duongdan, attrs)
        download_json.execute()

        try {
            datajson = download_json.get()
            val jsonObject = JSONObject(datajson)
            val jsonArraysanpham = jsonObject.getJSONArray("ThucPhamXanh")

            val dem = jsonArraysanpham.length()

            for (i in 0..dem - 1) {
                val sanpham = Sanpham()
                val `object` = jsonArraysanpham.getJSONObject(i)

                sanpham.maSanPham = `object`.getInt("maSanPham")
                sanpham.tenSanPham = `object`.getString("tenSanPham")
                sanpham.gia = `object`.getInt("gia")
                sanpham.hinhLon = `object`.getString("hinhLon")
                sanpham.hinhnho = `object`.getString("hinhNho")


                sanphamList.add(sanpham)

            }

        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return sanphamList
    }

    // cac loại rau
    fun LayDanhSachCacLoaiRauVaThit(tenham: String): List<CacloaiRauVacacloaiThit> {

        //post
        val duongdan = Activity_TrangChu.Server_Name

        val cacloaiRauVacacloaiThitList = ArrayList<CacloaiRauVacacloaiThit>()

        val attrs = ArrayList<HashMap<String, String>>()
        var datajson = ""


        val shham = HashMap<String, String>()
        shham.put("ham", tenham)


        attrs.add(shham)

        val download_json = Download_Json(duongdan, attrs)
        download_json.execute()

        try {
            datajson = download_json.get()
            val jsonObject = JSONObject(datajson)
            val jsonArraythuonghieu = jsonObject.getJSONArray("ThucPhamXanh")

            val dem = jsonArraythuonghieu.length()

            for (i in 0..dem - 1) {
                val cacloaiRauVacacloaiThit = CacloaiRauVacacloaiThit()
                val `object` = jsonArraythuonghieu.getJSONObject(i)

                cacloaiRauVacacloaiThit.tenSanPham = `object`.getString("tenSanPham")
                cacloaiRauVacacloaiThit.gia = `object`.getInt("gia")
                cacloaiRauVacacloaiThit.hinhLon = `object`.getString("hinhLon")


                cacloaiRauVacacloaiThitList.add(cacloaiRauVacacloaiThit)

            }

        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return cacloaiRauVacacloaiThitList
    }

    // cac loại Sp Ua chuộng
    fun LayDanhSachSanPham_UC(tenham: String): List<SanPhamUaChuong_SP> {

        //post
        val duongdan = Activity_TrangChu.Server_Name

        val sanPhamUaChuong = ArrayList<SanPhamUaChuong_SP>()

        val attrs = ArrayList<HashMap<String, String>>()
        var datajson = ""


        val shham = HashMap<String, String>()
        shham.put("ham", tenham)


        attrs.add(shham)

        val download_json = Download_Json(duongdan, attrs)
        download_json.execute()

        try {
            datajson = download_json.get()
            val jsonObject = JSONObject(datajson)
            val jsonArraythuonghieu = jsonObject.getJSONArray("ThucPhamXanh")

            val dem = jsonArraythuonghieu.length()

            for (i in 0..dem - 1) {
                val sanPhamUaChuongSp = SanPhamUaChuong_SP()
                val `object` = jsonArraythuonghieu.getJSONObject(i)

                sanPhamUaChuongSp.maLoaiSanPhamUC = `object`.getInt("maLoaiSanPhamUC")
                sanPhamUaChuongSp.tenloaiSanPhamUC = `object`.getString("tenLoaiSanPhamUC")
                sanPhamUaChuongSp.hinhLoaiSanPhamUC = `object`.getString("hinhThucPhamUC")


                sanPhamUaChuong.add(sanPhamUaChuongSp)

            }

        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return sanPhamUaChuong
    }

    // cac loại Sp thitj
    fun LayDanhSachLoai_thit(tenham: String): List<CacLoai_Thit> {

        //post
        val duongdan = Activity_TrangChu.Server_Name

        val cacLoai_thits = ArrayList<CacLoai_Thit>()

        val attrs = ArrayList<HashMap<String, String>>()
        var datajson = ""


        val shham = HashMap<String, String>()
        shham.put("ham", tenham)


        attrs.add(shham)

        val download_json = Download_Json(duongdan, attrs)
        download_json.execute()

        try {
            datajson = download_json.get()
            val jsonObject = JSONObject(datajson)
            val jsonArraythuonghieu = jsonObject.getJSONArray("ThucPhamXanh")

            val dem = jsonArraythuonghieu.length()

            for (i in 0..dem - 1) {
                val cacLoai_thit = CacLoai_Thit()
                val `object` = jsonArraythuonghieu.getJSONObject(i)

                cacLoai_thit.maSanPham = `object`.getInt("maSanPham")
                cacLoai_thit.tenSanPham = `object`.getString("tenSanPham")
                cacLoai_thit.gia = `object`.getInt("gia")
                cacLoai_thit.hinhLon = `object`.getString("hinhLon")


                cacLoai_thits.add(cacLoai_thit)

            }

        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return cacLoai_thits
    }

    // cac loại Sp rau
    fun LayDanhSachLoai_rau(tenham: String): List<CacLoai_Rau> {

        //post
        val duongdan = Activity_TrangChu.Server_Name

        val cacLoai_raus = ArrayList<CacLoai_Rau>()

        val attrs = ArrayList<HashMap<String, String>>()
        var datajson = ""


        val shham = HashMap<String, String>()
        shham.put("ham", tenham)


        attrs.add(shham)

        val download_json = Download_Json(duongdan, attrs)
        download_json.execute()

        try {
            datajson = download_json.get()
            val jsonObject = JSONObject(datajson)
            val jsonArraythuonghieu = jsonObject.getJSONArray("ThucPhamXanh")

            val dem = jsonArraythuonghieu.length()

            for (i in 0..dem - 1) {
                val cacLoai_rau = CacLoai_Rau()
                val `object` = jsonArraythuonghieu.getJSONObject(i)

                cacLoai_rau.maSanPham = `object`.getInt("maSanPham")
                cacLoai_rau.tenSanPham = `object`.getString("tenSanPham")
                cacLoai_rau.gia = `object`.getInt("gia")
                cacLoai_rau.hinhLon = `object`.getString("hinhLon")

                cacLoai_raus.add(cacLoai_rau)

            }

        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return cacLoai_raus
    }


}
