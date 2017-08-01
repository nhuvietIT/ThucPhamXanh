package com.example.nhuviet.thucphamxanh.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

import com.example.nhuviet.thucphamxanh.Model.ObjectClass.ThuongHieu
import com.example.nhuviet.thucphamxanh.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

/**
 * Created by nhuvi on 02/06/2017.
 */

class Adapter_RC_H_ThuongHieu(internal var context: Context, internal var thuongHieuList: List<ThuongHieu>) : RecyclerView.Adapter<Adapter_RC_H_ThuongHieu.ViewHoderThuongHieu>() {

    inner class ViewHoderThuongHieu(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tenTH: TextView
        internal var hinhTH: ImageView
        internal var progressBar: ProgressBar

        init {

            tenTH = itemView.findViewById(R.id.tx_RC_tieudeTH) as TextView
            hinhTH = itemView.findViewById(R.id.im_RC_loaiTH) as ImageView
            progressBar = itemView.findViewById(R.id.pro_load) as ProgressBar

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter_RC_H_ThuongHieu.ViewHoderThuongHieu {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.custom_layout_recyclerview_h_cacloaithuonghieu, parent, false)


        val viewHoderThuongHieu = ViewHoderThuongHieu(view)
        return viewHoderThuongHieu
    }

    override fun onBindViewHolder(holder: Adapter_RC_H_ThuongHieu.ViewHoderThuongHieu, position: Int) {
        val thuongHieu = thuongHieuList[position]
        holder.tenTH.text = thuongHieu.tenThuongHieu
        Picasso.with(context).load(thuongHieu.hinhThuongHieu).resize(220, 220).into(holder.hinhTH, object : Callback {
            override fun onSuccess() {
                holder.progressBar.visibility = View.GONE
            }

            override fun onError() {

            }
        })

    }

    override fun getItemCount(): Int {
        return thuongHieuList.size
    }


}
