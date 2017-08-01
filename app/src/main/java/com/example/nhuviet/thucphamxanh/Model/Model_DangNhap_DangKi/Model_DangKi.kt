package com.example.nhuviet.thucphamxanh.Model.Model_DangNhap_DangKi

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

import com.example.nhuviet.thucphamxanh.ConnectInternet.Download_Json
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.NhanVien
import com.example.nhuviet.thucphamxanh.View.TrangChu.Activity_TrangChu

import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList
import java.util.HashMap
import java.util.concurrent.ExecutionException

/**
 * Created by nhuvi on 30/05/2017.
 */

class Model_DangKi {
    fun Dangkithanhvien(nhanVien: NhanVien): Boolean? {
        val duongdan = Activity_TrangChu.Server_Name
        var kiemtra = false
        val attrs = ArrayList<HashMap<String, String>>()

        val shham = HashMap<String, String>()
        shham.put("ham", "Dangkithanhvien")

        val tenNhanVien = HashMap<String, String>()
        tenNhanVien.put("tenNhanVien", nhanVien.tenNhanVien)

        val tenDangNhap = HashMap<String, String>()
        tenDangNhap.put("tenDangNhap", nhanVien.tenDangNhap)

        val matKhau = HashMap<String, String>()
        matKhau.put("matKhau", nhanVien.matKhau)

        val maDocQuyen = HashMap<String, String>()
        maDocQuyen.put("maDocQuyen", nhanVien.maDocQuyen)

        val maLoaiNhanVien = HashMap<String, String>()
        maLoaiNhanVien.put("maLoaiNhanVien", nhanVien.maLoaiNhanVien.toString())

        attrs.add(shham)
        attrs.add(tenNhanVien)
        attrs.add(tenDangNhap)
        attrs.add(matKhau)
        attrs.add(maDocQuyen)
        attrs.add(maLoaiNhanVien)


        val download_json = Download_Json(duongdan, attrs)

        download_json.execute()


        try {
            val dulieujson = download_json.get()
            val jsonObject = JSONObject(dulieujson)
            val ketqua = jsonObject.getString("ketqua")
            Log.d("ketqua:", ketqua)
            if (ketqua == "true") {
                kiemtra = true

            } else {
                kiemtra = false
            }
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return kiemtra
    }
}
