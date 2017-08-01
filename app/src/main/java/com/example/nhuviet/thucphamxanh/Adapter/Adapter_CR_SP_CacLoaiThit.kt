package com.example.nhuviet.thucphamxanh.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView

import com.example.nhuviet.thucphamxanh.Model.ObjectClass.CacLoai_Thit
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Sanpham
import com.example.nhuviet.thucphamxanh.R
import com.example.nhuviet.thucphamxanh.View.ChiTiec_SanPham.ChiTiec_SanPham_Mua
import com.example.nhuviet.thucphamxanh.View.HienThiSanPhamTheoDanhMuc.HienThiSanPhamTheoDanhMuc
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

import java.text.DecimalFormat
import java.text.NumberFormat

/**
 * Created by nhuvi on 07/06/2017.
 */

class Adapter_CR_SP_CacLoaiThit(internal var context: Context, internal var cacLoai_thitList: List<CacLoai_Thit>, internal var kiemtra: Boolean) : RecyclerView.Adapter<Adapter_CR_SP_CacLoaiThit.ViewHolder_SanPham_Thit>() {

    inner class ViewHolder_SanPham_Thit(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tx_tẹnloai_thit: TextView
        internal var tx_gialoai_thit: TextView
        internal var tx_giamgialoai_hit: TextView? = null
        internal var im_Rc_loai_thit: ImageView
        internal var pro_loads_thit: ProgressBar
        internal var linnerlayout_thit: LinearLayout

        init {
            tx_tẹnloai_thit = itemView.findViewById(R.id.tx_RC_tẹnloai_thit) as TextView
            tx_gialoai_thit = itemView.findViewById(R.id.tx_gialoai_thit) as TextView
            im_Rc_loai_thit = itemView.findViewById(R.id.im_Rc_loai_thit) as ImageView
            pro_loads_thit = itemView.findViewById(R.id.pro_loads_thit) as ProgressBar
            linnerlayout_thit = itemView.findViewById(R.id.linnerlayout_thit) as LinearLayout
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter_CR_SP_CacLoaiThit.ViewHolder_SanPham_Thit {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.custom_layout_rc_sp_cacloai_thit, parent, false)

        val viewHolder_sanPham_thit = ViewHolder_SanPham_Thit(view)
        return viewHolder_sanPham_thit
    }

    override fun onBindViewHolder(holder: Adapter_CR_SP_CacLoaiThit.ViewHolder_SanPham_Thit, position: Int) {

        val cacLoai_thit = cacLoai_thitList[position]

        holder.linnerlayout_thit.tag = cacLoai_thit.maSanPham
        holder.linnerlayout_thit.setOnClickListener { v ->
            val intent_thit = Intent(context, ChiTiec_SanPham_Mua::class.java)
            intent_thit.putExtra("masp", v.tag as Int)
            intent_thit.putExtra("tenSP", cacLoai_thit.tenSanPham)
            intent_thit.putExtra("kiemtra", kiemtra)
            context.startActivity(intent_thit)
        }

        holder.tx_tẹnloai_thit.text = cacLoai_thit.tenSanPham.toString()

        val numberFormat = DecimalFormat("###,###")
        val gia = numberFormat.format(cacLoai_thit.gia.toLong()).toString()
        holder.tx_gialoai_thit.text = gia + " VNĐ"



        Picasso.with(context).load(cacLoai_thit.hinhLon).resize(220, 220).into(holder.im_Rc_loai_thit, object : Callback {
            override fun onSuccess() {
                holder.pro_loads_thit.visibility = View.GONE
            }

            override fun onError() {

            }
        })


    }

    override fun getItemCount(): Int {
        return cacLoai_thitList.size
    }


}