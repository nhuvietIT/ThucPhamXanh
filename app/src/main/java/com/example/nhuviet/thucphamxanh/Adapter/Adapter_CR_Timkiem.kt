package com.example.nhuviet.thucphamxanh.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Timkiem
import com.example.nhuviet.thucphamxanh.R
import com.example.nhuviet.thucphamxanh.View.ChiTiec_SanPham.ChiTiec_SanPham_Mua
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

import java.text.DecimalFormat
import java.text.NumberFormat

/**
 * Created by nhuvi on 17/06/2017.
 */

class Adapter_CR_Timkiem(internal var context: Context, internal var timkiems: List<Timkiem>) : RecyclerView.Adapter<Adapter_CR_Timkiem.ViewHolder_timkiem>() {

    inner class ViewHolder_timkiem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var textView_tenSP: TextView
        internal var textView_giaSP: TextView
        internal var imageView_SP: ImageView
        internal var progressBar: ProgressBar
        internal var cardView_tk: CardView

        init {
            textView_tenSP = itemView.findViewById(R.id.tx_RC_tẹnSP_GH) as TextView
            textView_giaSP = itemView.findViewById(R.id.tx_giaSP_GH) as TextView
            imageView_SP = itemView.findViewById(R.id.im_Rc_loai_GH) as ImageView
            progressBar = itemView.findViewById(R.id.pro_loads1) as ProgressBar
            cardView_tk = itemView.findViewById(R.id.cardView_GH) as CardView

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder_timkiem {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.custom_layout_rc_timkiem, parent, false)
        val viewHolder_timkiem = ViewHolder_timkiem(view)
        return viewHolder_timkiem
    }

    override fun onBindViewHolder(holder: ViewHolder_timkiem, position: Int) {
        val timkiemList = timkiems[position]


        holder.cardView_tk.setOnClickListener {
            val intent_rau = Intent(context, ChiTiec_SanPham_Mua::class.java)
            intent_rau.putExtra("masp", timkiemList.maSanPham)
            intent_rau.putExtra("tenSP", timkiemList.tenSanPham)
            context.startActivity(intent_rau)
        }

        holder.textView_tenSP.text = timkiemList.tenSanPham

        val numberFormat = DecimalFormat("###,###")
        val gia = numberFormat.format(timkiemList.gia.toLong()).toString()
        holder.textView_giaSP.text = gia + " VNĐ"

        Picasso.with(context).load(timkiemList.hinhLon).resize(220, 220).into(holder.imageView_SP, object : Callback {
            override fun onSuccess() {
                holder.progressBar.visibility = View.GONE
            }

            override fun onError() {

            }
        })


    }

    override fun getItemCount(): Int {
        return timkiems.size
    }


}
