package com.jhlee.rongame.data.remote

import android.util.Log
import com.google.firebase.storage.FirebaseStorage
import com.jhlee.quiz_libs.domain.model.Quiz
import com.jhlee.rongame.domain.const.QuizConst.Companion.QUIZ_FB_FILE_EXTENSION
import com.jhlee.rongame.domain.const.QuizConst.Companion.QUIZ_FB_FILE_NAME
import com.jhlee.rongame.domain.repository.QuizRepository
import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReaderBuilder
import java.io.File
import javax.inject.Inject
import javax.inject.Qualifier
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FirebaseRepository

class FBQuizRepositoryImpl @Inject constructor(private val firebaseStorage: FirebaseStorage) :
    QuizRepository {
    // Firebase 리포지터리를 위한 Qualifier

    override suspend fun insertQuiz(list: List<Quiz>) {}

    override suspend fun updateQuiz(quiz: Quiz) {}

    override suspend fun getQuizList(limit: Int): List<Quiz> {
        return emptyList()
    }


    override suspend fun getQuizListAll(): List<Quiz> {
        return suspendCoroutine { continuation ->
            val storageReference = firebaseStorage.reference
            val fileReference = storageReference.child("$QUIZ_FB_FILE_NAME.$QUIZ_FB_FILE_EXTENSION")

            val localFile = File.createTempFile(QUIZ_FB_FILE_NAME, QUIZ_FB_FILE_EXTENSION)
            fileReference.getFile(localFile).addOnSuccessListener {
                val csvReader = CSVReaderBuilder(localFile.reader()).withCSVParser(
                    CSVParserBuilder().withSeparator(',').build()
                ).build()

                val csvRows = csvReader.readAll()
                csvRows.removeAt(0)

                val quizList = mutableListOf<Quiz>()

                for (row in csvRows) {
                    val id = row[0].toInt()
                    val category = row[1]
                    val level = row[2].toInt()
                    val imageUrl = row[3]
                    val answer = row[4].toInt()
                    val question = row[5]
                    val choiceList = row[6].split("|")
                    val time = row[7].toLong()
                    val chance = row[8].toInt()
                    val reward = row[9].toInt()
                    val description = row[10]
                    Quiz(
                        id = id,
                        category = category,
                        level = level,
                        imageUrl = imageUrl,
                        answer = answer,
                        question = question,
                        choiceList = choiceList,
                        time = time,
                        chance = chance,
                        reward = reward,
                        description = description,
                    ).also {
                        quizList.add(it)
                    }
                }

                continuation.resume(quizList)
            }.addOnFailureListener {
                // 다운로드 실패 시
                continuation.resume(emptyList())
            }
        }
    }
}