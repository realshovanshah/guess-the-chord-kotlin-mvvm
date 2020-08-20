package com.example.guessthechord

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.guessthechord.navigation.R
import com.example.guessthechord.navigation.databinding.FragmentTitleBinding

/**
 * A simple [Fragment] subclass.
 */
class TitleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_title, container, false
        )

        binding .playButton.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_gameFragment)
        )

//        binding.playButton.setOnClickListener{view: View ->
//     //   1.   Navigation.findNavController(view).navigate(R.id.action_titleFragment_to_gameFragment)
//        2.   view.findNavController().navigate(R.id.action_titleFragment_to_gameFragment)
//        }

        return binding.root
    }

}