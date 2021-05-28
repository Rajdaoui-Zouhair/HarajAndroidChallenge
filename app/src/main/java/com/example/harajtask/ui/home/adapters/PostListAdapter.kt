package com.example.harajtask.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.harajtask.R
import com.example.harajtask.data.models.Post
import com.example.harajtask.databinding.ItemPostBinding
import com.example.harajtask.generics.ItemClick
import com.example.harajtask.ui.home.adapters.PostListAdapter.PostAdapterViewHolder
import java.text.SimpleDateFormat
import java.util.*

class PostListAdapter(private val itemClick: ItemClick<Post>, private val items: List<Post>) : RecyclerView.Adapter<PostAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapterViewHolder {
        return PostAdapterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false))
    }

    override fun onBindViewHolder(holder: PostAdapterViewHolder, position: Int) {
        holder.bindTo(items[position], position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class PostAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemPostBinding = ItemPostBinding.bind(itemView)
        private val context: Context = itemView.context

        fun bindTo(post: Post, position: Int) {

            Glide.with(context).load(post.thumbURL).into(binding.shapeableImageView)
            binding.tvItemTitle.text = post.title
            binding.tvItemDate.text = convertLongToTime(post.date)
            binding.tvItemCommentCount.text = context.getString(R.string.comment_count, post.commentCount)
            binding.tvItemLocation.text = post.city
            binding.tvItemUser.text = post.userName

            binding.root.setOnClickListener {
                itemClick.onItemClick(items[position])
            }
        }

        private fun convertLongToTime(time: Long): String {
            val date = Date(time)
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm")
            return format.format(date)
        }
    }

}
