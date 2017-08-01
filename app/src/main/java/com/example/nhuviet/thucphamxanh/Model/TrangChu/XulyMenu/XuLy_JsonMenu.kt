package com.example.nhuviet.thucphamxanh.Model.TrangChu.XulyMenu

import android.util.Log

import com.example.nhuviet.thucphamxanh.ConnectInternet.Download_Json
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.LoaiSanPham
import com.example.nhuviet.thucphamxanh.View.TrangChu.Activity_TrangChu

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList
import java.util.HashMap
import java.util.concurrent.ExecutionException

/**
 * Created by nhuvi on 16/05/2017.
 */

class XuLy_JsonMenu {

    fun parseJsonMenu(dulieujson: String): List<LoaiSanPham> {
        val loaiSanPhamList = ArrayList<LoaiSanPham>()

        var jsono: JSONObject? = null
        try {
            Log.d("kt2", dulieujson)
            jsono = JSONObject(dulieujson)
            val jarray = jsono.getJSONArray("ThucPhamXanh")

            val cuont = jarray.length()
            for (i in 0..cuont - 1) {
                val `object` = jarray.getJSONObject(i)

                val data_loaiSanPham = LoaiSanPham()

                data_loaiSanPham.maLoaiSP = Integer.parseInt(`object`.getString("malsp"))
                data_loaiSanPham.tenLoaiSP = `object`.getString("tenlsp")
                data_loaiSanPham.maLoaiCha = Integer.parseInt(`object`.getString("maloaicha"))

                loaiSanPhamList.add(data_loaiSanPham)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return loaiSanPhamList
    }

    fun loaiSanPhamtheomaloai(maloaisp: Int): List<LoaiSanPham> {
        var listloaisanpham: List<LoaiSanPham> = ArrayList()
        val attrs = ArrayList<HashMap<String, String>>()
        var datajson = ""
        //post
        val duongdan = Activity_TrangChu.Server_Name

        val shham = HashMap<String, String>()
        shham.put("ham", "Laydanhsachmenu")

        val mapmaloaicha = HashMap<String, String>()
        mapmaloaicha.put("maLoaiCha", maloaisp.toString())

        attrs.add(mapmaloaicha)
        attrs.add(shham)

        val download_json = Download_Json(duongdan, attrs)
        download_json.execute()

        try {
            datajson = download_json.get()
            val xuLy_jsonMenu = XuLy_JsonMenu()
            listloaisanpham = xuLy_jsonMenu.parseJsonMenu(datajson)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }

        return listloaisanpham

    }

}
