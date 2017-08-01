package com.example.nhuviet.thucphamxanh.Model.ObjectClass

/**
 * Created by nhuvi on 30/05/2017.
 */

class NhanVien {
    var maNhanVien: Int = 0
    var maLoaiNhanVien: Int = 0
    var gioiTinh: Int = 0
    var tenNhanVien: String
    var tenDangNhap: String
    var matKhau: String
    var diachi: String
    var ngaySinh: String
    var soDT: String
    var maDocQuyen: String

    constructor(maNhanVien: Int, maLoaiNhanVien: Int, gioiTinh: Int, tenNhanVien: String, tenDangNhap: String,
                matKhau: String, diachi: String, ngaySinh: String, soDT: String, maDocQuyen: String) {
        this.maNhanVien = maNhanVien
        this.maLoaiNhanVien = maLoaiNhanVien
        this.gioiTinh = gioiTinh
        this.tenNhanVien = tenNhanVien
        this.tenDangNhap = tenDangNhap
        this.matKhau = matKhau
        this.diachi = diachi
        this.ngaySinh = ngaySinh
        this.soDT = soDT
        this.maDocQuyen = maDocQuyen
    }

    constructor() {

    }
}
