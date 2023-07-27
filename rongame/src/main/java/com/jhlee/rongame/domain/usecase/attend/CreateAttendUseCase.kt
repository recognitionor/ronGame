package com.jhlee.rongame.domain.usecase.attend

import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.domain.repository.AttendRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class CreateAttendUseCase @Inject constructor(private val repository: AttendRepository) {
    operator fun invoke(date: Long): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading<Unit>())
            emit(Resource.Success<Unit>(repository.createAttend(date)))
        } catch (e: IOException) {
            emit(Resource.Error<Unit>(e.localizedMessage as String))
        }
    }
}