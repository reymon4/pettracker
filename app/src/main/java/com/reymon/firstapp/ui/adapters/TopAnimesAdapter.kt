package com.reymon.firstapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.reymon.firstapp.R
import com.reymon.firstapp.data.network.entities.top.Data
import com.reymon.firstapp.databinding.LayoutAnimesBinding

class TopAnimesAdapter(private val listUsers: List<Data>) :
    RecyclerView.Adapter<TopAnimesAdapter.ViewHolderAnime>() {

    inner class ViewHolderAnime(view: View) : RecyclerView.ViewHolder(view) {

        private var binding: LayoutAnimesBinding = LayoutAnimesBinding.bind(view)

        fun render(item: Data) {
            binding.image.load(item.images.jpg.large_image_url)
            binding.txtID.text = item.title_english
            binding.txtName.text = item.synopsis
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopAnimesAdapter.ViewHolderAnime {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolderAnime(
            inflater.inflate(
                R.layout.layout_animes,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = listUsers.size

    override fun onBindViewHolder(holder: ViewHolderAnime, position: Int) {
        holder.render(listUsers[position])
    }
}
