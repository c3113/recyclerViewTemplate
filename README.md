使用RecyclerView时，解决每个RecyclerView创建一个新的Adapter重复工作
使用时，只需要创建一个数据Model，一个ViewBinder，ViewBinder中实现数据与View之间的映射关系，比如：
```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = TemplateAdapter().apply {
            addBinder(RecyclerViewModel::class.java, ItemBinder{ model ->
                onClickModel(model)
            })
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        val list = mutableListOf<Any>()
        list.add(RecyclerViewModel("小狗小狗小狗", "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.dtstatic.com%2Fuploads%2Fblog%2F202103%2F31%2F20210331160001_9a852.thumb.1000_0.jpg&refer=http%3A%2F%2Fc-ssl.dtstatic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672987756&t=7c768151ffa695fa813f8c4b361791d7"))
        list.add(RecyclerViewModel("美女美女美女", "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fblog%2F202106%2F09%2F20210609081952_51ef5.thumb.1000_0.jpg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672987756&t=f8f393008f2652c7e55eeac46a55eb0d"))
        list.add(RecyclerViewModel("youyouyouyou", "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fblog%2F202105%2F10%2F20210510174256_4b4d0.thumb.1000_0.jpeg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672987756&t=ee1f2cd80c2181d2db92f412e0e2972b"))
        list.add(RecyclerViewModel("帅不帅帅不帅", "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fblog%2F202105%2F10%2F20210510174256_4b4d0.thumb.1000_0.jpeg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672987756&t=ee1f2cd80c2181d2db92f412e0e2972b"))
        list.addAll(list)
        list.addAll(list)
        list.addAll(list)
        adapter.values = list
    }
}

data class RecyclerViewModel(val description: String, val url: String)

//不同的样式需要对应不同的TemplateViewBinder实现类
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
```
