package com.turing.alan.cpifp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.turing.alan.cpifp.R
import com.turing.alan.cpifp.data.Champion
import com.turing.alan.cpifp.databinding.TaskListItemBinding

class ChampionAdapter(private val champions: List<Champion>) :
    RecyclerView.Adapter<ChampionAdapter.ChampionViewHolder>() {

    class ChampionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = TaskListItemBinding.bind(view)

        fun bind(champion: Champion) {
            binding.championName.text = champion.name
            binding.championTitle.text = champion.title
            binding.championDescription.text = champion.lore
            binding.championImage.load(champion.imageUrl) {
                transformations(RoundedCornersTransformation(16f))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_list_item, parent, false)
        return ChampionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChampionViewHolder, position: Int) {
        holder.bind(champions[position])
    }

    override fun getItemCount() = champions.size
}
