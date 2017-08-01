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

import com.example.nhuviet.thucphamxanh.Model.ObjectClass.CacLoai_Rau
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.CacloaiRauVacacloaiThit
import com.example.nhuviet.thucphamxanh.R
import com.example.nhuviet.thucphamxanh.View.ChiTiec_SanPham.ChiTiec_SanPham_Mua
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

import java.text.DecimalFormat
import java.text.NumberFormat

/**
 * Created by nhuvi on 06/06/2017.
 */

class Adapter_RC_SP_Cacloairau(internal var context: Context, internal var cacloairau: List<CacLoai_Rau>, internal var kiemtra: Boolean) : RecyclerView.Adapter<Adapter_RC_SP_Cacloairau.ViewHolder_Cacloairau>() {

    inner class ViewHolder_Cacloairau(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tenloairau: TextView
        internal var gialoairau: TextView
        internal var giamgia: TextView? = null
        internal var hinhloairau: ImageView
        internal var progressBar: ProgressBar
        internal var linearLayout_rau: LinearLayout

        init {
            tenloairau = itemView.findViewById(R.id.tx_RC_tẹnloairau) as TextView
            gialoairau = itemView.findViewById(R.id.tx_gialoairau) as TextView
            hinhloairau = itemView.findViewById(R.id.im_Rc_loairau) as ImageView
            progressBar = itemView.findViewById(R.id.pro_loads1) as ProgressBar
            linearLayout_rau = itemView.findViewById(R.id.linearLayout_rau) as LinearLayout

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder_Cacloairau {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.custom_layout_recyclerview_sp_cacloairau, parent, false)


        val viewHolder_cacloairau = ViewHolder_Cacloairau(view)
        return viewHolder_cacloairau
    }

    override fun onBindViewHolder(holder: ViewHolder_Cacloairau, position: Int) {
        val cacloairas = cacloairau[position]

        holder.linearLayout_rau.tag = cacloairas.maSanPham
        holder.linearLayout_rau.setOnClickListener { v ->
            val intent_rau = Intent(context, ChiTiec_SanPham_Mua::class.java)
            intent_rau.putExtra("masp", v.tag as Int)
            intent_rau.putExtra("tenSP", cacloairas.tenSanPham)
            intent_rau.putExtra("kiemtra", kiemtra)
            context.startActivity(intent_rau)
        }

        holder.tenloairau.text = cacloairas.tenSanPham

        val numberFormat = DecimalFormat("###,###")
        val gia = numberFormat.format(cacloairas.gia.toLong()).toString()
        holder.gialoairau.text = gia + " VNĐ"
        Picasso.with(context).load(cacloairas.hinhLon).resize(220, 220).into(holder.hinhloairau, object : Callback {
            override fun onSuccess() {
                holder.progressBar.visibility = View.GONE
            }

            override fun onError() {

            }
        })

    }

    override fun getItemCount(): Int {
        return cacloairau.size
    }


}
