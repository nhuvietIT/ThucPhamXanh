package com.example.nhuviet.thucphamxanh.Adapter

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.example.nhuviet.thucphamxanh.Model.ObjectClass.ThuongHieuvaSanpham
import com.example.nhuviet.thucphamxanh.R

/**
 * Created by nhuvi on 06/06/2017.
 */

class Adapter_CR_H_Home(internal var context: Context, internal var thuongHieuvaSanphamListl: List<ThuongHieuvaSanpham>) : RecyclerView.Adapter<Adapter_CR_H_Home.ViewHolder_Home>() {

    inner class ViewHolder_Home(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tenSP: TextView
        internal var tenloaiSP: TextView
        internal var recycler_quangcao: RecyclerView
        internal var recyclerView_thuonghieu: RecyclerView
        internal var recyclerView_loaiTP: RecyclerView

        init {

            recycler_quangcao = itemView.findViewById(R.id.recycler_quangcao) as RecyclerView
            tenSP = itemView.findViewById(R.id.tx_tensp) as TextView
            tenloaiSP = itemView.findViewById(R.id.txt_tenloaisp) as TextView
            recyclerView_thuonghieu = itemView.findViewById(R.id.recycler_loaiTH) as RecyclerView
            recyclerView_loaiTP = itemView.findViewById(R.id.recycler_cacloaiTP) as RecyclerView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder_Home {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.custom_layout_recyclerview_h_home, parent, false)

        val viewHolder_home = ViewHolder_Home(view)

        return viewHolder_home
    }

    override fun onBindViewHolder(holder: ViewHolder_Home, position: Int) {

        val thuongHieuvaSanpham = thuongHieuvaSanphamListl[position]


        //      xu ly hinh quảng cáo
        val quangCao = Adapter_RC_QuangCao(context, thuongHieuvaSanpham.quangCaos)
        val layoutManager_qc = GridLayoutManager(context, 1, LinearLayoutManager.HORIZONTAL, false)

        holder.recycler_quangcao.adapter = quangCao
        holder.recycler_quangcao.layoutManager = layoutManager_qc
        quangCao.notifyDataSetChanged()



        holder.tenSP.text = thuongHieuvaSanpham.tenSP.toString()
        holder.tenloaiSP.text = thuongHieuvaSanpham.tenLoaiSP.toString()
        // xu ly danh sách thương hiệu
        val adapter_rc_h_thuongHieu = Adapter_RC_H_ThuongHieu(context, thuongHieuvaSanpham.thuongHieus)
        val layoutManagerTH = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)

        holder.recyclerView_thuonghieu.layoutManager = layoutManagerTH
        holder.recyclerView_thuonghieu.adapter = adapter_rc_h_thuongHieu

        //    xu ly hiển thị ds ua chuong
        val uaChuong = Adapter_CR_SP_Ua_Chuong(context, thuongHieuvaSanpham.sanPhamUaChuongs, thuongHieuvaSanpham.isSP_UaChuong)
        val layoutManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
        holder.recyclerView_loaiTP.layoutManager = layoutManager
        holder.recyclerView_loaiTP.adapter = uaChuong
        uaChuong.notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return thuongHieuvaSanphamListl.size
    }


}
