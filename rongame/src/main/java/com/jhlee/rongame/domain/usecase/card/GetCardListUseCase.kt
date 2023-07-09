package com.jhlee.rongame.domain.usecase.card

import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.domain.model.Card
import com.jhlee.rongame.domain.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCardListUseCase @Inject constructor(
    private val repository: CardRepository<Card>
) {

    operator fun invoke(): Flow<Resource<List<Card>>> = flow {
        try {
            emit(Resource.Loading<List<Card>>())
            emit(Resource.Success<List<Card>>(repository.getCardList()))
        } catch (e: IOException) {
            emit(Resource.Error<List<Card>>(e.localizedMessage as String))
        }
    }
}