package com.example.shabyttan


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.artwork_item.view.*

class FavoriteAdapter(
    private val favorites : List<Favorite>
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>(){

    class FavoriteViewHolder(view : View) : RecyclerView.ViewHolder(view){
        fun bindFavorite(favorite : Favorite){
            itemView.artwork_title.text = favorite.title
            val resizeImage = favorite.imageUrl
            Glide.with(itemView.context)
                .load(resizeImage)
                .into(itemView.artwork_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.artwork_item, parent, false)
        )
    }

    override fun getItemCount(): Int = favorites.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindFavorite(favorites.get(position))
        holder.itemView.setOnClickListener {
            val activity = it.context as AppCompatActivity
            activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, MainFragment.newInstance(favorites.get(position).id.toString(), ""))
                .commitNow()
        }
    }
}