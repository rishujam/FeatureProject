package com.example.emptyproject.pokemon.ui.listing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.emptyproject.databinding.ItemPokemonBinding
import com.example.emptyproject.pokemon.domain.model.Pokemon

/*
 * Created by Sudhanshu Kumar on 23/09/24.
 */

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    inner class PokemonViewHolder(val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(
            ItemPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = differ.currentList[position]
        holder.binding.apply {
            tvPokemonNameList.text = pokemon.name
            Glide.with(this.root)
                .load(pokemon.url)
                .into(ivPokemonList)
            root.setOnClickListener {
                onItemClickListener?.let { it(pokemon, position) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Pokemon, position: Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Pokemon, position: Int) -> Unit) {
        onItemClickListener = listener
    }
}