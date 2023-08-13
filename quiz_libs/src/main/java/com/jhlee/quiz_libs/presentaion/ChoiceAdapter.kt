package com.jhlee.quiz_libs.presentaion

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhlee.quiz_libs.R
import com.jhlee.quiz_libs.databinding.RvItemQuizChoiceBinding
import com.jhlee.quiz_libs.domain.model.Quiz

class ChoiceAdapter(var quiz: Quiz, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<ChoiceAdapter.ChoiceAdapterViewHolder>() {

    companion object {
        const val ITEM_STATUS_DEFAULT = 0
        const val ITEM_STATUS_CORRECT = 1
        const val ITEM_STATUS_FAULT = 2
    }

    private val itemStatusMap: HashMap<Int, Int> = HashMap<Int, Int>(quiz.choiceList.size).apply {
        repeat(quiz.choiceList.size) {
            this[it] = 0
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class ChoiceAdapterViewHolder(private val binding: RvItemQuizChoiceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(choice: String, position: Int, status: Int? = ITEM_STATUS_DEFAULT) {

            when (status) {
                ITEM_STATUS_FAULT -> {
                    binding.choiceItemText.isEnabled = false
                    binding.choiceItemText.setBackgroundResource(R.drawable.choice_item_selector)
                }

                ITEM_STATUS_CORRECT -> {
                    binding.choiceItemText.setBackgroundResource(R.drawable.choice_item_correct)
                }

                else -> {
                    binding.choiceItemText.isEnabled = true
                    binding.choiceItemText.setBackgroundResource(R.drawable.choice_item_selector)
                }
            }
            binding.choiceItemText.text = "${position.plus(1)}. $choice"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoiceAdapterViewHolder {
        val binding = RvItemQuizChoiceBinding.inflate(LayoutInflater.from(parent.context))
        val lp = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        binding.root.layoutParams = lp
        return ChoiceAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int = quiz.choiceList.size

    override fun onBindViewHolder(holder: ChoiceAdapterViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.onItemClick(position)
        }

        return holder.bind(quiz.choiceList[position], position, (itemStatusMap[position]))
    }

    fun updateStatus(index: Int, result: Boolean) {
        itemStatusMap[index] = if (result) ITEM_STATUS_CORRECT else ITEM_STATUS_FAULT
        notifyItemChanged(index)
    }

    fun refreshList(quiz: Quiz) {
        this.quiz = quiz
        itemStatusMap.clear()
        notifyDataSetChanged()
    }
}