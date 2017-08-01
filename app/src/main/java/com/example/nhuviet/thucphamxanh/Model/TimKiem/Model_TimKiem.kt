package com.example.nhuviet.thucphamxanh.Model.TimKiem

import com.example.nhuviet.thucphamxanh.ConnectInternet.Download_Json
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.SanPham_DMUC
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Timkiem
import com.example.nhuviet.thucphamxanh.View.TrangChu.Activity_TrangChu

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList
import java.util.HashMap
import java.util.concurrent.ExecutionException

/**
 * Created by nhuvi on 17/06/2017.
 */

class Model_TimKiem {

    fun LayDanhSachtimKiemSP(tenSanpham: String, tenham: String, limit: Int): List<Timkiem> {
        val timkiems = ArrayList<Timkiem>()

        val attrs = ArrayList<HashMap<String, String>>()
        var datajson = ""
        //post
        val duongdan = Activity_TrangChu.Server_Name

        val shham = HashMap<String, String>()
        shham.put("ham", tenham)

        val shtenSanpham = HashMap<String, String>()
        shtenSanpham.put("tenSanPham", tenSanpham)

        val shlimit = HashMap<String, String>()
        shlimit.put("limit", limit.toString())

        attrs.add(shham)
        attrs.add(shtenSanpham)
        attrs.add(shlimit)

        val download_json = Download_Json(duongdan, attrs)
        download_json.execute()

        try {
            datajson = download_json.get()
            val jsonObject = JSONObject(datajson)
            val jsonArraysanpham = jsonObject.getJSONArray("ThucPhamXanh")

            val dem = jsonArraysanpham.length()

            for (i in 0..dem - 1) {
                val timkiem = Timkiem()
                val `object` = jsonArraysanpham.getJSONObject(i)

                timkiem.maSanPham = `object`.getInt("maSanPham")
                timkiem.tenSanPham = `object`.getString("tenSanPham")
                timkiem.hinhLon = `object`.getString("hinhLon")
                timkiem.hinhNho = `object`.getString("hinhNho")
                timkiem.gia = `object`.getInt("gia")
                timkiem.maNhanVien = `object`.getInt("maNhanVien")
                timkiem.soLuong = `object`.getInt("soLuong")

                timkiems.add(timkiem)
            }

        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return timkiems
    }
}
