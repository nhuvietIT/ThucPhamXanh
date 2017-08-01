package com.example.nhuviet.thucphamxanh.View.TrangChu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.TabLayout
import android.support.v4.view.MenuItemCompat
import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ExpandableListView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

import com.example.nhuviet.thucphamxanh.Adapter.Adapter_Expand
import com.example.nhuviet.thucphamxanh.Adapter.Adapter_ViewPager
import com.example.nhuviet.thucphamxanh.Model.Model_DangNhap_DangKi.Model_DangNhap
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.LoaiSanPham
import com.example.nhuviet.thucphamxanh.Presenter.ChiTietSanPham.Presenter_code_chitiet
import com.example.nhuviet.thucphamxanh.Presenter.DangKi.Presenter_Code_DangKi
import com.example.nhuviet.thucphamxanh.Presenter.Presenter_Code_XuLyMenu
import com.example.nhuviet.thucphamxanh.R
import com.example.nhuviet.thucphamxanh.View.DangNhap_DangKi_Activity.DangNhap_Activity
import com.example.nhuviet.thucphamxanh.View.DangNhap_DangKi_Activity.View_DangKi
import com.example.nhuviet.thucphamxanh.View.GioHang.View_GioHang_Activity
import com.example.nhuviet.thucphamxanh.View.TimKiem.TimKiem_Activity
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.GraphResponse
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient

import org.json.JSONException
import org.json.JSONObject

/**
 * Created by nhuvi on 16/05/2017.
 */

class Activity_TrangChu : AppCompatActivity(), View_XuLyMenu, AppBarLayout.OnOffsetChangedListener, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    internal var toolbar: Toolbar
    internal var tabLayout: TabLayout
    internal var viewPager: ViewPager
    internal var drawerLayout: DrawerLayout
    internal var drawerToggle: ActionBarDrawerToggle
    internal var expandableListView: ExpandableListView
    internal var accessToken: AccessToken? = null
    internal var thongtinnguoidung = ""
    internal var presenter_code_xuLyMenu: Presenter_Code_XuLyMenu
    internal var menu: Menu
    internal var model_dangNhap: Model_DangNhap
    internal var menuDangNhap: MenuItem
    internal var menuDangXuat: MenuItem
    internal var mgoogleApiClient: GoogleApiClient
    internal var googleSignInResult: GoogleSignInResult? = null
    internal var collapsingToolbarLayout: CollapsingToolbarLayout
    internal var appBarLayout: AppBarLayout
    internal var tx_giohang: TextView
    internal var onPause = false
    internal var bt_timkiem: Button

    //    public static final String Server_Name = "http://trinhlamvu.com/thucphamxanh/loaisanpham.php";
    //    public static final String SERVER = "http://trinhlamvu.com/thucphamxanh";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_trangchu)
        // ánh sạ
        toolbar = findViewById(R.id.toolbar) as Toolbar
        tabLayout = findViewById(R.id.tablayout) as TabLayout
        viewPager = findViewById(R.id.viewpager) as ViewPager
        drawerLayout = findViewById(R.id.drawerLayout) as DrawerLayout
        expandableListView = findViewById(R.id.ep_menu) as ExpandableListView
        appBarLayout = findViewById(R.id.appbar) as AppBarLayout
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar) as CollapsingToolbarLayout
        bt_timkiem = findViewById(R.id.bt_timkiem) as Button

        //tim kiem
        bt_timkiem.setOnClickListener(this)


        // toolbar
        toolbar.title = ""
        setSupportActionBar(toolbar)


        // DrawerLayout và ActionBarDrawerToggle
        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(drawerToggle)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        drawerToggle.syncState()


        // adapter viewpager
        val adapter_viewPager = Adapter_ViewPager(supportFragmentManager)
        viewPager.adapter = adapter_viewPager
        // tab layout
        tabLayout.setupWithViewPager(viewPager)

        // xulyMenu
        presenter_code_xuLyMenu = Presenter_Code_XuLyMenu(this)

        // model dang nhap
        model_dangNhap = Model_DangNhap()

        mgoogleApiClient = model_dangNhap.laythongtinGoogleApiClient(this, this)
        appBarLayout.addOnOffsetChangedListener(this)

        presenter_code_xuLyMenu.LayDanhSachMenu()
        presenter_code_xuLyMenu.Laytennguoidung_facebook()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_trangchu, menu)
        this.menu = menu

        // hien thi so luong gio hang
        val itemgiohang = menu.findItem(R.id.it_goihang)
        val giaodiengiohang = MenuItemCompat.getActionView(itemgiohang)
        tx_giohang = giaodiengiohang.findViewById(R.id.tx_itgiohang) as TextView
        val presenter_code_chitiet = Presenter_code_chitiet()
        tx_giohang.text = presenter_code_chitiet.Demsanphamtronggiohang(this).toString()

        giaodiengiohang.setOnClickListener {
            val intentgiohang = Intent(this@Activity_TrangChu, View_GioHang_Activity::class.java)
            startActivity(intentgiohang)
        }

        ////////////////////////////////////////
        menuDangNhap = menu.findItem(R.id.dangnhap)
        menuDangXuat = menu.findItem(R.id.dangxuat)

        // lấy thông tin người dùng GraphRequest API
        accessToken = presenter_code_xuLyMenu.Laytennguoidung_facebook()
        googleSignInResult = model_dangNhap.laythongtinnguoidung(mgoogleApiClient)

        if (accessToken != null) {
            val request = GraphRequest.newMeRequest(accessToken
            ) { `object`, response ->
                try {
                    // sữa tên đăng nhập
                    thongtinnguoidung = `object`.getString("name")
                    menuDangNhap.title = thongtinnguoidung

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            val parameters = Bundle()
            parameters.putString("fields", "name")
            request.parameters = parameters
            request.executeAsync()
        }
        if (googleSignInResult != null) {
            menuDangNhap.title = googleSignInResult!!.signInAccount!!.email
        }

        val tennv = model_dangNhap.LaycacheDangNhap(this)
        if (tennv != "") {
            menuDangNhap.title = tennv
        }


        if (accessToken != null || googleSignInResult != null || tennv != "") {
            menuDangXuat.isVisible = true
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true
        }


        val id = item.itemId
        when (id) {
            R.id.dangnhap -> if (accessToken == null && googleSignInResult == null && model_dangNhap.LaycacheDangNhap(this) == "") {
                val intent = Intent(this, DangNhap_Activity::class.java)
                startActivity(intent)
            }
            R.id.dangxuat -> {
                if (accessToken != null) {
                    LoginManager.getInstance().logOut()
                    Toast.makeText(application, "Đã đăng xuất", Toast.LENGTH_LONG).show()
                    this.menu.clear()
                    this.onCreateOptionsMenu(this.menu)
                }
                if (googleSignInResult != null) {
                    Auth.GoogleSignInApi.signOut(mgoogleApiClient)
                    Toast.makeText(application, "Đã đăng xuất", Toast.LENGTH_LONG).show()
                    this.menu.clear()
                    this.onCreateOptionsMenu(this.menu)
                }
                if (model_dangNhap.LaycacheDangNhap(this) != "") {
                    model_dangNhap.CapnhatcacheDangNhap(this, "")
                    Toast.makeText(application, "Đã đăng xuất", Toast.LENGTH_LONG).show()
                    this.menu.clear()
                    this.onCreateOptionsMenu(this.menu)
                }
            }
            R.id.it_seach -> {
                val intent = Intent(this, TimKiem_Activity::class.java)
                startActivity(intent)
            }
        }

        return true
    }

    override fun HienThiDanhSachMenu(loaiSanPhamList: List<LoaiSanPham>) {
        // Log.d("kt",loaiSanPhamList.get(0).getTenLoaiSP());
        val adapter_expand = Adapter_Expand(this, loaiSanPhamList)
        expandableListView.setAdapter(adapter_expand)
        adapter_expand.notifyDataSetChanged()

    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {

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

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        if (collapsingToolbarLayout.height + verticalOffset <= 1.5 * ViewCompat.getMinimumHeight(collapsingToolbarLayout)) {
            val linearLayout = appBarLayout.findViewById(R.id.lnSearch) as LinearLayout
            linearLayout.animate().alpha(0f).duration = 200
            val itSearch = menu.findItem(R.id.it_seach)
            itSearch.isVisible = true

        } else {
            val linearLayout = appBarLayout.findViewById(R.id.lnSearch) as LinearLayout
            linearLayout.animate().alpha(1f).duration = 200
            try {
                val itSearch = menu.findItem(R.id.it_seach)
                itSearch.isVisible = false
            } catch (e: Exception) {

            }

        }
    }

    override fun onClick(v: View) {
        val intimkiem = Intent(this, TimKiem_Activity::class.java)
        startActivity(intimkiem)
    }

    companion object {

        val Server_Name = "http://192.168.218.2:8082/data_zalada/loaisanpham.php"
        val SERVER = "http://192.168.218.2:8082/data_zalada"
    }


}
