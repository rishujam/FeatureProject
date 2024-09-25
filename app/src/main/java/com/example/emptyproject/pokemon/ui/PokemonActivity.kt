package com.example.emptyproject.pokemon.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.emptyproject.databinding.ActivityPokemonBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}