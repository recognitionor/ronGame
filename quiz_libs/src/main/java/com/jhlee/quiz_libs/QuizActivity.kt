package com.jhlee.quiz_libs

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jhlee.quiz_libs.domain.model.Quiz

class QuizActivity : AppCompatActivity() {

    companion object {
        const val QUIZ_LIST_EXTRA = "QUIZ_LIST_EXTRA"
        const val QUIZ_ANSWER_EXTRA = "QUIZ_ANSWER_EXTRA"
        const val QUIZ_RESULT_CODE = 170627
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        val quizList = getQuizList()
        if (quizList.size < 1) {
            finish()
        }
        // 프래그먼트 인스턴스 생성
        val fragment = QuizFragment(quizList)

        // 프래그먼트를 띄우기 위해 FragmentManager를 사용합니다.
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        // R.id.fragment_layout는 프래그먼트를 띄울 레이아웃 컨테이너의 ID입니다.
        // 프래그먼트를 추가하고자 하는 경우 add() 메서드를 사용합니다.
        transaction.add(R.id.fragment_layout, fragment)

        // 트랜잭션을 커밋하여 프래그먼트를 실제로 화면에 추가합니다.
        transaction.commit()

    }

    private fun getQuizList(): ArrayList<Quiz> {
        val quizList = arrayListOf<Quiz>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val tempList = intent.getParcelableArrayListExtra(QUIZ_LIST_EXTRA, Quiz::class.java)
            tempList?.let { quizList.addAll(it) }
        } else {
            val tempList = intent.getParcelableArrayExtra(QUIZ_LIST_EXTRA)
            if (tempList != null) {
                // Parcelable 배열을 ArrayList<Quiz>로 변환
                for (parcelable in tempList) {
                    if (parcelable is Quiz) {
                        quizList.add(parcelable)
                    }
                }
            }
        }
        return quizList
    }
}