package com.example.nhuviet.thucphamxanh.Adapter

import android.app.FragmentContainer
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Display
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.example.nhuviet.thucphamxanh.Model.ObjectClass.LoaiSanPham
import com.example.nhuviet.thucphamxanh.Model.TrangChu.XulyMenu.XuLy_JsonMenu
import com.example.nhuviet.thucphamxanh.R
import com.example.nhuviet.thucphamxanh.View.DangNhap_DangKi_Activity.DangNhap_Activity
import com.example.nhuviet.thucphamxanh.View.HienThiSanPhamTheoDanhMuc.HienThiSanPhamTheoDanhMuc
import com.google.android.gms.auth.api.Auth

/**
 * Created by nhuvi on 16/05/2017.
 */

class Adapter_Expand(internal var context: Context, internal var loaiSanPhams: List<LoaiSanPham>) : BaseExpandableListAdapter() {
    internal var viewhodermenu: ViewHoderMenu

    init {

        val xuLy_jsonMenu = XuLy_JsonMenu()

        val count = loaiSanPhams.size
        for (i in 0..count - 1) {
            val maloaisp = loaiSanPhams[i].maLoaiSP
            loaiSanPhams[i].listcon = xuLy_jsonMenu.loaiSanPhamtheomaloai(maloaisp)
        }

    }

    override fun getGroupCount(): Int {
        return loaiSanPhams.size
    }

    override fun getChildrenCount(groupCha: Int): Int {
        if (loaiSanPhams[groupCha].listcon.size != 0) {
            return 1
        } else {
            return 0
        }
    }

    override fun getGroup(groupCha: Int): Any {
        return loaiSanPhams[groupCha]
    }

    override fun getChild(groupCha: Int, groupCon: Int): Any {
        return loaiSanPhams[groupCha].listcon[groupCon]
    }

    override fun getGroupId(groupCha: Int): Long {
        return loaiSanPhams[groupCha].maLoaiSP.toLong()
    }

    override fun getChildId(groupCha: Int, groupCon: Int): Long {
        return loaiSanPhams[groupCha].listcon[groupCon].maLoaiSP.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    inner class ViewHoderMenu {
        internal var txt_tensp: TextView? = null
        internal var hinhmenu: ImageView? = null
    }

    override fun getGroupView(groupCha: Int, isExpanded: Boolean, convertView: View, parent: ViewGroup): View {

        var viewGroupcha: View? = convertView
        if (viewGroupcha == null) {
            viewhodermenu = ViewHoderMenu()
            val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            viewGroupcha = layoutInflater.inflate(R.layout.custom_layout_gorupcha, parent, false)

            viewhodermenu.txt_tensp = viewGroupcha!!.findViewById(R.id.txt_tenloaisp) as TextView
            viewhodermenu.hinhmenu = viewGroupcha.findViewById(R.id.im_add) as ImageView

            viewGroupcha.tag = viewhodermenu
        } else {
            viewhodermenu = viewGroupcha.tag as ViewHoderMenu
        }
        viewhodermenu.txt_tensp!!.text = loaiSanPhams[groupCha].tenLoaiSP
        // ẩn icon sd listcon
        val demvitrisanphamcon = loaiSanPhams[groupCha].listcon.size
        if (demvitrisanphamcon > 0) {
            viewhodermenu.hinhmenu!!.visibility = View.VISIBLE
        } else {
            viewhodermenu.hinhmenu!!.visibility = View.INVISIBLE
        }
        // ẩn hiện icon - +
        if (isExpanded) {
            viewhodermenu.hinhmenu!!.setImageResource(R.drawable.ic_remove_black_24dp)
            viewGroupcha.setBackgroundResource(R.color.colordangnhap)
        } else {
            viewhodermenu.hinhmenu!!.setImageResource(R.drawable.ic_add_black_24dp)
        }
        // sukien lick
        viewGroupcha.setOnTouchListener { v, event ->
            val fragmentManager = (context as AppCompatActivity).supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            val hienThiSanPhamTheoDanhMuc = HienThiSanPhamTheoDanhMuc()

            val bundle = Bundle()
            bundle.putInt("malsp", loaiSanPhams[groupCha].maLoaiSP)
            bundle.putString("tenSP", loaiSanPhams[groupCha].tenLoaiSP)
            bundle.putBoolean("kiemtra", true)

            hienThiSanPhamTheoDanhMuc.arguments = bundle
            fragmentTransaction.addToBackStack("Home")
            fragmentTransaction.replace(R.id.themframent, hienThiSanPhamTheoDanhMuc)
            fragmentTransaction.commit()

            //                Toast.makeText(context,loaiSanPhams.get(groupCha).getTenLoaiSP()+"+"
            //                        +loaiSanPhams.get(groupCha).getMaLoaiSP(), Toast.LENGTH_SHORT).show();
            false
        }

        return viewGroupcha
    }

    override fun getChildView(groupCha: Int, groupCon: Int, isLastChild: Boolean, convertView: View, parent: ViewGroup): View {
        //     LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //        View viewCon = layoutInflater.inflate(R.layout.custom_layout_gorupcon,parent,false);
        //        ExpandableListView expandableListView = (ExpandableListView) viewCon.findViewById(R.id.ep_menucon);
        val viewGroupcha = convertView

        val sencondExpanalbe = SencondExpanalbe(context)
        val secondAdapter = Adapter_Expand(context, loaiSanPhams[groupCha].listcon)

        sencondExpanalbe.setAdapter(secondAdapter)
        sencondExpanalbe.setGroupIndicator(null)
        notifyDataSetChanged()

        return sencondExpanalbe
    }

    inner class SencondExpanalbe(context: Context) : ExpandableListView(context) {

        override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
            var heightMeasureSpec = heightMeasureSpec

            val windowManager = context.getSystemService(context.WINDOW_SERVICE) as WindowManager
            val display = windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)

            val width = size.x
            val height = size.y
            Log.d("size", width.toString() + "-" + height)

            //    widthMeasureSpec = MeasureSpec.makeMeasureSpec(width,MeasureSpec.AT_MOST);
            heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.AT_MOST)

            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return false
    }


    // --------------------------------------cấp 3--------------------------------------------------------
    //    public class SecondAdapter extends BaseExpandableListAdapter{
    //        List<LoaiSanPham> listcon;
    //
    //        public SecondAdapter(List<LoaiSanPham> listcon) {
    //            this.listcon = listcon;
    //
    //            XuLy_JsonMenu xuLy_jsonMenu = new XuLy_JsonMenu();
    //
    //            int count= listcon.size();
    //            for (int i=0; i<count;i++){
    //                int maloaisp = listcon.get(i).getMaLoaiSP();
    //                listcon.get(i).setListcon(xuLy_jsonMenu.loaiSanPhamtheomaloai(maloaisp));
    //            }
    //        }
    //        @Override
    //        public int getGroupCount() {
    //            return listcon.size();
    //        }
    //
    //        @Override
    //        public int getChildrenCount(int groupCha) {
    //            return listcon.get(groupCha).getListcon().size();
    //        }
    //
    //        @Override
    //        public Object getGroup(int groupCha) {
    //            return listcon.get(groupCha);
    //        }
    //
    //        @Override
    //        public Object getChild(int groupCha, int groupCon) {
    //            return listcon.get(groupCha).getListcon().get(groupCon);
    //        }
    //
    //        @Override
    //        public long getGroupId(int groupCha) {
    //            return listcon.get(groupCha).getMaLoaiSP();
    //        }
    //
    //        @Override
    //        public long getChildId(int groupCha, int groupCon)
    //        {
    //            return listcon.get(groupCha).getListcon().get(groupCon).getMaLoaiSP();
    //        }
    //
    //        @Override
    //        public boolean hasStableIds() {
    //            return false;
    //        }
    //
    //        @Override
    //        public View getGroupView(int groupCha, boolean isExpanded, View convertView, ViewGroup parent)
    //        {
    //            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    //            View viewCha = layoutInflater.inflate(R.layout.custom_layout_gorupcha,parent,false);
    //            TextView txtTenLoaisp = (TextView) viewCha.findViewById(R.id.txt_tenloaisp);
    //            txtTenLoaisp.setText(listcon.get(groupCha).getTenLoaiSP());
    //            return viewCha;
    //        }
    //
    //        @Override
    //        public View getChildView(int groupCha, int groupCon, boolean isLastChild, View convertView, ViewGroup parent)
    //        {
    ////            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    ////            View viewCha = layoutInflater.inflate(R.layout.custom_layout_gorupcha,parent,false);
    ////            TextView txtTenLoaisp = (TextView) viewCha.findViewById(R.id.txt_tenloaisp);
    ////            txtTenLoaisp.setText(listcon.get(groupCha).getListcon().get(groupCon).getTenLoaiSP());
    ////            return viewCha;
    //            TextView textView = new TextView(context);
    //            textView.setText(listcon.get(groupCha).getListcon().get(groupCon).getTenLoaiSP());
    //            textView.setPadding(15,5,5,5);
    //            textView.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
    //
    //            return textView ;
    //        }
    //
    //        @Override
    //        public boolean isChildSelectable(int groupPosition, int childPosition) {
    //            return false;
    //        }
    //    }


}

