package com.myk.feature.search.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.myk.feature.search.R
import com.myk.feature.search.databinding.PokemonItemBinding
import com.myk.feature.search.databinding.SearchFragmentBinding
import com.myk.feature.search.domain.model.PokemonDomainModel
import com.myk.library.base.presentation.BaseAdapter
import com.myk.library.base.presentation.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * This screen allows a user to search for Pokemon!
 */
class SearchFragment : Fragment(R.layout.search_fragment) {

    private val viewModel: SearchViewModel by viewModel()
    private val binding by viewBinding(SearchFragmentBinding::bind)
    private val adapter = PokemonAdapter(listOf())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycleView.adapter = adapter

        viewModel.pokemon.observe(
            viewLifecycleOwner,
            {
                adapter.setItems(it)
            }
        )
    }

    class PokemonAdapter(
        items: List<PokemonDomainModel>
    ) : BaseAdapter<PokemonDomainModel, PokemonItemBinding, PokemonViewHolder>(items) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PokemonViewHolder(
            PokemonItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class PokemonViewHolder(
        binding: PokemonItemBinding
    ) : BaseAdapter.ViewHolder<PokemonDomainModel, PokemonItemBinding>(binding) {
        override fun bind(item: PokemonDomainModel?) {
            binding.imageView.load(item?.imageUrl)
        }
    }
}
