package com.example.shabyttan


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shabyttan.models.Creator
import kotlinx.android.synthetic.main.creator_item.view.*

class CreatorAdapter(
    private val creators : List<Creator>
) : RecyclerView.Adapter<CreatorAdapter.CreatorViewHolder>(){

    class CreatorViewHolder(view : View) : RecyclerView.ViewHolder(view){
        fun bindCreator(Creator : Creator){
            itemView.artwork_title.text = Creator.name
            itemView.creator_lifecycle.text = "${Creator.birth_year} - ${Creator.death_year}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatorViewHolder {
        return CreatorViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.creator_item, parent, false)
        )
    }

    override fun getItemCount(): Int = creators.size

    override fun onBindViewHolder(holder: CreatorViewHolder, position: Int) {
        holder.bindCreator(creators.get(position))
    }
}