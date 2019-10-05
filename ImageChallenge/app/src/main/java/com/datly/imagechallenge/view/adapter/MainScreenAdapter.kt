package com.datly.imagechallenge.view.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.datly.imagechallenge.R
import com.datly.imagechallenge.data.model.Image
import com.datly.imagechallenge.data.model.Images

class MainScreenAdapter(
    private var onImageClicked: (Int) -> Unit,
    private var imageList: Images
): RecyclerView.Adapter<MainScreenAdapter.ViewHolder>() {

    override fun getItemCount() = imageList.images.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.main_screen_item,
            parent,
            false)
        return ViewHolder(itemView, parent.context)
    }

    override fun getItemViewType(position: Int) = position

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(holder.adapterPosition)

        holder.cardView.setOnClickListener { onImageClicked(holder.adapterPosition) }
    }

    fun setImageList(otherImageList: Images) {
        this.imageList = otherImageList
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        view: View,
        private var context: Context
    ): RecyclerView.ViewHolder(view) {

        lateinit var cardView: CardView
        lateinit var itemImage: ImageView
        lateinit var itemDescription: TextView

        fun onBind(position: Int) {
            cardView = itemView.findViewById(R.id.card_view_container)
            itemImage = itemView.findViewById(R.id.item_image)
            itemDescription = itemView.findViewById(R.id.item_description)

            val currentImage = imageList.images[position]

            Glide.with(context)
                .load(currentImage.url_link+".jpg")
                .listener(object : RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                })
                .into(itemImage)

            itemDescription.text = currentImage.title
        }
    }
}