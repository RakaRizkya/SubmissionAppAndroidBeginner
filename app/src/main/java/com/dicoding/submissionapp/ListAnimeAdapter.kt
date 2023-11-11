package com.dicoding.submissionapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.submissionapp.databinding.ItemRowAnimeBinding

class ListAnimeAdapter(val listAnime: ArrayList<Anime>): RecyclerView.Adapter<ListAnimeAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemRowAnimeBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListAnimeAdapter.ListViewHolder {
        val binding = ItemRowAnimeBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListAnimeAdapter.ListViewHolder, position: Int) {
       val (title, genre, rating, synopsis, picture) = listAnime[position]
        holder.binding.imgItemPhoto.setImageResource(picture)
        holder.binding.tvItemName.text = title
        holder.binding.tvItemDescription.text = synopsis

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listAnime[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = listAnime.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Anime)
    }
}