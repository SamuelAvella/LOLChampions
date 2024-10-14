package com.turing.alan.cpifp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.turing.alan.cpifp.R
import com.turing.alan.cpifp.data.Champion
import com.turing.alan.cpifp.databinding.ChampionListItemBinding

class ChampionAdapter : ListAdapter<Champion, ChampionAdapter.ChampionViewHolder>(ChampionDiffCallback) {

    class ChampionViewHolder(private val binding: ChampionListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(champion: Champion){
            binding.championName.text = champion.name
            binding.championTitle.text =champion.title
            binding.championImage.load(champion.imageUrl) {
                transformations(RoundedCornersTransformation(16f))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionViewHolder {
        val binding = ChampionListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChampionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChampionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object ChampionDiffCallback : DiffUtil.ItemCallback<Champion>() {
        override fun areItemsTheSame(oldItem: Champion, newItem: Champion) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Champion, newItem: Champion) = oldItem == newItem
    }
}

