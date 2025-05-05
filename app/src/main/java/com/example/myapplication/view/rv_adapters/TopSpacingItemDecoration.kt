package com.example.myapplication.view.rv_adapters

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class TopSpacingItemDecoration (private val paddindInOp: Int) : RecyclerView.ItemDecoration() {
    private val Int.cnvertPx: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = paddindInOp.cnvertPx
        outRect.right = paddindInOp.cnvertPx
        outRect.left = paddindInOp.cnvertPx
    }
}