package com.jhlee.rongame.domain.usecase.user

import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.domain.model.UserInfo
import com.jhlee.rongame.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertUserInfoUseCase @Inject constructor(
    private val repository: UserRepository<UserInfo>
) {

    operator fun invoke(): Flow<Resource<UserInfo>> = flow {
        try {
            emit(Resource.Loading<UserInfo>())
            val coin = repository.createCard()
            emit(Resource.Success<UserInfo>(coin))
        } catch (e: Exception) {
            emit(Resource.Error<UserInfo>(e.localizedMessage ?: "error"))
        }
    }
}