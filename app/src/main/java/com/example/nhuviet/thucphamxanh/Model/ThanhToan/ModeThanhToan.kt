package com.example.nhuviet.thucphamxanh.Model.ThanhToan

import android.util.Log

import com.example.nhuviet.thucphamxanh.ConnectInternet.Download_Json
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.ChiTiecHoanDon
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.HoaDon
import com.example.nhuviet.thucphamxanh.View.TrangChu.Activity_TrangChu

import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList
import java.util.HashMap
import java.util.concurrent.ExecutionException

/**
 * Created by nhuvi on 17/06/2017.
 */

class ModeThanhToan {

    fun ThemHoaDon(hoaDon: HoaDon): Boolean {
        val duongdan = Activity_TrangChu.Server_Name
        var kiemtra = false
        val attrs = ArrayList<HashMap<String, String>>()

        val hsHam = HashMap<String, String>()
        hsHam.put("ham", "ThemHoaDon")

        val chiTietHoaDonList = hoaDon.chiTiecHoanDonList

        var chuoijson = "{\"DANHSACHSANPHAM\" :[ "
        for (i in chiTietHoaDonList.indices) {
            chuoijson += "{"
            chuoijson += "\"maSanPham\" : " + chiTietHoaDonList[i].maSanPham + ","
            chuoijson += "\"soLuong\" : " + chiTietHoaDonList[i].soluong

            if (i == chiTietHoaDonList.size - 1) {
                chuoijson += "}"
            } else {
                chuoijson += "},"
            }

        }
        chuoijson += "]}"
        Log.d("loo", chuoijson)

        val hsDanhSachSanPham = HashMap<String, String>()
        hsDanhSachSanPham.put("danhSachSanPham", chuoijson)

        val hsTenNguoiNhan = HashMap<String, String>()
        hsTenNguoiNhan.put("tenNguoiNhan", hoaDon.tenNguoiNhan)

        val hsSoDT = HashMap<String, String>()
        hsSoDT.put("SoDT", hoaDon.soDT.toString())

        val hsDiaChi = HashMap<String, String>()
        hsDiaChi.put("diaChi", hoaDon.diaChi)

        val hsChuyenKhoan = HashMap<String, String>()
        hsChuyenKhoan.put("chuyenKhoan", hoaDon.chuyenKhoan.toString())

        attrs.add(hsHam)
        attrs.add(hsDanhSachSanPham)
        attrs.add(hsTenNguoiNhan)
        attrs.add(hsSoDT)
        attrs.add(hsDiaChi)
        attrs.add(hsChuyenKhoan)

        val downloadJSON = Download_Json(duongdan, attrs)
        downloadJSON.execute()

        try {
            val dulieuJSON = downloadJSON.get()
            val jsonObject = JSONObject(dulieuJSON)
            val ketqua = jsonObject.getString("ketqua")
            Log.d("kiemtra", ketqua)
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
