package com.myk.feature.search.domain.usecase

import com.myk.feature.search.domain.model.PokemonDomainModel
import com.myk.feature.search.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonUseCase(private val repository: PokemonRepository) {
    /**
     * Assumes page count starts at 0
     */
    operator fun invoke(id: Int): Flow<PokemonDomainModel?> = repository.getPokemon(id)
}
