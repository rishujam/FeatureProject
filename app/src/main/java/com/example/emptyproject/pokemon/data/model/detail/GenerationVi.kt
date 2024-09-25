package com.example.emptyproject.pokemon.data.model.detail

import com.google.gson.annotations.SerializedName

data class GenerationVi(
    @SerializedName("omegaruby-alphasapphire")
    val omegaRubyAlphaSapphire: OmegarubyAlphasapphire,
    @SerializedName("x-y")
    val xY: XY
)