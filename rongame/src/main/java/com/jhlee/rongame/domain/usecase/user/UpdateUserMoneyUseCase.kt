package com.jhlee.rongame.domain.usecase.user

import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.domain.model.UserInfo
import com.jhlee.rongame.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateUserMoneyUseCase @Inject constructor(
    private val repository: UserRepository<UserInfo>
) {

    operator fun invoke(): Flow<Resource<UserInfo>> = flow {
        try {
            emit(Resource.Loading<UserInfo>())
            val userInfo = repository.getUserInfo()
            val userInfoTemp = userInfo.copy(money = userInfo.money.minus(1))
            val updatedUser = repository.updateUserInfo(userInfoTemp)
            emit(Resource.Success<UserInfo>(updatedUser))
        } catch (e: Exception) {
            emit(Resource.Error<UserInfo>(e.localizedMessage ?: "error"))
        }
    }
}