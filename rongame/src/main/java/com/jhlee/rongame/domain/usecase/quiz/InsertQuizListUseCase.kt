package com.jhlee.rongame.domain.usecase.quiz

import com.jhlee.quiz_libs.domain.model.Quiz
import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertQuizListUseCase @Inject constructor(private val quizRepository: QuizRepository) {
    operator fun invoke(): Flow<Resource<List<Quiz>>> = flow {
        emit(Resource.Loading<List<Quiz>>())
        try {
            quizRepository.insertQuiz(quizList)
            val result = quizRepository.getQuizList()
            emit(Resource.Success<List<Quiz>>(result))
        } catch (e: Exception) {
            emit(Resource.Error<List<Quiz>>(e.message.toString()))
        }
    }

    // quizList

    private val quizList: List<Quiz> = arrayListOf(
        Quiz(
            id = 1,
            category = "영어",
            level = 1,
            imageUrl = "",
            answer = 1,
            question = "강아지는 영어로 뭐니?",
            choiceList = listOf("dog(도그)", "cat(캣)", "horse(호스)", "duck(덕)", "bird(버드)"),
            time = 15,
            chance = 1,
            reward = 100
        ),

        Quiz(
            id = 2,
            category = "영어",
            level = 2,
            imageUrl = "",
            answer = 2,
            question = "고양이는 영어로 뭐니?",
            choiceList = listOf("dog(도그)", "cat(캣)", "horse(호스)", "duck(덕)", "bird(버드)"),
            time = 15,
            chance = 1,
            reward = 100
        ),

        Quiz(
            id = 3,
            category = "영어",
            level = 3,
            imageUrl = "",
            answer = 3,
            question = "말은 영어로 뭐니?",
            choiceList = listOf("dog(도그)", "cat(캣)", "horse(호스)", "duck(덕)", "bird(버드)"),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 4,
            category = "영어",
            level = 4,
            imageUrl = "",
            answer = 4,
            question = "오리는 영어로 뭐니?",
            choiceList = listOf("dog(도그)", "cat(캣)", "horse(호스)", "duck(덕)", "bird(버드)"),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 5,
            category = "영어",
            level = 5,
            imageUrl = "",
            answer = 5,
            question = "새는 영어로 뭐니?",
            choiceList = listOf("dog(도그)", "cat(캣)", "horse(호스)", "duck(덕)", "bird(버드)"),
            time = 15,
            chance = 1,
            reward = 100
        ),

        Quiz(
            id = 6,
            category = "영어",
            level = 1,
            imageUrl = "",
            answer = 2,
            question = "손은 영어로 뭐니?",
            choiceList = listOf("foot(풋)", "hand(핸드)", "head(헤드)", "leg(레그)", "face(페이스)"),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 7,
            category = "영어",
            level = 2,
            imageUrl = "",
            answer = 2,
            question = "발은 영어로 뭐니?",
            choiceList = listOf("foot(풋)", "hand(핸드)", "head(헤드)", "leg(레그)", "face(페이스)"),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 8,
            category = "영어",
            level = 3,
            imageUrl = "",
            answer = 3,
            question = "머리는 영어로 뭐니?",
            choiceList = listOf("foot(풋)", "hand(핸드)", "head(헤드)", "leg(레그)", "face(페이스)"),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 9,
            category = "영어",
            level = 4,
            imageUrl = "",
            answer = 4,
            question = "다리는 영어로 뭐니?",
            choiceList = listOf("foot(풋)", "hand(핸드)", "head(헤드)", "leg(레그)", "face(페이스)"),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 10,
            category = "영어",
            level = 5,
            imageUrl = "",
            answer = 5,
            question = "얼굴은 영어로 뭐니?",
            choiceList = listOf("foot(풋)", "hand(핸드)", "head(헤드)", "leg(레그)", "face(페이스)"),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 11,
            category = "영어",
            level = 1,
            imageUrl = "",
            answer = 1,
            question = "하늘은 영어로 뭐니?",
            choiceList = listOf("sky(스카이)", "cloud(클라우드)", "sun(썬)", "moon(문)", "star(스타)"),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 12,
            category = "영어",
            level = 2,
            imageUrl = "",
            answer = 2,
            question = "구름은 영어로 뭐니?",
            choiceList = listOf("sky(스카이)", "cloud(클라우드)", "sun(썬)", "moon(문)", "star(스타)"),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 13,
            category = "영어",
            level = 3,
            imageUrl = "",
            answer = 3,
            question = "태양은 영어로 뭐니?",
            choiceList = listOf("sky(스카이)", "cloud(클라우드)", "sun(썬)", "moon(문)", "star(스타)"),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 14,
            category = "영어",
            level = 4,
            imageUrl = "",
            answer = 4,
            question = "달은 영어로 뭐니?",
            choiceList = listOf("sky(스카이)", "cloud(클라우드)", "sun(썬)", "moon(문)", "star(스타)"),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 15,
            category = "영어",
            level = 5,
            imageUrl = "",
            answer = 5,
            question = "별은 영어로 뭐니?",
            choiceList = listOf("sky(스카이)", "cloud(클라우드)", "sun(썬)", "moon(문)", "star(스타)"),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 16,
            category = "영어",
            level = 2,
            imageUrl = "",
            answer = 1,
            question = "나무는 영어로 뭐니?",
            choiceList = listOf("tree(트리)", "flower(플라워)", "grass(그래스)", "leaf(리프)", "fruit(프루트)"),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 17,
            category = "영어",
            level = 2,
            imageUrl = "",
            answer = 2,
            question = "꽃은 영어로 뭐니?",
            choiceList = listOf("tree(트리)", "flower(플라워)", "grass(그래스)", "leaf(리프)", "fruit(프루트)"),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 18,
            category = "영어",
            level = 2,
            imageUrl = "",
            answer = 3,
            question = "풀은 영어로 뭐니?",
            choiceList = listOf("tree(트리)", "flower(플라워)", "grass(그래스)", "leaf(리프)", "fruit(프루트)"),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 19,
            category = "영어",
            level = 2,
            imageUrl = "",
            answer = 4,
            question = "잎은 영어로 뭐니?",
            choiceList = listOf("tree(트리)", "flower(플라워)", "grass(그래스)", "leaf(리프)", "fruit(프루트)"),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 20,
            category = "영어",
            level = 2,
            imageUrl = "",
            answer = 5,
            question = "과일은 영어로 뭐니?",
            choiceList = listOf("tree(트리)", "flower(플라워)", "grass(그래스)", "leaf(리프)", "fruit(프루트)"),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 21,
            category = "영어",
            level = 3,
            imageUrl = "",
            answer = 1,
            question = "책은 영어로 뭐니?",
            choiceList = listOf(
                "book(북)", "magazine(매거진)", "newspaper(신문)", "journal(저널)", "magazine(잡지)"
            ),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 22,
            category = "영어",
            level = 3,
            imageUrl = "",
            answer = 2,
            question = "엄마는 영어로 뭐니?",
            choiceList = listOf(
                "father(파더)", "mather(마더)", "brother(브라더)", "sister(시스터)", "friend(프렌드)"
            ),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 23,
            category = "영어",
            level = 3,
            imageUrl = "",
            answer = 1,
            question = "아빠는 영어로 뭐니?",
            choiceList = listOf(
                "father(파더)", "mather(마더)", "brother(브라더)", "sister(시스터)", "friend(프렌드)"
            ),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 24,
            category = "영어",
            level = 3,
            imageUrl = "",
            answer = 5,
            question = "친구는 영어로 뭐니?",
            choiceList = listOf(
                "father(파더)", "mather(마더)", "brother(브라더)", "sister(시스터)", "friend(프렌드)"
            ),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 25,
            category = "영어",
            level = 3,
            imageUrl = "",
            answer = 3,
            question = "남자 형제(형 또는 남동생)는 영어로 뭐니?",
            choiceList = listOf(
                "father(파더)", "mather(마더)", "brother(브라더)", "sister(시스터)", "friend(프렌드)"
            ),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 26,
            category = "과학",
            level = 2,
            imageUrl = "",
            answer = 1,
            question = "다음중 가장 가벼운 것은?",
            choiceList = listOf(
                "공기", "물", "깃털", "풍선", "바위"
            ),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 27,
            category = "수학",
            level = 2,
            imageUrl = "",
            answer = 4,
            question = "토끼가 3칸씩 점프를 하는 길이 12칸짜리 길을 건너려고 합니다. 몇 번의 점프가 필요할까요?",
            choiceList = listOf(
                "1", "2", "3", "4", "5"
            ),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 28,
            category = "수학",
            level = 2,
            imageUrl = "",
            answer = 4,
            question = "두 개의 주사위를 던집니다. 각 주사위에는 1부터 6까지의 눈금이 있습니다. 이 두 주사위를 던져서 합할 더할때 , 나올수 있는 가장 큰수는",
            choiceList = listOf(
                "9", "10", "11", "12", "13"
            ),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 29,
            category = "역사",
            level = 1,
            imageUrl = "",
            answer = 1,
            question = "한글을 만든 사람의 이름은?",
            choiceList = listOf(
                "세종대왕", "구종대왕", "별종대왕", "고종대왕", "로종대왕"
            ),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 30,
            category = "역사",
            level = 1,
            imageUrl = "",
            answer = 3,
            question = "해시계를 만든 사람의 이름은?",
            choiceList = listOf(
                "오영실", "오영란", "장영실", "장국영", "스티브잡스"
            ),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 31,
            category = "상식",
            level = 1,
            imageUrl = "",
            answer = 4,
            question = "지금 땅이 흔들거리고 건물이 흔들거립니다. 무슨일이 일어난것일까요?",
            choiceList = listOf(
                "바람이 분다.", "비가 내림", "눈이 오고 있음", "지진", "너무 더움"
            ),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 32,
            category = "상식",
            level = 1,
            imageUrl = "",
            answer = 1,
            question = "지금 땅이 흔들거리고 건물이 흔들거립니다. 무슨일이 일어난것일까요?",
            choiceList = listOf(
                "바람이 분다.", "비가 내림", "눈이 오고 있음", "지진", "너무 더움"
            ),
            time = 15,
            chance = 1,
            reward = 100
        ), Quiz(
            id = 33,
            category = "영어",
            level = 3,
            imageUrl = "",
            answer = 4,
            question = "여자 형제(언니 또는 여동생)는 영어로 뭐니?",
            choiceList = listOf(
                "father(파더)", "mather(마더)", "brother(브라더)", "sister(시스터)", "friend(프렌드)"
            ),
            time = 15,
            chance = 1,
            reward = 100
        )


    )
}