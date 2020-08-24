
package com.example.guessthechord.screens

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.guessthechord.navigation.R
import com.example.guessthechord.navigation.databinding.FragmentGameFinishedBinding


class GameFinishedFragment : Fragment() {

    private val args: GameFinishedFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameFinishedBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_game_finished, container, false)

        binding.nextMatchButton.setOnClickListener{view ->
            view.findNavController().navigate(R.id.action_gameWonFragment_to_gameFragment)
        }

        binding.scoreView.text = args.score.toString()

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu, menu)
        if(getShareIntent().resolveActivity(requireActivity().packageManager)==null){
            menu.findItem(R.id.share).isVisible = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getShareIntent(): Intent {
//        val shareIntent = Intent(Intent.ACTION_SEND)
//        shareIntent.setType("text/plain")
//                .putExtra(Intent.EXTRA_TEXT, getString(R.string.share_success_text, args.numCorrect, args.numQuestions))
        return ShareCompat.IntentBuilder.from(activity)
                .setText(getString(R.string.share_success_text, args.score))
                .setType("text/plain").intent
    }

    private fun shareSuccess(){
        startActivity(getShareIntent())
    }

}
