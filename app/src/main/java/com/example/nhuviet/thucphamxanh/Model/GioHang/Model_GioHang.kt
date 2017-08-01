package com.example.nhuviet.thucphamxanh.Model.GioHang

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log

import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Chitiet_SP
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Sanpham

import java.util.ArrayList

/**
 * Created by nhuvi on 14/06/2017.
 */

class Model_GioHang {
    internal var database: SQLiteDatabase
    internal var database_sanPham: Database_SanPham
    fun MoKetNoiSQL(context: Context) {
        database_sanPham = Database_SanPham(context)
        database = database_sanPham.writableDatabase
    }

    fun CapnhatsoluongSP_giohang(masp: Int, soluong: Int): Boolean {

        val values = ContentValues()
        values.put(Database_SanPham.tb_GioHang_Soluong, soluong)
        val id = database.update(Database_SanPham.tb_GioHang, values, Database_SanPham.tb_GioHang_MASP + " = " + masp, null)
        if (id > 0) {
            return true
        } else {
            return false
        }
    }

    fun XoaSanPham_GioHang(masp: Int): Boolean {
        val kiemtra = database.delete(Database_SanPham.tb_GioHang, Database_SanPham.tb_GioHang_MASP + " = " + masp, null)
        if (kiemtra > 0) {
            return true
        } else {
            return false
        }

    }

    fun ThemGioHang(chitiet_sp: Chitiet_SP): Boolean {

        val values = ContentValues()
        values.put(Database_SanPham.tb_GioHang_MASP, chitiet_sp.maSanPham)
        values.put(Database_SanPham.tb_GioHang_TENSP, chitiet_sp.tenSanPham.toString())
        values.put(Database_SanPham.tb_GioHang_GIATIEN, chitiet_sp.gia)
        values.put(Database_SanPham.tb_GioHang_HInHNANH, chitiet_sp.hinhgiohang)
        values.put(Database_SanPham.tb_GioHang_Soluong, chitiet_sp.soluong)
        values.put(Database_SanPham.tb_GioHang_SoLuongton, chitiet_sp.soLuongTonKho)

        val id = database.insert(database_sanPham.tb_GioHang, null, values)


        if (id > 0) {
            return true
        } else {
            return false
        }

    }

    fun LayDanhSachGioHang(): List<Chitiet_SP> {
        val chitietSpList = ArrayList<Chitiet_SP>()

        val sql = "SELECT * FROM " + Database_SanPham.tb_GioHang
        val cursor = database.rawQuery(sql, null)

        cursor.moveToFirst()

        while (!cursor.isAfterLast) {
            val masp = cursor.getInt(cursor.getColumnIndex(Database_SanPham.tb_GioHang_MASP))
            val tensp = cursor.getString(cursor.getColumnIndex(Database_SanPham.tb_GioHang_TENSP))
            val giasp = cursor.getInt(cursor.getColumnIndex(Database_SanPham.tb_GioHang_GIATIEN))
            val hinhsp = cursor.getBlob(cursor.getColumnIndex(Database_SanPham.tb_GioHang_HInHNANH))
            val soluong = cursor.getInt(cursor.getColumnIndex(Database_SanPham.tb_GioHang_Soluong))
            val soluongton = cursor.getInt(cursor.getColumnIndex(Database_SanPham.tb_GioHang_SoLuongton))
            val sanpham = Chitiet_SP()

            sanpham.maSanPham = masp
            sanpham.tenSanPham = tensp
            sanpham.gia = giasp
            sanpham.hinhgiohang = hinhsp
            sanpham.soluong = soluong
            sanpham.soLuongTonKho = soluongton

            chitietSpList.add(sanpham)
            cursor.moveToNext()
        }

        return chitietSpList
    }


}