package com.waheed.nytimes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.waheed.nytimes.R
import com.waheed.nytimes.data.remote.responses.ArticleSearchResponse
import kotlinx.android.synthetic.main.item_search_article.view.*
import javax.inject.Inject

class SearchArticlesAdapter @Inject constructor() :
    RecyclerView.Adapter<SearchArticlesAdapter.ImageViewHolder>() {
    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffCallback =
        object : DiffUtil.ItemCallback<ArticleSearchResponse.Response.Doc>() {
            override fun areItemsTheSame(
                oldItem: ArticleSearchResponse.Response.Doc,
                newItem: ArticleSearchResponse.Response.Doc
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ArticleSearchResponse.Response.Doc,
                newItem: ArticleSearchResponse.Response.Doc
            ): Boolean {
                return oldItem == newItem
            }
        }

    private val differ = AsyncListDiffer(this, diffCallback)

    var articles: List<ArticleSearchResponse.Response.Doc>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_search_article,
                parent,
                false
            )
        )
    }

    private var onItemClickListener: ((ArticleSearchResponse.Response.Doc) -> Unit)? = null

    fun setOnItemClickListener(listener: (ArticleSearchResponse.Response.Doc) -> Unit) {
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val doc = articles[position]
        holder.itemView.apply {
            tvHeading.text = String.format("%s", doc.headline.main)
            tvDescription.text = String.format("%s", doc.leadParagraph ?: doc.abstract)
            setOnClickListener {
                onItemClickListener?.let { click ->
                    click(doc)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}















