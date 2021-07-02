package com.waheed.nytimes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.waheed.nytimes.R
import com.waheed.nytimes.data.remote.responses.PopularArticlesResponse
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.item_articles.view.*
import javax.inject.Inject

class ArticlesAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<ArticlesAdapter.ShoppingItemViewHolder>() {

    class ShoppingItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var articles: ArrayList<PopularArticlesResponse.Article> = ArrayList()

    fun submitList(articles: List<PopularArticlesResponse.Article>) {
        this.articles.clear()
        this.articles.addAll(articles)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemViewHolder {
        return ShoppingItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_articles,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ShoppingItemViewHolder, position: Int) {
        val article = articles[position]
        holder.itemView.apply {
            loadImageThumb(ivThumb, article)
            tvHeading.text = article.title
            tvDescription.text = article.abstract
        }
    }

    /**
     *
     */
    private fun loadImageThumb(ivThumb: ImageView, article: PopularArticlesResponse.Article?) {
        article?.let { art ->
            art.media?.let { media ->
                media.isNotEmpty().let { mediaAvailable ->
                    if (mediaAvailable) {
                        media.first().mediaMetadata?.let { metaData ->
                            metaData.isNotEmpty().let { metaDataAvailable ->
                                if (metaDataAvailable) {
                                    glide.load(metaData.first().url).into(ivThumb)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}