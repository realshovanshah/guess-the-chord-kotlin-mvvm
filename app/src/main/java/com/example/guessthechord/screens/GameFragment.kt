
package com.example.guessthechord.screens

//import com.example.guessthechord.GameFragmentDirections
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.example.guessthechord.navigation.R
import com.example.guessthechord.navigation.databinding.FragmentGameBinding
import com.example.guessthechord.viewmodels.GameViewModel

class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel
    private val TAG = "GameFragment"
    // The current question
    lateinit var question: Question

    lateinit var answers: MutableList<String>

    // The current score
    private var score = 0

    private lateinit var questions: MutableList<Question>

    private lateinit var binding: FragmentGameBinding

    data class Question(
            val text: String,
            val image: Int,
            val answers: List<String>)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_game, container, false)

        Log.d(TAG, "onCreateView: Called ViewModelProviders.of")
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        binding.game = this

        resetList()
        nextWord()

        binding.skipButton.setOnClickListener{onSkip()}

        // Set the onClickListener for the submitButton
        binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        {
            onCorrect()
        }

        updateWordText()
        updateScoreText()

        return binding.root
    }

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        questions = mutableListOf(
                Question(text = "What chord is this?",
                        image = R.drawable.a_chord,
                        answers = listOf("A major", "A minor", "D major", "F chord")),
                Question(text = "What chord is this?",
                        image = R.drawable.c_chord,
                        answers = listOf("C major", "C minor", "A minor", "E major")),
                Question(text = "What chord is this?",
                        image = R.drawable.d_chord,
                        answers = listOf("D major", "F chord", "D minor", "C major")),
                Question(text = "What chord is this?",
                        image = R.drawable.e_chord,
                        answers = listOf("E major", "C major", "E minor", "D major")),
                Question(text = "What chord is this?",
                        image = R.drawable.g_chord,
                        answers = listOf("G major", "A minor", "C major", "F chord")),
                Question(text = "What chord is this?",
                        image = R.drawable.a_minor_chord,
                        answers = listOf("A minor", "A major", "D minor", "G major")),
                Question(text = "What chord is this?",
                        image = R.drawable.d_minor_chord,
                        answers = listOf("D minor", "D major", "C major", "A minor")),
                Question(text = "What chord is this?",
                        image = R.drawable.e_minor_chord,
                        answers = listOf("E minor", "C major", "D major", "E major"))
        )
        questions.shuffle()
    }

    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        val action = GameFragmentDirections.actionGameFragmentToGameWonFragment(score)
        NavHostFragment.findNavController(this).navigate(action)
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        //Select and remove a word from the list
        if (questions.isEmpty()) {
            gameFinished()
        } else {
            question = questions.removeAt(0)
        }
        binding.invalidateAll()
        updateWordText()
        updateScoreText()
    }

    /** Methods for buttons presses **/

    private fun onSkip() {
        score--
        nextWord()
    }

    private fun onCorrect() {
        val checkedId = binding.questionRadioGroup.checkedRadioButtonId
        // Do nothing if nothing is checked (id == -1)
        if (-1 != checkedId) {
            var answerIndex = 0
            when (checkedId) {
                R.id.secondAnswerRadioButton -> answerIndex = 1
                R.id.thirdAnswerRadioButton -> answerIndex = 2
                R.id.fourthAnswerRadioButton -> answerIndex = 3
            }
            if (answers[answerIndex] == question.answers[0]) {
                score++
            }else{
                score--
            }
            nextWord()
        }
    }

    /** Methods for updating the UI **/

    private fun updateWordText() {
        answers = question.answers.toMutableList()
        answers.shuffle()
        binding.questionText.text = question.text

    }

    private fun updateScoreText() {
        binding.scoreText.text = score.toString()
    }

}
