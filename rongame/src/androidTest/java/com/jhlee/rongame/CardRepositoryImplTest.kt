package com.jhlee.rongame

import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jhlee.rongame.data.local.AppDatabase
import com.jhlee.rongame.data.local.dao.DBCardDao
import com.jhlee.rongame.data.repository.CardRepositoryImpl
import com.jhlee.rongame.domain.model.Card
import com.jhlee.rongame.domain.model.Hero
import com.jhlee.rongame.domain.repository.CardRepository
import com.jhlee.rongame.domain.usecase.card.CreateCardUseCase
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CardRepositoryImplTest {

    private lateinit var db: AppDatabase
    private lateinit var dao: DBCardDao
    private lateinit var repository: CardRepository<Card>
    private lateinit var useCase: CreateCardUseCase

    @Before
    fun setup() {
        // 테스트용 In-Memory 데이터베이스 생성
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), AppDatabase::class.java
        ).build()

        // 테스트용 DAO 생성
        dao = db.cardDao()

        // 테스트할 Repository 생성
        repository = CardRepositoryImpl(dao)
        useCase = CreateCardUseCase(repository)
    }

    @After
    fun cleanup() {
        // 테스트 종료 후 데이터베이스 닫기
        db.close()
    }

    @Test
    fun testCreateCard() = runBlockingTest {
        // Arrange
        val hero = Hero(1, "", "", "", 1, 1, 1, 1)
        val card = Card(1, "", 1, 1, hero)

        // Act
        val result = repository.createCard(card)
        useCase()
        println(result)
        println("result")

    }

    @Test
    fun testGetCard() {
        // Arrange
        // TODO: 필요한 데이터를 데이터베이스에 삽입하여 테스트 환경을 구성하세요.

        // Act
//        val result = repository.getCard()

        // Assert
        // 예상한 결과와 실제 결과를 비교하여 테스트 검증
        // TODO: 예상한 결과와 비교하는 로직을 추가하세요.
    }

    // 테스트할 다른 메서드들에 대한 테스트도 추가 가능

}
