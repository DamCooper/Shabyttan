package com.example.shabyttan


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shabyttan.models.Artwork
import com.example.shabyttan.models.Creator
import kotlinx.android.synthetic.main.artwork_item.view.*

class ArtworkAdapter(
    private val artworks : List<Artwork>
) : RecyclerView.Adapter<ArtworkAdapter.ArtworkViewHolder>(){

    class ArtworkViewHolder(view : View) : RecyclerView.ViewHolder(view){
        fun bindCreator(artwork : Artwork){
            itemView.artwork_title.text = artwork.title
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