package com.example.emptyproject.pokemon.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.emptyproject.databinding.FragmentPokemonDetailBinding

/*
 * Created by Sudhanshu Kumar on 17/09/24.
 */

class PokemonDetailFragment : Fragment() {

    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding get() = _binding

    private val args: PokemonDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("RishuTest", "arg: ${args.pokemon}")
        Log.d("RishuTest", "arg: ${args.id}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}