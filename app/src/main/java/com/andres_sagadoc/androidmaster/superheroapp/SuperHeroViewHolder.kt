package com.andres_sagadoc.androidmaster.superheroapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.andres_sagadoc.androidmaster.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemSuperheroBinding.bind(view)
    fun bind(superHeroResponse: SuperHeroResponse) {
        binding.tvSuperHeroName.text = superHeroResponse.name
        Picasso.get()
            .load(superHeroResponse.image.url)
            .into(binding.ivSuperHero)
    }
}