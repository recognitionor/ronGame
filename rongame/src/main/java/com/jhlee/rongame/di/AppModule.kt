package com.jhlee.rongame.di

import android.content.Context
import com.jhlee.rongame.data.local.AppDatabase
import com.jhlee.rongame.data.local.DatabaseBuilder
import com.jhlee.rongame.data.local.dao.DBCardDao
import com.jhlee.rongame.data.local.dao.DBHeroDao
import com.jhlee.rongame.data.local.dao.DBUserInfoDao
import com.jhlee.rongame.data.repository.CardRepositoryImpl
import com.jhlee.rongame.data.repository.HeroRepositoryImpl
import com.jhlee.rongame.data.repository.UserRepositoryImpl
import com.jhlee.rongame.domain.model.Card
import com.jhlee.rongame.domain.model.Hero
import com.jhlee.rongame.domain.model.UserInfo
import com.jhlee.rongame.domain.repository.CardRepository
import com.jhlee.rongame.domain.repository.HeroRepository
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
    fun provideLocalHeroRepository(dao: DBHeroDao): HeroRepository<Hero> {
        return HeroRepositoryImpl(dao)
    }


    @Provides
    @Singleton
    fun provideLocalCardRepository(cardDao: DBCardDao, heroDao: DBHeroDao): CardRepository<Card> {
        return CardRepositoryImpl(cardDao, heroDao)
    }

    @Provides
    @Singleton
    fun provideLocalUserInfoRepository(dao: DBUserInfoDao): UserRepository<UserInfo> {
        return UserRepositoryImpl(dao)
    }


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return DatabaseBuilder.getInstance(context)
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
    fun provideDBHeroDao(database: AppDatabase): DBHeroDao {
        return database.heroDao()
    }
}