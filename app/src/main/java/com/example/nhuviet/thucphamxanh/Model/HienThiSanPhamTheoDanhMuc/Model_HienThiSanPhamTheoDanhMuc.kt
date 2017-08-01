package com.example.nhuviet.thucphamxanh.Model.HienThiSanPhamTheoDanhMuc

import com.example.nhuviet.thucphamxanh.ConnectInternet.Download_Json
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.CacLoai_Rau
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.SanPham_DMUC
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Sanpham
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

class Model_HienThiSanPhamTheoDanhMuc {

    fun LaySanPhamTheoMaLoaiSP(Malsp: Int, tenham: String, limit: Int): List<SanPham_DMUC> {
        val sanphamList = ArrayList<SanPham_DMUC>()

        val attrs = ArrayList<HashMap<String, String>>()
        var datajson = ""
        //post
        val duongdan = Activity_TrangChu.Server_Name

        val shham = HashMap<String, String>()
        shham.put("ham", tenham)

        val smaloai = HashMap<String, String>()
        smaloai.put("maLoaiSanPham", Malsp.toString())

        val shlimit = HashMap<String, String>()
        shlimit.put("limit", limit.toString())

        attrs.add(shham)
        attrs.add(smaloai)
        attrs.add(shlimit)

        val download_json = Download_Json(duongdan, attrs)
        download_json.execute()

        try {
            datajson = download_json.get()
            val jsonObject = JSONObject(datajson)
            val jsonArraysanpham = jsonObject.getJSONArray("ThucPhamXanh")

            val dem = jsonArraysanpham.length()

            for (i in 0..dem - 1) {
                val sanpham = SanPham_DMUC()
                val `object` = jsonArraysanpham.getJSONObject(i)


                sanpham.maLoaiSanPham = `object`.getInt("maLoaiSanPham")
                sanpham.tenLoaiSanPham = `object`.getString("tenLoaiSanPham")
                sanpham.hinhSanPhamDM = `object`.getString("hinhThucPhamUC")

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

    fun LaySanPhammaloairau(Malsp: Int, tenham: String): List<CacLoai_Rau> {
        val sanphamList = ArrayList<CacLoai_Rau>()

        val attrs = ArrayList<HashMap<String, String>>()
        var datajson = ""
        //post
        val duongdan = Activity_TrangChu.Server_Name

        val shham = HashMap<String, String>()
        shham.put("ham", tenham)

        val smaloai = HashMap<String, String>()
        smaloai.put("maLoaiSanPham", Malsp.toString())

        attrs.add(shham)
        attrs.add(smaloai)


        val download_json = Download_Json(duongdan, attrs)
        download_json.execute()

        try {
            datajson = download_json.get()
            val jsonObject = JSONObject(datajson)
            val jsonArraysanpham = jsonObject.getJSONArray("ThucPhamXanh")

            val dem = jsonArraysanpham.length()

            for (i in 0..dem - 1) {
                val sanpham = CacLoai_Rau()
                val `object` = jsonArraysanpham.getJSONObject(i)


                sanpham.maSanPham = `object`.getInt("maLoaiSanPham")
                sanpham.maSanPham = `object`.getInt("maSanPham")
                sanpham.gia = `object`.getInt("gia")
                sanpham.tenSanPham = `object`.getString("tenSanPham")
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


}
