package project.adapter

import ir.sinapp.classroom.databinding.AdapterAssignmentBinding
import ir.sinapp.classroom.databinding.AdapterExamBinding
import ir.sinapp.classroom.databinding.AdapterPostBinding
import ir.sinapp.classroom.databinding.AdapterQuestionBinding
import project.adapter.PagedBaseAdapter
import project.data.model.Assignment
import project.data.model.Exam
import project.data.model.Post
import project.data.model.Question


class PostViewHolder(binding: AdapterPostBinding) : PagedBaseAdapter.BaseViewHolder<Post, AdapterPostBinding> (binding) {

    override fun onBind(item: Post, position: Int) {

    }

}

class AssignmentViewHolder(binding: AdapterAssignmentBinding) : PagedBaseAdapter.BaseViewHolder<Assignment, AdapterAssignmentBinding> (binding) {

    override fun onBind(item: Assignment, position: Int) {

    }


}

class QuestionViewHolder(binding: AdapterQuestionBinding) : PagedBaseAdapter.BaseViewHolder<Question, AdapterQuestionBinding> (binding) {

    override fun onBind(item: Question, position: Int) {
    }


}

class ExamViewHolder(binding: AdapterExamBinding) : PagedBaseAdapter.BaseViewHolder<Exam, AdapterExamBinding> (binding) {

    override fun onBind(item: Exam, position: Int) {
    }


}

class MaterialViewHolder(binding: AdapterPostBinding) : PagedBaseAdapter.BaseViewHolder<Post, AdapterPostBinding> (binding) {

    override fun onBind(item: Post, position: Int) {

    }


}