package com.example.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ListAdapter(private val context: Context) :
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    var stores: MutableList<Data.StoreID> = arrayListOf()


    //private val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getItemCount(): Int = stores.size

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userIconImageView: ImageView = view.findViewById(R.id.userIcon)
        val userNameTextView: TextView = view.findViewById(R.id.userName)
        val contentTextView: TextView = view.findViewById(R.id.content)
        val dateTextView: TextView = view.findViewById(R.id.date)
        val contentPictureView: ImageView = view.findViewById(R.id.contentPicture)
        val _tagTextView: TextView = view.findViewById(R.id._tag)
        val heartView: ImageView = view.findViewById(R.id.heart)
        var LikeNum: TextView = view.findViewById(R.id.likeCount)
    }

    override fun getItemId(p0: Int): Long = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_timeline,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val store = stores[position]
        holder.apply {
            userIconImageView.setImageResource(R.mipmap.ic_launcher_round)
            userNameTextView.text = store.store.name
            dateTextView.text = store.store.businessHours
            contentTextView.text = store.store.content
            contentPictureView.setImageResource(R.color.colorPrimary)
            LikeNum.text = stores[position].post.likeCount.toString()
            store.post.likeState?.let { state ->
                if (state) {
                    return@let heartView.setImageResource(R.mipmap.heart2)
                }
                heartView.setImageResource(R.mipmap.heart1)
            }
        }

        holder.heartView.setOnClickListener {
            store.post.likeState?.let { state ->
                if (!state) {
                    store.post.likeCount = store.post.likeCount?.run { this + 1 }
                    holder.heartView.setImageResource(R.mipmap.heart2)
                    holder.LikeNum.text = store.post.likeCount.toString()
                } else {
                    store.post.likeCount = store.post.likeCount?.run { this - 1 }
                    holder.heartView.setImageResource(R.mipmap.heart1)
                    holder.LikeNum.text = store.post.likeCount.toString()
                }
                store.post.likeState = !state
            }
        }
    }


}



