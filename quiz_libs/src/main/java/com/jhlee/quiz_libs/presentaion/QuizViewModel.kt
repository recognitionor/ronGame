package com.jhlee.quiz_libs.presentaion

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jhlee.quiz_libs.domain.model.Quiz

class QuizViewModel(private val quizList: ArrayList<Quiz>) : ViewModel() {

    var quizIndex: Int = 0

    val time = MutableLiveData<Long>(-1)

    val quiz = MutableLiveData<Quiz>(quizList[quizIndex])

    val resultState = MutableLiveData<Int>(QUIZ_RESULT_STATE_READY)

    val isValidTime = MutableLiveData<Boolean>(false)

    val selectStatus = MutableLiveData<HashMap<Int, Boolean>>(HashMap(1))

    val chance = MutableLiveData<Int>(quizList[0].chance)

    companion object {
        const val QUIZ_RESULT_STATE_READY = -1

        const val QUIZ_RESULT_STATE_SUCCESS = 1

        const val QUIZ_RESULT_STATE_FAIL = 0

        const val QUIZ_RESULT_STATE_DONE = 2
    }

    init {
//        startQuiz()
    }

    fun selectAnswer(index: Int) {
        val isResult = (index + 1) == (quiz.value?.answer ?: "")

        val tempMap = HashMap<Int, Boolean>(1).apply {
            this[index] = isResult
        }
        if (isResult) {
            selectStatus.postValue(tempMap)
            resultState.postValue(QUIZ_RESULT_STATE_SUCCESS)
        } else {
            val tempChance = chance.value?.minus(1)
            selectStatus.postValue(tempMap)
            chance.postValue(tempChance)
            if ((tempChance ?: 0) < 1) {
                resultState.postValue(QUIZ_RESULT_STATE_FAIL)
            }
        }
    }

    fun nextQuiz() {
        quizIndex++
        if (quizIndex < quizList.size) {
            val quiz = quizList[quizIndex]
            this.quiz.postValue(quiz)
            this.chance.postValue(quiz.chance)
            this.selectStatus.postValue(HashMap(1))
            this.isValidTime.postValue(false)
            this.time.postValue(-1)
            this.resultState.postValue(QUIZ_RESULT_STATE_READY)
        } else {
            this.resultState.postValue(QUIZ_RESULT_STATE_DONE)
        }
    }

    fun startQuiz() {
        val currentTime = System.currentTimeMillis()
        isValidTime.postValue(true)
        Thread {
            while (resultState.value == QUIZ_RESULT_STATE_READY) {
                val elapsedTime = System.currentTimeMillis() - currentTime
                if (elapsedTime > (quiz.value?.time ?: 0)) {
                    break
                }
                time.postValue(elapsedTime)
                Thread.sleep(50)
            }
            isValidTime.postValue(false)
            if (resultState.value == QUIZ_RESULT_STATE_READY) {
                resultState.postValue(QUIZ_RESULT_STATE_FAIL)
            }
        }.start()
    }
}