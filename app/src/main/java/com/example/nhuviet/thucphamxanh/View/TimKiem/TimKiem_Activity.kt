package com.example.nhuviet.thucphamxanh.View.TimKiem

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import com.example.nhuviet.thucphamxanh.Adapter.Adapter_CR_Timkiem
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.ILoad_more
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Load_more_RC_Scroll
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.SanPham_DMUC
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Timkiem
import com.example.nhuviet.thucphamxanh.Presenter.TimKiem.Presenter_code_TimKiem
import com.example.nhuviet.thucphamxanh.R

import java.util.ArrayList

/**
 * Created by nhuvi on 17/06/2017.
 */

class TimKiem_Activity : AppCompatActivity(), View_Timkiem, ILoad_more, SearchView.OnQueryTextListener {

    internal var toolbar: Toolbar
    internal var Rc_tk: RecyclerView
    internal var editsearch: SearchView
    internal var presenter_code_timKiem: Presenter_code_TimKiem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_timkiem)


        toolbar = findViewById(R.id.toobar_tk) as Toolbar
        toolbar.title = "Tìm Kiếm "
        setSupportActionBar(toolbar)

        presenter_code_timKiem = Presenter_code_TimKiem(this)
        editsearch = findViewById(R.id.simpleSearchView) as SearchView
        (editsearch.findViewById(android.support.v7.appcompat.R.id.search_src_text) as EditText).setTextColor(Color.WHITE)
        val theTextArea = editsearch.findViewById(R.id.search_src_text) as SearchView.SearchAutoComplete
        theTextArea.setHintTextColor(Color.WHITE)
        editsearch.setOnQueryTextListener(this)




        Rc_tk = findViewById(R.id.Rc_tk) as RecyclerView


        // hien thị nut back
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //        getMenuInflater().inflate(R.menu.menu_timkiem,menu);
        //
        //        MenuItem item =  menu.findItem(R.id.itseach);
        //        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        //        searchView.setIconified(false);
        //        searchView.setBackgroundColor(Color.WHITE);
        //        searchView.setOnQueryTextListener(this);
        return true

    }

    override fun TimkiemThanhCong(timkiems: List<Timkiem>) {
        val layoutManager = LinearLayoutManager(this)
        val adapter_cr_timkiem = Adapter_CR_Timkiem(this, timkiems)

        Rc_tk.layoutManager = layoutManager
        Rc_tk.adapter = adapter_cr_timkiem
        Rc_tk.addOnScrollListener(Load_more_RC_Scroll(layoutManager, this))
        adapter_cr_timkiem.notifyDataSetChanged()


    }

    override fun timkiemThatBai() {
        Toast.makeText(this, "Sản phẩm không có :(..!  ", Toast.LENGTH_SHORT).show()
    }

    override fun LoadMore(tongItem: Int) {
        // List<Timkiem> timkiem = presenter_code_timKiem.Timkiem_TenSanPham(tongItem);

    }

    override fun onQueryTextSubmit(query: String): Boolean {
        presenter_code_timKiem.Timkiem_TenSanPham(query, 10)
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        // go ts dau hieu thi ts do
        return false
    }

    // su kien nut back
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            finish() // đóng hoạt động này và quay trở lại hoạt động xem trước (nếu có)
        }

        return super.onOptionsItemSelected(item)
    }
}
