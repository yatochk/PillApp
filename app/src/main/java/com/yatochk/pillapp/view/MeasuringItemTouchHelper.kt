package com.yatochk.pillapp.view

import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.yatochk.pillapp.view.adapter.PressureViewHolder
import com.yatochk.pillapp.view.adapter.TemperatureViewHolder

class MeasuringItemTouchHelper(
    dragDirs: Int,
    swipeDirs: Int,
    private val listener: RecyclerItemTouchHelperListener
) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = true

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (viewHolder != null) {
            val typeViewHolder: DeletableViewHolder =
                (viewHolder as? PressureViewHolder) ?: (viewHolder as TemperatureViewHolder)
            val foregroundView = typeViewHolder.foreground
            ItemTouchHelper.Callback.getDefaultUIUtil().onSelected(foregroundView)
        }
    }

    override fun onChildDrawOver(
        c: Canvas, recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float,
        actionState: Int, isCurrentlyActive: Boolean
    ) {
        val typeViewHolder: DeletableViewHolder =
            (viewHolder as? PressureViewHolder) ?: (viewHolder as TemperatureViewHolder)
        val foregroundView = typeViewHolder.foreground
        ItemTouchHelper.Callback.getDefaultUIUtil().onDrawOver(
            c, recyclerView, foregroundView, dX, dY,
            actionState, isCurrentlyActive
        )
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        val typeViewHolder: DeletableViewHolder =
            (viewHolder as? PressureViewHolder) ?: (viewHolder as TemperatureViewHolder)
        val foregroundView = typeViewHolder.foreground
        ItemTouchHelper.Callback.getDefaultUIUtil().clearView(foregroundView)
    }

    override fun onChildDraw(
        canvas: Canvas, recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float,
        actionState: Int, isCurrentlyActive: Boolean
    ) {
        val typeViewHolder: DeletableViewHolder =
            (viewHolder as? PressureViewHolder) ?: (viewHolder as TemperatureViewHolder)
        val foregroundView = typeViewHolder.foreground

        ItemTouchHelper.Callback.getDefaultUIUtil().onDraw(
            canvas, recyclerView, foregroundView, dX, dY,
            actionState, isCurrentlyActive
        )
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener.onSwiped(viewHolder, direction, viewHolder.adapterPosition)
    }

    interface RecyclerItemTouchHelperListener {
        fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int)
    }
}