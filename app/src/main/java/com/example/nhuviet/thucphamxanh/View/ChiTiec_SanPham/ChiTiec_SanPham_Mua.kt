package com.example.nhuviet.thucphamxanh.View.ChiTiec_SanPham

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.example.nhuviet.thucphamxanh.Adapter.Adapter_Slider_Frament
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Chitiet_SP
import com.example.nhuviet.thucphamxanh.Presenter.ChiTietSanPham.Frament_SliderSanPham
import com.example.nhuviet.thucphamxanh.Presenter.ChiTietSanPham.Presenter_code_chitiet
import com.example.nhuviet.thucphamxanh.R
import com.example.nhuviet.thucphamxanh.View.GioHang.View_GioHang_Activity
import com.example.nhuviet.thucphamxanh.View.ThanhToan.View_ThanhToanActivity
import com.example.nhuviet.thucphamxanh.View.TrangChu.Activity_TrangChu
import com.example.nhuviet.thucphamxanh.View.TrangChu.View_ChiTietSanpham

import java.io.ByteArrayOutputStream
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.ArrayList

/**
 * Created by nhuvi on 11/06/2017.
 */

class ChiTiec_SanPham_Mua : AppCompatActivity(), View_ChiTietSanpham, View.OnClickListener {
    internal var tx_tensp_chitiec: TextView
    internal var tc_giaChitiec: TextView
    internal var tx_tenloaiSPChitiec: TextView
    internal var tx_CHdonggoi: TextView
    internal var tc_thongchitiec: TextView
    internal var tx_giohang: TextView
    internal var tb_baove: Button
    internal var bt_dambao: Button
    internal var muangay: Button
    internal var themgiohang: Button
    internal var toolbar: Toolbar
    internal var masp: Int = 0
    internal var presenter_code_chitiet: Presenter_code_chitiet
    internal var sanphamgiohang: Chitiet_SP
    internal var viewPager: ViewPager
    internal var fragmentList: MutableList<Fragment>
    internal var onPause = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_chitiec_sanpham_mua)

        viewPager = findViewById(R.id.ct_ViewPager) as ViewPager

        tx_tensp_chitiec = findViewById(R.id.tx_tensp_chitiec) as TextView
        tc_giaChitiec = findViewById(R.id.tc_giaChitiec) as TextView
        tx_tenloaiSPChitiec = findViewById(R.id.tx_tenloaiSPChitiec) as TextView
        tx_CHdonggoi = findViewById(R.id.tx_CHdonggoi) as TextView
        tc_thongchitiec = findViewById(R.id.tc_thongchitiec) as TextView

        bt_dambao = findViewById(R.id.bt_dambao) as Button
        tb_baove = findViewById(R.id.tb_baove) as Button
        muangay = findViewById(R.id.muangay) as Button
        themgiohang = findViewById(R.id.themgiohang) as Button


        muangay.setOnClickListener(this)


        toolbar = findViewById(R.id.toolbar) as Toolbar

        val intent = intent
        masp = intent.getIntExtra("masp", 0)
        val tenSP = intent.getStringExtra("tenSP")
        //  boolean  kiemtra = intent.getBooleanExtra("kiemtra", false);

        toolbar.title = tenSP
        setSupportActionBar(toolbar)
        themgiohang.setOnClickListener(this)

        // view pager
        presenter_code_chitiet = Presenter_code_chitiet(this)

        presenter_code_chitiet.LayDanhSachChiTiet_SP(masp)

        // hien thị nut back
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }

    }

    // su kien nut back
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            finish() // đóng hoạt động này và quay trở lại hoạt động xem trước (nếu có)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun HienthiDanhSachChiTiec_SanPhan(chitietSps: Chitiet_SP) {

        masp = chitietSps.maSanPham

        sanphamgiohang = chitietSps

        // so sach so luong ton kho trong sqlite vaf server
        sanphamgiohang.soLuongTonKho = chitietSps.soluong

        tx_tensp_chitiec.text = chitietSps.tenSanPham.toString()
        val numberFormat = DecimalFormat("###,###")
        val gia = numberFormat.format(chitietSps.gia.toLong()).toString()
        tc_giaChitiec.text = gia + " VNĐ"
        tx_tenloaiSPChitiec.text = chitietSps.tenLoaiSanPham
        tx_CHdonggoi.text = chitietSps.tenThuongHieu
        tc_thongchitiec.text = chitietSps.thongtin
    }

    override fun Loi_HienthiDanhSachChiTiec_SanPhan() {

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_trangchu, menu)
        val itemgiohang = menu.findItem(R.id.it_goihang)
        val giaodiengiohang = MenuItemCompat.getActionView(itemgiohang)
        tx_giohang = giaodiengiohang.findViewById(R.id.tx_itgiohang) as TextView
        tx_giohang.text = presenter_code_chitiet.Demsanphamtronggiohang(this).toString()


        giaodiengiohang.setOnClickListener {
            val intentgiohang = Intent(this@ChiTiec_SanPham_Mua, View_GioHang_Activity::class.java)
            startActivity(intentgiohang)
        }
        return true
    }

    override fun Themgiohangthanhcong() {
        Toast.makeText(this, "Đã thêm Sản phẩm vào giỏ hàng..!", Toast.LENGTH_SHORT).show()
        tx_giohang.text = presenter_code_chitiet.Demsanphamtronggiohang(this).toString()
    }

    override fun Themgiohangthatbai() {
        Toast.makeText(this, "Sản phẩm đã có trong giỏ hàng..!", Toast.LENGTH_SHORT).show()
    }

    override fun HienSliderSanPham(linkhinhsanpham: Array<String>) {
        fragmentList = ArrayList<Fragment>()
        for (i in 1..linkhinhsanpham.size - 1) {
            val frament_sliderSanPham = Frament_SliderSanPham()
            val bundle = Bundle()
            bundle.putString("linhhinh", Activity_TrangChu.SERVER + linkhinhsanpham[i])
            frament_sliderSanPham.arguments = bundle

            fragmentList.add(frament_sliderSanPham)
        }
        val adapter_slider_frament = Adapter_Slider_Frament(supportFragmentManager, fragmentList)
        viewPager.adapter = adapter_slider_frament
        adapter_slider_frament.notifyDataSetChanged()

    }


    override fun onClick(v: View) {
        val id = v.id

        when (id) {
            R.id.themgiohang -> {
                val fragment = fragmentList[1]
                val view = fragment.view
                val imageView = view!!.findViewById(R.id.im_frament) as ImageView
                val bitmap = (imageView.drawable as BitmapDrawable).bitmap


                val byteArrayOutputStream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
                imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 220, 220, false))

                val hinhgiohang = byteArrayOutputStream.toByteArray()

                sanphamgiohang.hinhgiohang = hinhgiohang
                sanphamgiohang.soluong = 1

                presenter_code_chitiet.Themgiohang(sanphamgiohang, this)
                Log.d("ol", bitmap.toString())
            }
            R.id.muangay -> {
                val fragment1 = fragmentList[1]
                val view1 = fragment1.view
                val imageView1 = view1!!.findViewById(R.id.im_frament) as ImageView
                val bitmap1 = (imageView1.drawable as BitmapDrawable).bitmap


                val byteArrayOutputStream1 = ByteArrayOutputStream()
                bitmap1.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream1)
                imageView1.setImageBitmap(Bitmap.createScaledBitmap(bitmap1, 220, 220, false))


                val hinhgiohang1 = byteArrayOutputStream1.toByteArray()

                sanphamgiohang.hinhgiohang = hinhgiohang1
                sanphamgiohang.soluong = 1

                presenter_code_chitiet.Themgiohang(sanphamgiohang, this)
                val intentthanhtoan = Intent(this@ChiTiec_SanPham_Mua, View_ThanhToanActivity::class.java)
                startActivity(intentthanhtoan)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (onPause) {
            val presenter_code_chitiet = Presenter_code_chitiet()
            tx_giohang.text = presenter_code_chitiet.Demsanphamtronggiohang(this).toString()
        }
    }

    override fun onPause() {
        super.onPause()
        //        boolean onPause = false;
        onPause = true
    }
}
