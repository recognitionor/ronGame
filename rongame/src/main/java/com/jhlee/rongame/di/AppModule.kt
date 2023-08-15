package com.jhlee.rongame.di

import android.content.Context
import com.google.firebase.storage.FirebaseStorage
import com.jhlee.rongame.data.local.AppDatabase
import com.jhlee.rongame.data.local.DatabaseBuilder
import com.jhlee.rongame.data.local.dao.DBAttendDao
import com.jhlee.rongame.data.local.dao.DBCardDao
import com.jhlee.rongame.data.local.dao.DBGameStageDao
import com.jhlee.rongame.data.local.dao.DBQuizDao
import com.jhlee.rongame.data.local.dao.DBUserInfoDao
import com.jhlee.rongame.data.remote.FBQuizRepositoryImpl
import com.jhlee.rongame.data.remote.FirebaseRepository
import com.jhlee.rongame.data.repository.DBAttendRepositoryImpl
import com.jhlee.rongame.data.repository.DBCardRepositoryImpl
import com.jhlee.rongame.data.repository.DBGameStageRepositoryImpl
import com.jhlee.rongame.data.repository.DBQuizRepositoryImpl
import com.jhlee.rongame.data.repository.DBUserRepositoryImpl
import com.jhlee.rongame.data.repository.LocalRepository
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
    @FirebaseRepository
    @Singleton
    @Provides
    fun provideFirebaseRepository(): QuizRepository {
        return FBQuizRepositoryImpl(FirebaseStorage.getInstance())
    }

    @Provides
    @Singleton
    fun provideLocalCardRepository(cardDao: DBCardDao): CardRepository<Card> {
        return DBCardRepositoryImpl(cardDao)
    }

    @Provides
    @Singleton
    fun provideLocalUserInfoRepository(dao: DBUserInfoDao): UserRepository<UserInfo> {
        return DBUserRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideLocalGameStageRepository(dao: DBGameStageDao): GameStageRepository {
        return DBGameStageRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideLocalAttendRepository(dao: DBAttendDao): AttendRepository {
        return DBAttendRepositoryImpl(dao)
    }

    @LocalRepository
    @Provides
    @Singleton
    fun provideLocalQuizRepository(dao: DBQuizDao): QuizRepository {
        return DBQuizRepositoryImpl(dao)
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