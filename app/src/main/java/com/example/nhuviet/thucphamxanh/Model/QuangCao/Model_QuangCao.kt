package com.example.nhuviet.thucphamxanh.Model.QuangCao

import com.example.nhuviet.thucphamxanh.ConnectInternet.Download_Json
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Chitiet_SP
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.QuangCao
import com.example.nhuviet.thucphamxanh.View.TrangChu.Activity_TrangChu

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList
import java.util.HashMap
import java.util.concurrent.ExecutionException

/**
 * Created by nhuvi on 21/06/2017.
 */

class Model_QuangCao {
    fun LayDanhSach_quangcao(tenham: String): List<QuangCao> {


        val quangCaos = ArrayList<QuangCao>()

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
                val quangCao = QuangCao()
                val `object` = jsonArraysanpham.getJSONObject(i)

                quangCao.hinhQuangCao = `object`.getString("hinhQuangCao")

                quangCaos.add(quangCao)
            }

        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return quangCaos


    }
}
