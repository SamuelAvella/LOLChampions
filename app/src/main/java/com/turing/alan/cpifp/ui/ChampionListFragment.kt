package com.turing.alan.cpifp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.turing.alan.cpifp.data.Champion
import com.turing.alan.cpifp.data.ChampionsRepository
import com.turing.alan.cpifp.data.InMemoryChampionsRepository
import com.turing.alan.cpifp.databinding.FragmentChampionListBinding

class ChampionListFragment : Fragment() {

    private val repository: ChampionsRepository = InMemoryChampionsRepository.getInstance()
    private lateinit var binding: FragmentChampionListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentChampionListBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ChampionAdapter(::toChampionDetail)
        val rv = binding.championsList
        rv.adapter = adapter
        (rv.adapter as ChampionAdapter).submitList(repository.readAll())
    }

    override fun onResume() {
        super.onResume()
        val rv = binding.championsList
        (rv.adapter as ChampionAdapter).submitList(repository.readAll())
    }

    private fun toChampionDetail(champion: Champion) {
        val action = ChampionListFragmentDirections.actionChampionListFragmentToChampionDetailFragment(champion.id)
        findNavController().navigate(action)
    }
}


