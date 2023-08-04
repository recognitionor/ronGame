package com.jhlee.quiz_libs

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhlee.quiz_libs.databinding.LayoutQuizBinding
import com.jhlee.quiz_libs.domain.model.Quiz
import com.jhlee.quiz_libs.presentaion.ChoiceAdapter
import com.jhlee.quiz_libs.presentaion.QuizViewModel

class QuizFragment(private val quizList: ArrayList<Quiz>) : Fragment() {

    private lateinit var binding: LayoutQuizBinding

    private lateinit var adapter: ChoiceAdapter

    lateinit var quizViewModel: QuizViewModel

    var selectList: HashMap<Quiz, Int> = hashMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutQuizBinding.inflate(layoutInflater)

        if (quizList.size < 1) {
            finishFragment()
            return
        }

        quizViewModel = QuizViewModel(quizList)

        quizViewModel.quiz.value?.let {
            adapter = ChoiceAdapter(it, object : ChoiceAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    if (quizViewModel.isValidTime.value == true) {
                        selectList[quizList[quizViewModel.quizIndex]] = position
                        quizViewModel.selectAnswer(position)
                    }
                }
            })
        }

        binding.quizChoiceRv.adapter = adapter
        binding.quizChoiceRv.layoutManager = LinearLayoutManager(requireContext())
        binding.remainChance.text = getString(
            R.string.quiz_remain_chance, quizViewModel.quiz.value?.chance ?: 0
        )
        initObserver()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    private fun initObserver() {
        quizViewModel.quiz.observe(this) {
            binding.quizQuestionTv.text = it.question
            binding.quizLevelStarView.setRatingStar(it.level)
            binding.quizLevelStarView.setRatingStar(it.level)
            binding.timeProgressbar.max = it.time.toInt()
            adapter.refreshList(it)
        }
        quizViewModel.chance.observe(this) {
            binding.remainChance.text = getString(R.string.quiz_remain_chance, it)
        }

        quizViewModel.selectStatus.observe(this) {
            it.entries.forEach { map ->
                adapter.updateStatus(map.key, map.value)
            }
        }

        quizViewModel.time.observe(this) {
            if (it < 0) {
                binding.root.visibility = View.GONE
            } else {
                binding.root.visibility = View.VISIBLE
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.timeProgressbar.setProgress(it.toInt(), true)
                } else {
                    binding.timeProgressbar.progress = it.toInt()
                }
                binding.timeTv.text = ((it / 1000).plus(1)).toString()
            }
        }

        quizViewModel.resultState.observe(this) {

            when (it) {
                QuizViewModel.QUIZ_RESULT_STATE_READY -> {
                    quizViewModel.startQuiz()
                }

                QuizViewModel.QUIZ_RESULT_STATE_SUCCESS -> {
                    AlertDialog.Builder(requireContext()).apply {
                        val isDone = quizViewModel.quizIndex + 1 >= quizList.size
                        val btnText = if (isDone) {
                            getString(R.string.quiz_done)
                        } else {
                            getString(R.string.quiz_next_stage)
                        }
                        setPositiveButton(btnText) { dialog, _ ->
                            if (isDone) {
                                finishFragment()
                            } else {
                                quizViewModel.nextQuiz()
                            }
                            dialog.dismiss()
                        }
                        setTitle(getString(R.string.quiz_correct))
                        create()
                    }.show()
                }

                QuizViewModel.QUIZ_RESULT_STATE_FAIL -> {
                    AlertDialog.Builder(requireContext()).apply {
                        val isDone = quizViewModel.quizIndex + 1 >= quizList.size
                        val btnText = if (isDone) {
                            getString(R.string.quiz_done)
                        } else {
                            getString(R.string.quiz_next_stage)
                        }
                        setPositiveButton(btnText) { dialog, _ ->
                            if (isDone) {
                                finishFragment()
                            } else {
                                quizViewModel.nextQuiz()
                            }
                            dialog.dismiss()
                        }
                        setTitle(getString(R.string.quiz_incorrect))
                        create()
                    }.show()
                }

                QuizViewModel.QUIZ_RESULT_STATE_DONE -> {
                    finishFragment()
                }
            }
        }
    }

    private fun finishFragment() {
        val intent = Intent()
        intent.putExtra(QuizActivity.QUIZ_ANSWER_EXTRA, selectList)
        requireActivity().setResult(QuizActivity.QUIZ_RESULT_CODE, intent)
        requireActivity().finish()
    }
}