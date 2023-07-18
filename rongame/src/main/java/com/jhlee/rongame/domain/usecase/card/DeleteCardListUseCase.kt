package com.jhlee.rongame.domain.usecase.card

import android.util.Log
import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.domain.model.Card
import com.jhlee.rongame.domain.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class DeleteCardListUseCase @Inject constructor(
    private val repository: CardRepository<Card>
) {

    operator fun invoke(idList: List<Int>): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading<Unit>())
            emit(Resource.Success<Unit>(repository.deleteCardList(idList)))
        } catch (e: IOException) {
            emit(Resource.Error<Unit>(e.localizedMessage as String))
        }
    }
}