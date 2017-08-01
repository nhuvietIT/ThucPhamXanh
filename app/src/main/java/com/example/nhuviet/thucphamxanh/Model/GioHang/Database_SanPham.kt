package com.example.nhuviet.thucphamxanh.Model.GioHang

import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/**
 * Created by nhuvi on 14/06/2017.
 */

class Database_SanPham(context: Context) : SQLiteOpenHelper(context, tenTable, null, vertion) {

    override fun onCreate(db: SQLiteDatabase) {
        val tb_gioHang = "CREATE TABLE $tb_GioHang($tb_GioHang_MASP INTEGER PRIMARY KEY,"
        +tb_GioHang_TENSP + " TEXT," + tb_GioHang_GIATIEN + " REAL," + tb_GioHang_HInHNANH + " BLOB,"
        +tb_GioHang_Soluong + " INTEGER," + tb_GioHang_SoLuongton + " INTEGER);"


        val tb_yeuThich = "CREATE TABLE $tb_YeuThich($tb_YeuThich_MASP INTEGER PRIMARY KEY,"
        +tb_YeuThich_TENSP + " TEXT," + tb_YeuThich_GIATIEN + " REAL," + tb_YeuThich_HInHNANH + " BLOB);"

        db.execSQL(tb_gioHang)

        db.execSQL(tb_yeuThich)


    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS" + tb_GioHang)
        onCreate(db)
    }

    companion object {
        val tb_GioHang = "GIO_HANG"
        val tb_GioHang_MASP = "MASP"
        val tb_GioHang_TENSP = "TENSP"
        val tb_GioHang_GIATIEN = "GIATIEN"
        val tb_GioHang_HInHNANH = "HINHANH"
        val tb_GioHang_Soluong = "SOLUONG"
        val tb_GioHang_SoLuongton = "SOLUONGTON"

        val tb_YeuThich = "GIOHANG"
        val tb_YeuThich_MASP = "MASP"
        val tb_YeuThich_TENSP = "TENSP"
        val tb_YeuThich_GIATIEN = "GIATIEN"
        val tb_YeuThich_HInHNANH = "HINHANH"

        val tenTable = "Gioihang_SanPham"
        val vertion = 1
    }
}
