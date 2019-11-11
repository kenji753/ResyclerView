package com.example.recyclerview


/*
class Adapter(private val context: Context, private val dataList: List<Data>) :
    RecyclerView.Adapter<Adapter.ListViewHolder>() {

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userIconImageView: ImageView = view.findViewById(R.id.userIcon)
        val userNameTextView: TextView = view.findViewById(R.id.userName)
        val contentTextView: TextView = view.findViewById(R.id.content)
        val dateTextView: TextView = view.findViewById(R.id.date)
        val contentPictureView: ImageView = view.findViewById(R.id.contentPicture)
        val _tagTextView: TextView = view.findViewById(R.id._tag)
        val heartView: ImageView = view.findViewById(R.id.heart)
        var LikeNum: TextView = view.findViewById(R.id.likeCount)
        //var countLike: Int = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(LayoutInflater.from(context).inflate(R.layout.list_timeline, parent, false))

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.userIconImageView.setImageResource(R.mipmap.ic_launcher_round)
        holder.userNameTextView.text = dataList[position].username
        holder.dateTextView.text = dataList[position].date
        holder.contentTextView.text = dataList[position].content
        holder.contentPictureView.setImageResource(R.color.colorPrimary)
        holder.LikeNum.text=dataList[position].heartCount.toString()
        //holder.heartView.setImageResource(R.mipmap.heart1)
        if(dataList[position].heartState)
            holder.heartView.setImageResource(R.mipmap.heart2)
        else
            holder.heartView.setImageResource(R.mipmap.heart1)

        holder.heartView.setOnClickListener {
            if(!dataList[position].heartState)
            {
                dataList[position].heartState = true
                dataList[position].heartCount++
                holder.heartView.setImageResource(R.mipmap.heart2)
                holder.LikeNum.text=dataList[position].heartCount.toString()

            }
            else
            {
                dataList[position].heartState = false
                dataList[position].heartCount--
                holder.heartView.setImageResource(R.mipmap.heart1)
                holder.LikeNum.text=dataList[position].heartCount.toString()

            }
        }




    }



}
*/