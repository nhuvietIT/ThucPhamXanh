package com.example.nhuviet.thucphamxanh.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.example.nhuviet.thucphamxanh.Model.GioHang.Model_GioHang
import com.example.nhuviet.thucphamxanh.Model.ObjectClass.Chitiet_SP
import com.example.nhuviet.thucphamxanh.R
import com.example.nhuviet.thucphamxanh.View.GioHang.View_GioHang_Activity

import java.text.DecimalFormat
import java.text.NumberFormat

/**
 * Created by nhuvi on 16/06/2017.
 */

class Adapter_CR_GioHang(internal var context: Context, internal var chitiet_spList: MutableList<Chitiet_SP>) : RecyclerView.Adapter<Adapter_CR_GioHang.ViewHolder_GioHang>() {
    internal var model_gioHang: Model_GioHang

    init {
        model_gioHang = Model_GioHang()
        model_gioHang.MoKetNoiSQL(context)
    }

    inner class ViewHolder_GioHang(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var textView_tenSP: TextView
        internal var textView_giaSP: TextView
        internal var textView_soluong: TextView
        internal var imageView_SP: ImageView
        internal var imageView_xoa: ImageView
        internal var imageButton_giam: ImageButton
        internal var imageButton_tang: ImageButton

        init {
            textView_tenSP = itemView.findViewById(R.id.tx_RC_tẹnSP_GH) as TextView
            textView_giaSP = itemView.findViewById(R.id.tx_giaSP_GH) as TextView
            textView_soluong = itemView.findViewById(R.id.tx_soluong) as TextView
            imageView_SP = itemView.findViewById(R.id.im_Rc_loai_GH) as ImageView
            imageView_xoa = itemView.findViewById(R.id.im_xoa_GH) as ImageView
            imageButton_giam = itemView.findViewById(R.id.giam_gh) as ImageButton
            imageButton_tang = itemView.findViewById(R.id.tang_gh) as ImageButton

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder_GioHang {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.custom_layout_giohang, parent, false)

        val viewHolder_gioHang = ViewHolder_GioHang(view)
        return viewHolder_gioHang
    }

    override fun onBindViewHolder(holder: ViewHolder_GioHang, position: Int) {
        val chitiet_sp = chitiet_spList[position]

        holder.textView_tenSP.text = chitiet_sp.tenSanPham

        val numberFormat = DecimalFormat("###,###")
        val gia = numberFormat.format(chitiet_sp.gia.toLong()).toString()
        holder.textView_giaSP.text = gia + " VNĐ"

        val hinhsp = chitiet_sp.hinhgiohang
        val bm = BitmapFactory.decodeByteArray(hinhsp, 0, hinhsp.size)
        holder.imageView_SP.setImageBitmap(bm)

        holder.imageView_xoa.tag = chitiet_sp.maSanPham

        holder.imageView_xoa.setOnClickListener { v ->
            val model_giohang = Model_GioHang()
            model_giohang.MoKetNoiSQL(context)
            model_giohang.XoaSanPham_GioHang(v.tag as Int)
            chitiet_spList.removeAt(position)
            notifyDataSetChanged()
        }

        holder.textView_soluong.text = chitiet_sp.soluong.toString()

        holder.imageButton_giam.setOnClickListener {
            var soluong = Integer.parseInt(holder.textView_soluong.text.toString())

            if (soluong > 1) {
                soluong--
            }
            model_gioHang.CapnhatsoluongSP_giohang(chitiet_sp.maSanPham, soluong)
            holder.textView_soluong.text = soluong.toString()
        }

        holder.imageButton_tang.setOnClickListener {
            var soluong = Integer.parseInt(holder.textView_soluong.text.toString())
            val soluongtonkho = chitiet_sp.soLuongTonKho
            if (soluong < soluongtonkho) {
                soluong++
            } else {
                Toast.makeText(context, "Bạn mua vượt quá số lượng sản phẩm tồn kho..!", Toast.LENGTH_SHORT).show()
            }
            soluong++

            model_gioHang.CapnhatsoluongSP_giohang(chitiet_sp.maSanPham, soluong)
            holder.textView_soluong.text = soluong.toString()
        }


    }

    override fun getItemCount(): Int {
        return chitiet_spList.size
    }


}
