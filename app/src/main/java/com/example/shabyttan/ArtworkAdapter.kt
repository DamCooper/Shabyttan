package com.example.shabyttan


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shabyttan.models.ArtData
import com.example.shabyttan.models.Artwork
import com.example.shabyttan.models.Creator
import kotlinx.android.synthetic.main.artwork_item.view.*
import kotlinx.android.synthetic.main.fragment_main.*

class ArtworkAdapter(
    private val artworks : List<ArtData>
) : RecyclerView.Adapter<ArtworkAdapter.ArtworkViewHolder>(){

    class ArtworkViewHolder(view : View) : RecyclerView.ViewHolder(view){
        fun bindCreator(artwork : ArtData){
            itemView.artwork_title.text = artwork.title

            val resizeImage = artwork.images.web.url
            Glide.with(itemView.context)
                .load(resizeImage)
                .into(itemView.artwork_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworkViewHolder {
        return ArtworkViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.artwork_item, parent, false)
        )
    }

    override fun getItemCount(): Int = artworks.size

    override fun onBindViewHolder(holder: ArtworkViewHolder, position: Int) {
        holder.bindCreator(artworks.get(position))
    }
}