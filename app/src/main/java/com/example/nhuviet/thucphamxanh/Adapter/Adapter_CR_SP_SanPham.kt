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

import com.example.nhuviet.thucphamxanh.Model.ObjectClass.G_SanPham_Tren
import com.example.nhuviet.thucphamxanh.R

/**
 * Created by nhuvi on 02/06/2017.
 */

class Adapter_CR_SP_SanPham(internal var context: Context, internal var g_sanPhamTrenList: List<G_SanPham_Tren>) : RecyclerView.Adapter<Adapter_CR_SP_SanPham.ViewHolderSanPhan>() {

    inner class ViewHolderSanPhan(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var recycler_quangcao: RecyclerView
        internal var recyclerView_tren: RecyclerView
        internal var recyclerView_giua: RecyclerView
        internal var recyclerView_duoi: RecyclerView
        internal var tx_tentren: TextView
        internal var getTx_tengiua: TextView
        internal var txt_tenduoi: TextView

        init {

            recycler_quangcao = itemView.findViewById(R.id.recycler_quangcao) as RecyclerView
            tx_tentren = itemView.findViewById(R.id.tx_tentren) as TextView
            getTx_tengiua = itemView.findViewById(R.id.tx_tengiua) as TextView
            txt_tenduoi = itemView.findViewById(R.id.txt_tenduoi) as TextView
            recyclerView_tren = itemView.findViewById(R.id.recycler_tren) as RecyclerView
            recyclerView_giua = itemView.findViewById(R.id.recycler_giua) as RecyclerView
            recyclerView_duoi = itemView.findViewById(R.id.recycler_duoi) as RecyclerView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter_CR_SP_SanPham.ViewHolderSanPhan {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.custom_layout_recyclerview_sp_sanpham_tren, parent, false)

        val viewHolderSanPham = ViewHolderSanPhan(view)

        return viewHolderSanPham
    }

    override fun onBindViewHolder(holder: Adapter_CR_SP_SanPham.ViewHolderSanPhan, position: Int) {

        val g_sanPhamTren = g_sanPhamTrenList[position]

        // xu ly hinh quảng cáo
        val quangCao = Adapter_RC_QuangCao(context, g_sanPhamTren.quangCaos)
        val layoutManager_qc = GridLayoutManager(context, 1, LinearLayoutManager.HORIZONTAL, false)

        holder.recycler_quangcao.adapter = quangCao
        holder.recycler_quangcao.layoutManager = layoutManager_qc
        quangCao.notifyDataSetChanged()
        //tieu de

        holder.tx_tentren.text = g_sanPhamTren.ten_SPTen.toString()
        holder.getTx_tengiua.text = g_sanPhamTren.ten_giua.toString()
        holder.txt_tenduoi.text = g_sanPhamTren.ten_SPDuoi.toString()


        //    xu ly hiển thị ds ua chuong
        val uaChuong = Adapter_CR_SP_Ua_Chuong(context, g_sanPhamTren.sanPhamUaChuong_sps, g_sanPhamTren.isSP_UaChuong)
        val layoutManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
        holder.recyclerView_tren.layoutManager = layoutManager
        holder.recyclerView_tren.adapter = uaChuong
        uaChuong.notifyDataSetChanged()
        //  xu lý hiển thị ds cac loai rau
        val cacloairau = Adapter_RC_SP_Cacloairau(context, g_sanPhamTren.cacLoai_raus, g_sanPhamTren.isLoaiRau)
        val layoutManager_rau = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
        holder.recyclerView_giua.layoutManager = layoutManager_rau
        holder.recyclerView_giua.adapter = cacloairau
        cacloairau.notifyDataSetChanged()

        // xu lý hiển thị ds cac loai Thit
        val loaiThit = Adapter_CR_SP_CacLoaiThit(context, g_sanPhamTren.cacLoai_thits, g_sanPhamTren.isLoaithit)
        val layoutManager_thit = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
        holder.recyclerView_duoi.layoutManager = layoutManager_thit
        holder.recyclerView_duoi.adapter = loaiThit

    }

    override fun getItemCount(): Int {

        return g_sanPhamTrenList.size
    }


}
