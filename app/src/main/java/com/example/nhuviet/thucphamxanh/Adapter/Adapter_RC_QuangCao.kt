package com.example.nhuviet.thucphamxanh.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar

import com.example.nhuviet.thucphamxanh.Model.ObjectClass.QuangCao
import com.example.nhuviet.thucphamxanh.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

/**
 * Created by nhuvi on 21/06/2017.
 */

class Adapter_RC_QuangCao(internal var context: Context, internal var quangCaoList: List<QuangCao>) : RecyclerView.Adapter<Adapter_RC_QuangCao.ViewHolder_QuangCao>() {

    inner class ViewHolder_QuangCao(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var im_Rc_loai_QC: ImageView
        internal var progressBar: ProgressBar

        init {
            im_Rc_loai_QC = itemView.findViewById(R.id.im_Rc_loai_QC) as ImageView
            progressBar = itemView.findViewById(R.id.pro_loads1) as ProgressBar
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder_QuangCao {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.custom_layout_quangcao, parent, false)

        val viewHolder_quangCao = ViewHolder_QuangCao(view)

        return viewHolder_quangCao
    }

    override fun onBindViewHolder(holder: ViewHolder_QuangCao, position: Int) {
        val quangCao = quangCaoList[position]

        Picasso.with(context).load(quangCao.hinhQuangCao).resize(990, 235).into(holder.im_Rc_loai_QC, object : Callback {
            override fun onSuccess() {
                holder.progressBar.visibility = View.GONE
            }

            override fun onError() {

            }
        })

    }

    override fun getItemCount(): Int {
        return quangCaoList.size
    }

}
