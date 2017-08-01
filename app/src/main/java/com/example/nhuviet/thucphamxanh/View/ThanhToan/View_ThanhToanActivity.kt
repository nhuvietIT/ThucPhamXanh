package com.example.nhuviet.thucphamxanh.View.ThanhToan

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

import com.example.nhuviet.thucphamxanh.Model.ObjectClass.ChiTiecHoanDon
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Chitiet_SP
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.HoaDon
import com.example.nhuviet.thucphamxanh.Presenter.ThanhToan.Presenter_code_ThanhToan
import com.example.nhuviet.thucphamxanh.R
import com.example.nhuviet.thucphamxanh.View.TrangChu.Activity_TrangChu

import java.util.ArrayList

/**
 * Created by nhuvi on 16/06/2017.
 */

class View_ThanhToanActivity : AppCompatActivity(), View.OnClickListener, View_ThanhToan {
    internal var toolbar: Toolbar
    internal var Thanhtoan: Button
    internal var ed_nguoinhan: EditText
    internal var ed_diachi: EditText
    internal var ed_phone: EditText
    internal var im_giaohang_nhantien: ImageButton
    internal var im_chuyenkhoan: ImageButton
    internal var cb_thoathuan: CheckBox
    internal var presenter_code_ThanhToan: Presenter_code_ThanhToan
    internal var nhantiengiaohang: TextView
    internal var chuyenkhoan: TextView
    internal var chitiechoaadons: MutableList<ChiTiecHoanDon> = ArrayList()
    internal var chonhinhthuc = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_thanhtoan)

        ed_nguoinhan = findViewById(R.id.rd_th_tennguoinhan) as EditText
        ed_diachi = findViewById(R.id.ed_diachigiao) as EditText
        ed_phone = findViewById(R.id.ed_phone) as EditText

        im_giaohang_nhantien = findViewById(R.id.im_nhantiengiaohang) as ImageButton
        im_chuyenkhoan = findViewById(R.id.im_nhantien_Chuyenkhoan) as ImageButton

        cb_thoathuan = findViewById(R.id.thoathuan) as CheckBox

        nhantiengiaohang = findViewById(R.id.txgiaohang) as TextView
        chuyenkhoan = findViewById(R.id.txchueynkhoan) as TextView

        Thanhtoan = findViewById(R.id.Thanhtoan) as Button
        toolbar = findViewById(R.id.toolbar) as Toolbar
        toolbar.title = "Thanh Toán"
        setSupportActionBar(toolbar)

        presenter_code_ThanhToan = Presenter_code_ThanhToan(this, this)
        presenter_code_ThanhToan.LayDanhSachSP_GoiHang()

        Thanhtoan.setOnClickListener(this)
        im_giaohang_nhantien.setOnClickListener(this)
        im_chuyenkhoan.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val id = v.id
        when (id) {
            R.id.Thanhtoan -> {
                //                Intent intentthanhtoan = new Intent(this, Activity_TrangChu.class);
                //                Toast.makeText(this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                //                startActivity(intentthanhtoan);
                val tennguoinhan = ed_nguoinhan.text.toString()
                val diachi = ed_diachi.text.toString()
                val sdt = ed_phone.text.toString()

                if (tennguoinhan.trim { it <= ' ' }.length > 0 && diachi.trim { it <= ' ' }.length > 0 && sdt.trim { it <= ' ' }.length > 0) {


                    if (cb_thoathuan.isChecked) {
                        val hoadon = HoaDon()

                        hoadon.tenNguoiNhan = tennguoinhan
                        hoadon.diaChi = diachi
                        hoadon.soDT = sdt
                        hoadon.chuyenKhoan = chonhinhthuc
                        hoadon.chiTiecHoanDonList = chitiechoaadons
                        presenter_code_ThanhToan.ThemHoaDon(hoadon)

                    } else {
                        Toast.makeText(this, "Bạn chưa check thỏa thuận ", Toast.LENGTH_SHORT).show()
                    }

                } else {
                    Toast.makeText(this, "Bạn chưa điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.im_nhantiengiaohang -> {
                chonHinhThuc_giaohang(nhantiengiaohang, chuyenkhoan)
                chonhinhthuc = 0
            }
            R.id.im_nhantien_Chuyenkhoan -> {
                chonHinhThuc_giaohang(chuyenkhoan, nhantiengiaohang)
                chonhinhthuc = 1
            }
        }
    }

    private fun chonHinhThuc_giaohang(chon: TextView, khongchon: TextView) {
        chon.setTextColor(getIdcolor(R.color.backgrundToobar))
        khongchon.setTextColor(getIdcolor(R.color.colorden))

    }

    private fun getIdcolor(idcolor: Int): Int {
        var color = 0
        if (Build.VERSION.SDK_INT > 21) {
            color = ContextCompat.getColor(this, idcolor)
        } else {
            color = resources.getColor(idcolor)
        }
        return color

    }

    override fun DatHangThanhCong() {
        Toast.makeText(this, "Thanh toán thành công", Toast.LENGTH_SHORT).show()
        val intentthanhtoan = Intent(this, Activity_TrangChu::class.java)
        startActivity(intentthanhtoan)
        finish()
    }

    override fun DatHangThatBai() {
        Toast.makeText(this, "Đặt hàng thất bại", Toast.LENGTH_SHORT).show()
    }

    override fun HienThiDanhSachSP_GoiHang(chitiet_Sp: List<Chitiet_SP>) {

        for (i in chitiet_Sp.indices) {
            val chitiechoadon = ChiTiecHoanDon()
            chitiechoadon.maSanPham = chitiet_Sp[i].maSanPham
            chitiechoadon.soluong = chitiet_Sp[i].soluong

            chitiechoaadons.add(chitiechoadon)
        }

    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}
