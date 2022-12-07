package com.hilary.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * @description:    通用Adapter
 * @author ChenKai
 * @date 2022/12/2
 */
class TemplateAdapter<Model : Any> : ModelAdapter<Model, TemplateViewHolder>() {

    /**
     * 存储数据模型与Binder的对应关系
     */
    private val binderMap = mutableMapOf<Class<Model>, TemplateViewBinder<Model>>()

    /**
     * 添加Binder与Model对应关系
     */
    fun addBinder(clazz: Class<Model>, binder: TemplateViewBinder<Model>) {
        binderMap[clazz] = binder
    }

    /**
     * 移除Binder
     */
    fun removeBinder(clazz: Class<Model>) {
        binderMap.remove(clazz)
    }

    /**
     * 通过位置拿到对应的数据Model，及Model对应的Binder，调用binder.binderView
     * 把数据映射到View上
     */
    override fun onBindViewHolder(holder: TemplateViewHolder, position: Int) {
        val model = values[position]
        val binder = binderMap[model::class.java]
        binder?.binderView(model, holder)
    }

    /**
     * 根据position位置，拿到相应的数据Model，再通过Model.class拿到对应的Binder
     * 通过调用binder拿到Binder对应的布局文件，把布局文件的id返回
     */
    override fun getItemViewType(position: Int): Int {
        val model = values[position]
        val binder = binderMap[model::class.java]
        return binder?.itemViewType() ?: throw java.lang.NullPointerException("未发现 $model 绑定的 Binder")
    }

    /**
     * viewType是getItemViewType(...)返回的布局id,可以直接使用此ID，生成相机的布局ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemplateViewHolder =
        TemplateViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))

}