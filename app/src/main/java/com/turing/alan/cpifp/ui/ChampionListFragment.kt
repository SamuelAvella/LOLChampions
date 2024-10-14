package com.turing.alan.cpifp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.turing.alan.cpifp.data.InMemoryChampionsRepository
import com.turing.alan.cpifp.databinding.FragmentChampionListBinding

class ChampionListFragment : Fragment() {

    private val repository: ChampionRepository = InMemoryChampionsRepository.getInstance()
    private lateinit var binding: FragmentChampionListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflar el layout usando ViewBinding
        binding = FragmentChampionListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar el adaptador
        val adapter = ChampionAdapter(::toItemDetail)
        val rv = binding.recyclerViewChampions
        rv.adapter = adapter

        // Asignar la lista de campeones
        (rv.adapter as ChampionAdapter).submitList(repository.getChampions())

        // Configurar el FAB si tienes alguna acción que quieras realizar
        binding.createTaskFab.setOnClickListener {
            // No sé si necesitas esta acción, pero podrías añadir algún botón de acción
            // val action = ChampionListFragmentDirections.actionChampionListFragmentToChampionCreateFragment()
            // findNavController().navigate(action)
        }
    }

    override fun onResume() {
        super.onResume()
        val rv = binding.recyclerViewChampions
        (rv.adapter as ChampionAdapter).submitList(repository.getChampions())
    }

    // Método para ir al detalle del campeón
    private fun toItemDetail(championId: String) {
        val action = ChampionListFragmentDirections.actionChampionListFragmentToChampionDetailFragment(championId)
        findNavController().navigate(action)
    }
}
}

