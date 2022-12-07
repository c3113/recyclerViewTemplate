package com.hilary.recyclerview

/**
 * @description:    通过此类把数据绑定到View上
 *                  点击事件：在创建Binder时添加点击事件，根据需求传不同的参数
 * @author ChenKai
 * @date 2022/12/2
 */
abstract class TemplateViewBinder<Model>(
    open val onClick: ((model: Model) -> Unit)?,
    open val onLongClick: ((model: Model) -> Unit)?
    ) {

    constructor(onClick: ((model: Model) -> Unit)?) : this(onClick, null)

    constructor() : this(null, null)

    /**
     * 获取View布局
     */
    abstract fun itemViewType(): Int

    /**
     * 数据绑定到View的操作类
     */
    abstract fun binderView(model: Model, viewHolder: TemplateViewHolder)
}