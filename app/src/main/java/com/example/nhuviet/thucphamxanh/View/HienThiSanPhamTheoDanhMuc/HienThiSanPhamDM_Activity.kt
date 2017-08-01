package com.example.nhuviet.thucphamxanh.View.HienThiSanPhamTheoDanhMuc

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

import com.example.nhuviet.thucphamxanh.Adapter.Adapter_CR_SP_DanhMucSP
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.ILoad_more
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.SanPham_DMUC
import com.example.nhuviet.thucphamxanh.Presenter.HienThiSanPhamTheoDanhMuc.Presenter_Code_HienThiDanhMuc
import com.example.nhuviet.thucphamxanh.R
import com.example.nhuviet.thucphamxanh.View.ChiTiec_SanPham.ChiTiec_SanPham_Mua
import com.example.nhuviet.thucphamxanh.View.GioHang.View_GioHang_Activity
import com.example.nhuviet.thucphamxanh.View.TrangChu.View_Hienthi_SanPham_TheoDanhMuc

/**
 * Created by nhuvi on 18/06/2017.
 */

class HienThiSanPhamDM_Activity : AppCompatActivity(), View_Hienthi_SanPham_TheoDanhMuc, View.OnClickListener, ILoad_more {

    internal var recyclerView_danhMuc: RecyclerView
    internal var bt_trangthai_CR: Button
    internal var dangGrid = true
    internal var layoutManager: RecyclerView.LayoutManager
    internal var HienThiDanhMuc: Presenter_Code_HienThiDanhMuc
    internal var masp: Int = 0
    internal var kiemtra: Boolean = false
    internal var adapter_cr_sp_danhMucSP: Adapter_CR_SP_DanhMucSP
    internal var toolbar: Toolbar
    internal var sanPham_dmuc: MutableList<SanPham_DMUC>
    internal var progressBar: ProgressBar
    internal var tx_giohang: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_hienthidanhsachdanhmuc)
        recyclerView_danhMuc = findViewById(R.id.RC_danhmucSP) as RecyclerView
        bt_trangthai_CR = findViewById(R.id.bt_RC_listchang) as Button
        toolbar = findViewById(R.id.toolbar) as Toolbar
        progressBar = findViewById(R.id.proProgressBar_loadDM) as ProgressBar

        bt_trangthai_CR.setOnClickListener(this)



        masp = intent.getIntExtra("malsp", 0)
        val tenSP = intent.getStringExtra("tenSP")
        kiemtra = intent.getBooleanExtra("kiemtra", false)

        HienThiDanhMuc = Presenter_Code_HienThiDanhMuc(this)
        HienThiDanhMuc.LaydanhsachsanphamtheomaloaiSP(masp, kiemtra)
        toolbar.title = tenSP
        setSupportActionBar(toolbar)

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


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_trangchu, menu)
        val itemgiohang = menu.findItem(R.id.it_goihang)
        val giaodiengiohang = MenuItemCompat.getActionView(itemgiohang)
        tx_giohang = giaodiengiohang.findViewById(R.id.tx_itgiohang) as TextView
        tx_giohang.text = HienThiDanhMuc.Demsanphamtronggiohang(this).toString()


        //        giaodiengiohang.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                Intent intentgiohang = new Intent(HienThiSanPhamDM_Activity.this, View_GioHang_Activity.class);
        //                startActivity(intentgiohang);
        //            }
        //        });
        return true


    }

    override fun HienthiDanhSach_SanPhan(sanPham_dmucs: MutableList<SanPham_DMUC>) {
        sanPham_dmuc = sanPham_dmucs
        if (dangGrid) {
            layoutManager = GridLayoutManager(this, 2)
            adapter_cr_sp_danhMucSP = Adapter_CR_SP_DanhMucSP(this, R.layout.custom_layout_rc_danhmuc_sp_grid, sanPham_dmuc)

        } else {
            layoutManager = LinearLayoutManager(this)
            adapter_cr_sp_danhMucSP = Adapter_CR_SP_DanhMucSP(this, R.layout.custom_layout_rc_danhmuc_sp_line, sanPham_dmuc)

        }

        recyclerView_danhMuc.layoutManager = layoutManager
        recyclerView_danhMuc.adapter = adapter_cr_sp_danhMucSP
        //Load more
        //recyclerView_danhMuc.addOnScrollListener(new Load_more_RC_Scroll(layoutManager,this));
        adapter_cr_sp_danhMucSP.notifyDataSetChanged()

    }

    override fun Loi_HienthiDanhSach_SanPhan() {

    }

    override fun onClick(v: View) {
        val id = v.id
        when (id) {
            R.id.bt_RC_listchang -> {
                dangGrid = !dangGrid
                HienThiDanhMuc.LaydanhsachsanphamtheomaloaiSP(masp, kiemtra)
            }
        }

    }

    override fun LoadMore(tongItem: Int) {
        val sanPham_dmuces = HienThiDanhMuc.LaydanhsachsanphamtheomaloaiSP_LoadMore(masp, kiemtra, tongItem, progressBar)
        sanPham_dmuc.addAll(sanPham_dmuces)
        adapter_cr_sp_danhMucSP.notifyDataSetChanged()
    }
}
