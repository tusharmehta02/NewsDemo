package com.assignment.doubtnut.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.assignment.doubtnut.R
import com.assignment.doubtnut.model.NewsResponse
import com.assignment.doubtnut.utility.Utils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_news_list.view.*

/**
 * Created by tusharmehta on 27/03/20
 */
class NewsListAdapter(private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<NewsListAdapter.ItemHolder>() {

    private lateinit var context: Context

    private var itemList = listOf<NewsResponse.Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        context = parent.context
        return ItemHolder(
            LayoutInflater.from(context)
                .inflate(
                    R.layout.item_news_list,
                    parent,
                    false
                )
        )
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val items = itemList[holder.adapterPosition]
        holder.bind(items, context, itemClickListener)
    }

    fun setNews(articles: List<NewsResponse.Article>) {
        itemList = articles
        notifyDataSetChanged()
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tv_source = itemView.tv_source
        private val tv_news_title = itemView.tv_news_title
        private val tv_news_date = itemView.tv_news_date
        private val iv_news_thumbnail = itemView.iv_news_thumbnail

        fun bind(
            item: NewsResponse.Article,
            context: Context,
            itemClickListener: ItemClickListener
        ) {
            tv_source.text = item.source.name
            tv_news_title.text = item.title
            tv_news_date.text =
                Utils.getFormattedDate(item.publishedAt)
            Glide.with(context).load(item.urlToImage).override(100, 100).error(R.mipmap.ic_launcher)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
                .placeholder(R.mipmap.ic_launcher).into(iv_news_thumbnail)

            itemView.setOnClickListener {
                itemClickListener.onItemClick(item, iv_news_thumbnail)
            }
        }


    }

    interface ItemClickListener {
        fun onItemClick(item: NewsResponse.Article, sharedElement: AppCompatImageView)
    }

}