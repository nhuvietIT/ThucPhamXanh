package com.example.nhuviet.thucphamxanh.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView

import com.example.nhuviet.thucphamxanh.Model.ObjectClass.SanPham_DMUC
import com.example.nhuviet.thucphamxanh.R
import com.example.nhuviet.thucphamxanh.View.ChiTiec_SanPham.ChiTiec_SanPham_Mua
import com.example.nhuviet.thucphamxanh.View.HienThiSanPhamTheoDanhMuc.HienThiSanPhamTheoDanhMuc
import com.example.nhuviet.thucphamxanh.View.RauAnLa.RauAnLa
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

/**
 * Created by nhuvi on 10/06/2017.
 */

class Adapter_CR_SP_DanhMucSP(internal var context: Context, internal var layout: Int, internal var sanPhamDmucList: List<SanPham_DMUC>) : RecyclerView.Adapter<Adapter_CR_SP_DanhMucSP.ViewHolder_DanhMuc>() {


    inner class ViewHolder_DanhMuc(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tenloaiSPDM: TextView
        internal var hinhThucPhamDM: ImageView
        internal var progressBarDM: ProgressBar
        internal var linearLayoutDM: LinearLayout
        internal var cardViewDM: CardView

        init {
            tenloaiSPDM = itemView.findViewById(R.id.tx_RC_tẹnSPDM) as TextView
            hinhThucPhamDM = itemView.findViewById(R.id.im_Rc_loaiSPDM) as ImageView
            progressBarDM = itemView.findViewById(R.id.pro_loadsDM) as ProgressBar
            linearLayoutDM = itemView.findViewById(R.id.lick_linnerlayoutDM) as LinearLayout
            cardViewDM = itemView.findViewById(R.id.cardViewDM) as CardView

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter_CR_SP_DanhMucSP.ViewHolder_DanhMuc {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(layout, parent, false)

        val viewHolder_danhMuc = ViewHolder_DanhMuc(view)
        return viewHolder_danhMuc
    }

    override fun onBindViewHolder(holder: Adapter_CR_SP_DanhMucSP.ViewHolder_DanhMuc, position: Int) {


        val sanPhamDmuc = sanPhamDmucList[position]

        holder.cardViewDM.setOnClickListener {
            val intentdm = Intent(context, RauAnLa::class.java)
            intentdm.putExtra("malsp", sanPhamDmuc.maLoaiSanPham)
            intentdm.putExtra("tenSP", sanPhamDmuc.tenLoaiSanPham)
            context.startActivity(intentdm)
        }

        Picasso.with(context).load(sanPhamDmuc.hinhSanPhamDM).resize(220, 220).into(holder.hinhThucPhamDM, object : Callback {
            override fun onSuccess() {
                holder.progressBarDM.visibility = View.GONE
            }

            override fun onError() {

            }
        })
        holder.tenloaiSPDM.text = sanPhamDmuc.tenLoaiSanPham


    }

    override fun getItemCount(): Int {
        return sanPhamDmucList.size
    }

}
