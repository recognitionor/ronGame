package com.jhlee.rongame.di

import android.content.Context
import com.jhlee.rongame.data.local.AppDatabase
import com.jhlee.rongame.data.local.DatabaseBuilder
import com.jhlee.rongame.data.local.dao.DBAttendDao
import com.jhlee.rongame.data.local.dao.DBCardDao
import com.jhlee.rongame.data.local.dao.DBGameStageDao
import com.jhlee.rongame.data.local.dao.DBQuizDao
import com.jhlee.rongame.data.local.dao.DBUserInfoDao
import com.jhlee.rongame.data.local.entity.DBAttend
import com.jhlee.rongame.data.repository.AttendRepositoryImpl
import com.jhlee.rongame.data.repository.CardRepositoryImpl
import com.jhlee.rongame.data.repository.GameStageRepositoryImpl
import com.jhlee.rongame.data.repository.QuizRepositoryImpl
import com.jhlee.rongame.data.repository.UserRepositoryImpl
import com.jhlee.rongame.domain.model.Card
import com.jhlee.rongame.domain.model.UserInfo
import com.jhlee.rongame.domain.repository.AttendRepository
import com.jhlee.rongame.domain.repository.CardRepository
import com.jhlee.rongame.domain.repository.GameStageRepository
import com.jhlee.rongame.domain.repository.QuizRepository
import com.jhlee.rongame.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideLocalCardRepository(cardDao: DBCardDao): CardRepository<Card> {
        return CardRepositoryImpl(cardDao)
    }

    @Provides
    @Singleton
    fun provideLocalUserInfoRepository(dao: DBUserInfoDao): UserRepository<UserInfo> {
        return UserRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideLocalGameStageRepository(dao: DBGameStageDao): GameStageRepository {
        return GameStageRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideLocalAttendRepository(dao: DBAttendDao): AttendRepository {
        return AttendRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideLocalQuizRepository(dao: DBQuizDao): QuizRepository {
        return QuizRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return DatabaseBuilder.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideDBGameStageDao(database: AppDatabase): DBGameStageDao {
        return database.gameStageDao()
    }

    @Provides
    @Singleton
    fun provideDBUserDao(database: AppDatabase): DBUserInfoDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideDBCardDao(database: AppDatabase): DBCardDao {
        return database.cardDao()
    }

    @Provides
    @Singleton
    fun provideDBAttendDao(database: AppDatabase): DBAttendDao {
        return database.attendDao()
    }

    @Provides
    @Singleton
    fun provideDBQuizDao(database: AppDatabase): DBQuizDao {
        return database.quizDao()
    }
}