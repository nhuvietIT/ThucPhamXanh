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
import android.widget.Toast

import com.example.nhuviet.thucphamxanh.Model.ObjectClass.SanPhamUaChuong_SP
import com.example.nhuviet.thucphamxanh.R
import com.example.nhuviet.thucphamxanh.View.HienThiSanPhamTheoDanhMuc.HienThiSanPhamDM_Activity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

/**
 * Created by nhuvi on 08/06/2017.
 */

class Adapter_CR_SP_Ua_Chuong(internal var context: Context, internal var sanPhamUaChuong_spList: List<SanPhamUaChuong_SP>, internal var kiemtra: Boolean) : RecyclerView.Adapter<Adapter_CR_SP_Ua_Chuong.Viewholder_UaChuong>() {

    inner class Viewholder_UaChuong(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tenloaiSP: TextView
        internal var hinhThucPhamUC: ImageView
        internal var progressBar: ProgressBar
        internal var linearLayout: LinearLayout

        init {
            tenloaiSP = itemView.findViewById(R.id.tx_RC_tẹnSPuc) as TextView
            hinhThucPhamUC = itemView.findViewById(R.id.im_Rc_loaiSPuc) as ImageView
            progressBar = itemView.findViewById(R.id.pro_loadsuc) as ProgressBar
            linearLayout = itemView.findViewById(R.id.lick_linnerlayoutuc) as LinearLayout
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder_UaChuong {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.custom_layout_sp_sanpham_uachuong, parent, false)

        val viewholder_uaChuong = Viewholder_UaChuong(view)
        return viewholder_uaChuong
    }

    override fun onBindViewHolder(holder: Viewholder_UaChuong, position: Int) {
        val sanPhamUaChuong_sp = sanPhamUaChuong_spList[position]

        holder.linearLayout.setOnClickListener {
            val spuc = Intent(context, HienThiSanPhamDM_Activity::class.java)
            spuc.putExtra("malsp", sanPhamUaChuong_sp.maLoaiSanPhamUC)
            spuc.putExtra("tenSP", sanPhamUaChuong_sp.tenloaiSanPhamUC)
            spuc.putExtra("kiemtra", kiemtra)
            context.startActivity(spuc)

            //                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
            //                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //
            //                HienThiSanPhamTheoDanhMuc hienThiSanPhamTheoDanhMuc = new HienThiSanPhamTheoDanhMuc();
            //
            //                Bundle bundle = new Bundle();
            //
            //                bundle.putInt("malsp", sanPhamUaChuong_sp.getMaLoaiSanPhamUC());
            //                bundle.putBoolean("kiemtra", false);
            //                bundle.putString("tenSP", sanPhamUaChuong_sp.getTenloaiSanPhamUC());
            //
            //                hienThiSanPhamTheoDanhMuc.setArguments(bundle);
            //                fragmentTransaction.addToBackStack("Trang Chủ");
            //                fragmentTransaction.replace(R.id.themFragment, hienThiSanPhamTheoDanhMuc);
            //                fragmentTransaction.commit();


            Toast.makeText(context, sanPhamUaChuong_sp.tenloaiSanPhamUC, Toast.LENGTH_SHORT).show()
        }


        Picasso.with(context).load(sanPhamUaChuong_sp.hinhLoaiSanPhamUC).resize(220, 220).into(holder.hinhThucPhamUC, object : Callback {
            override fun onSuccess() {
                holder.progressBar.visibility = View.GONE
            }

            override fun onError() {

            }
        })
        holder.tenloaiSP.text = sanPhamUaChuong_sp.tenloaiSanPhamUC

    }

    override fun getItemCount(): Int {
        return sanPhamUaChuong_spList.size
    }

}
