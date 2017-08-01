package com.example.nhuviet.thucphamxanh.Model.Model_Trangchu_home

import com.example.nhuviet.thucphamxanh.ConnectInternet.Download_Json
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Sanpham
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.ThuongHieu
import com.example.nhuviet.thucphamxanh.View.TrangChu.Activity_TrangChu

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList
import java.util.HashMap
import java.util.concurrent.ExecutionException

/**
 * Created by nhuvi on 06/06/2017.
 */

class Model_Home {

    // thương hiệu
    fun LayDanhSachThuongHieuThucpham(tenham: String): List<ThuongHieu> {

        val thuongHieuList = ArrayList<ThuongHieu>()

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
            val jsonArraythuonghieu = jsonObject.getJSONArray("ThucPhamXanh")

            val dem = jsonArraythuonghieu.length()

            for (i in 0..dem - 1) {
                val thuongHieu = ThuongHieu()
                val `object` = jsonArraythuonghieu.getJSONObject(i)


                thuongHieu.maThuongHieu = `object`.getInt("maThuongHieu")
                thuongHieu.tenThuongHieu = `object`.getString("tenThuongHieu")
                thuongHieu.hinhThuongHieu = `object`.getString("hinhThuongHieu")

                thuongHieuList.add(thuongHieu)

            }

        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return thuongHieuList
    }


}
