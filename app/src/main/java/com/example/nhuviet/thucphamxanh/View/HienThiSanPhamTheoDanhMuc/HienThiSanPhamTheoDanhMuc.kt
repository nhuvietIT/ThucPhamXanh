package com.example.nhuviet.thucphamxanh.View.HienThiSanPhamTheoDanhMuc

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast

import com.example.nhuviet.thucphamxanh.Adapter.Adapter_CR_SP_DanhMucSP
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.ILoad_more
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Load_more_RC_Scroll
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.SanPham_DMUC
import com.example.nhuviet.thucphamxanh.Presenter.HienThiSanPhamTheoDanhMuc.Presenter_Code_HienThiDanhMuc
import com.example.nhuviet.thucphamxanh.R
import com.example.nhuviet.thucphamxanh.View.TrangChu.Activity_TrangChu
import com.example.nhuviet.thucphamxanh.View.TrangChu.View_Hienthi_SanPham_TheoDanhMuc

/**
 * Created by nhuvi on 06/06/2017.
 */

class HienThiSanPhamTheoDanhMuc : Fragment(), View_Hienthi_SanPham_TheoDanhMuc, View.OnClickListener, ILoad_more {

    internal var recyclerView_danhMuc: RecyclerView
    internal var bt_trangthai_CR: Button
    internal var dangGrid = true
    internal var layoutManager: RecyclerView.LayoutManager
    internal var HienThiDanhMuc: Presenter_Code_HienThiDanhMuc
    internal var malsp: Int = 0
    internal var kiemtra: Boolean = false
    internal var adapter_cr_sp_danhMucSP: Adapter_CR_SP_DanhMucSP
    internal var toolbar: Toolbar
    internal var sanPham_dmuc: MutableList<SanPham_DMUC>
    internal var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.layout_hienthidanhsachdanhmuc1, container, false)

        setHasOptionsMenu(true)

        recyclerView_danhMuc = view.findViewById(R.id.RC_danhmucSP) as RecyclerView
        bt_trangthai_CR = view.findViewById(R.id.bt_RC_listchang) as Button
        toolbar = view.findViewById(R.id.toolbar) as Toolbar
        progressBar = view.findViewById(R.id.proProgressBar_loadDM) as ProgressBar

        bt_trangthai_CR.setOnClickListener(this)

        val bundle = arguments
        malsp = bundle.getInt("malsp", 0)
        val tenSP = bundle.getString("tenSP")
        kiemtra = bundle.getBoolean("kiemtra", false)

        HienThiDanhMuc = Presenter_Code_HienThiDanhMuc(this)
        HienThiDanhMuc.LaydanhsachsanphamtheomaloaiSP(malsp, kiemtra)
        toolbar.title = tenSP
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setHomeButtonEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { fragmentManager.popBackStack("Home", FragmentManager.POP_BACK_STACK_INCLUSIVE) }
        //    Toast.makeText(getActivity(), masp + "-" + tenSP + "-" + "-" + kiemtra, Toast.LENGTH_SHORT).show();

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        //  inflater.inflate(R.menu.menu_trangchu,menu);


    }

    override fun HienthiDanhSach_SanPhan(sanPham_dmucs: MutableList<SanPham_DMUC>) {
        sanPham_dmuc = sanPham_dmucs
        if (dangGrid) {
            layoutManager = GridLayoutManager(context, 2)
            adapter_cr_sp_danhMucSP = Adapter_CR_SP_DanhMucSP(context, R.layout.custom_layout_rc_danhmuc_sp_grid, sanPham_dmuc)

        } else {
            layoutManager = LinearLayoutManager(context)
            adapter_cr_sp_danhMucSP = Adapter_CR_SP_DanhMucSP(context, R.layout.custom_layout_rc_danhmuc_sp_line, sanPham_dmuc)

        }

        recyclerView_danhMuc.layoutManager = layoutManager
        recyclerView_danhMuc.adapter = adapter_cr_sp_danhMucSP
        //Load more
        //   recyclerView_danhMuc.addOnScrollListener(new Load_more_RC_Scroll(layoutManager,this));
        adapter_cr_sp_danhMucSP.notifyDataSetChanged()

    }

    override fun Loi_HienthiDanhSach_SanPhan() {

    }

    override fun onClick(v: View) {
        val id = v.id
        when (id) {
            R.id.bt_RC_listchang -> {
                dangGrid = !dangGrid
                HienThiDanhMuc.LaydanhsachsanphamtheomaloaiSP(malsp, kiemtra)
            }
        }

    }

    override fun LoadMore(tongItem: Int) {
        val sanPham_dmuces = HienThiDanhMuc.LaydanhsachsanphamtheomaloaiSP_LoadMore(malsp, kiemtra, tongItem, progressBar)
        sanPham_dmuc.addAll(sanPham_dmuces)
        adapter_cr_sp_danhMucSP.notifyDataSetChanged()
    }


}
