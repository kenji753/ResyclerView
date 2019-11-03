package com.example.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView


class ListAdapter(private val context: Context):  RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    var articles: MutableList<Data> = arrayListOf()


    //private val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getItemCount(): Int = articles.size

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
        holder.userIconImageView.setImageResource(R.mipmap.ic_launcher_round)
        holder.userNameTextView.text = articles[position].username
        holder.dateTextView.text = articles[position].date
        holder.contentTextView.text = articles[position].content
        holder.contentPictureView.setImageResource(R.color.colorPrimary)
        holder.LikeNum.text = articles[position].heartCount.toString()

        if (articles[position].heartState)
            holder.heartView.setImageResource(R.mipmap.heart2)
        else
            holder.heartView.setImageResource(R.mipmap.heart1)

        holder.heartView.setOnClickListener {
            if (!articles[position].heartState) {
                articles[position].heartState = true
                articles[position].heartCount++
                holder.heartView.setImageResource(R.mipmap.heart2)
                holder.LikeNum.text = articles[position].heartCount.toString()

            } else {
                articles[position].heartState = false
                articles[position].heartCount--
                holder.heartView.setImageResource(R.mipmap.heart1)
                holder.LikeNum.text = articles[position].heartCount.toString()

            }
        }
    }


}



