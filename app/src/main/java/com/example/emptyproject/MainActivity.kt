package com.example.emptyproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.emptyproject.databinding.ActivityMainBinding
import com.example.emptyproject.pokemon.ui.PokemonActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCommunity.setOnClickListener {
            startActivity(Intent(this, PokemonActivity::class.java))
        }
    }
}