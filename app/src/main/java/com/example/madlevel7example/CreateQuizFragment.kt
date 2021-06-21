package com.example.madlevel7example

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.madlevel7example.databinding.FragmentCreateQuizBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CreateQuizFragment : Fragment() {

    private var _binding: FragmentCreateQuizBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: QuizViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btCreateQuizConfirmAnswer.setOnClickListener {
            viewModel.createQuiz(
                binding.etCreateQuizQuestion.text.toString(),
                binding.etCreateQuizAnswer.text.toString()
            )
        }

        observeQuizCreation()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeQuizCreation() {
        viewModel.createSuccess.observe(viewLifecycleOwner, {
            Toast.makeText(activity, R.string.successfully_created_quiz, Toast.LENGTH_LONG).show()
            findNavController().popBackStack()
        })

        viewModel.errorText.observe(viewLifecycleOwner, {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
    }
}