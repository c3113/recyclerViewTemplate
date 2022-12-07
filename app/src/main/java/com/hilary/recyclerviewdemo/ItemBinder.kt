package com.hilary.recyclerviewdemo

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.Model
import com.hilary.recyclerview.TemplateViewBinder
import com.hilary.recyclerview.TemplateViewHolder

/**
 * @description:
 * @author ChenKai
 * @date 2022/12/7
 */
class ItemBinder(override val onClick: ((RecyclerViewModel) -> Unit)) : TemplateViewBinder<RecyclerViewModel>() {
    override fun itemViewType() = R.layout.adapter_item

    override fun binderView(model: RecyclerViewModel, viewHolder: TemplateViewHolder) {
        viewHolder.itemView.findViewById<TextView>(R.id.descriptionView).text = model.description
        val imageView = viewHolder.itemView.findViewById<ImageView>(R.id.imageView)
        Glide.with(imageView.context).load(model.url).into(imageView)
        viewHolder.itemView.setOnClickListener {
            onClick(model)
        }
    }
}