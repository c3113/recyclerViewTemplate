package com.hilary.recyclerview

import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

/**
 * @description:    基础Adapter，只涉及数据相关操作
 * @author ChenKai
 * @date 2022/12/2
 */
abstract class ModelAdapter<Model : Any, Binder: ViewHolder> : Adapter<Binder>() {
    var values = mutableListOf<Model>()
        set(list) {
            values.clear()
            values.addAll(list)
        }
    override fun getItemCount() = values.size
}