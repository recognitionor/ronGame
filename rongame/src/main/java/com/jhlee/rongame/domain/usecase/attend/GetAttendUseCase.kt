package com.jhlee.rongame.domain.usecase.attend

import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.domain.repository.AttendRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetAttendUseCase @Inject constructor(private val repository: AttendRepository) {
    operator fun invoke(): Flow<Resource<List<String>>> = flow {
        try {
            emit(Resource.Loading<List<String>>())
            emit(Resource.Success<List<String>>(repository.getAttendList()))
        } catch (e: IOException) {
            emit(Resource.Error<List<String>>(e.localizedMessage as String))
        }
    }
}