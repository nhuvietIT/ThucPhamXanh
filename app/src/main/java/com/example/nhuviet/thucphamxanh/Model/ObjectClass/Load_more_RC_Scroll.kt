package com.example.nhuviet.thucphamxanh.Model.ObjectClass

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout

/**
 * Created by nhuvi on 11/06/2017.
 */

class Load_more_RC_Scroll(internal var layoutManager: RecyclerView.LayoutManager, internal var iLoadMore: ILoad_more) : RecyclerView.OnScrollListener() {
    internal var itemandautien = 0
    internal var tongitem = 0
    internal var itemloadTR = 7

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        tongitem = layoutManager.itemCount

        if (layoutManager is LinearLayoutManager) {
            itemandautien = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        } else if (layoutManager is GridLayoutManager) {
            itemandautien = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        }

        //        Log.d("ktRC",tongitem + "-" + itemandautien);
        if (tongitem <= itemandautien + itemloadTR) {
            iLoadMore.LoadMore(tongitem)
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
    }
}
