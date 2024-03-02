package com.example.anmp_w1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.anmp_w1.databinding.FragmentGameBinding
import kotlin.random.Random
import kotlin.random.nextInt

class GameFragment : Fragment() {
    private lateinit var binding:FragmentGameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    var result = 0
    var points = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            val playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName
            binding.txtTurn.text = "$playerName's turn "
            this.result = setEquation()
        }

        binding.btnSubmit.setOnClickListener {
            var userAnswer:String = binding.txtAnswer.text.toString()
            if (userAnswer.toInt() == this.result) {
                this.points += 1
                this.result = setEquation()
            }
            else {
                //goes to resultFragment
                val action = GameFragmentDirections.actionGameFragmentToResultFragment(points)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    fun setEquation():Int {
        var randomNumber1 = Random.nextInt(1, 101) //101 because until is exclusive
        var randomNumber2 = Random.nextInt(1, 101)
        binding.txtNumber1.text = randomNumber1.toString()
        binding.txtNumber2.text = randomNumber2.toString()

        var result = randomNumber1 + randomNumber2
        return result
    }
}