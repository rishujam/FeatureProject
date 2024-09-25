package com.example.emptyproject.pokemon.data.model.detail

import com.google.gson.annotations.SerializedName

data class GenerationIii(
    val emerald: Emerald,
    @SerializedName("firered-leafgreen")
    val fireRedLeafGreen: FireredLeafgreen,
    val rubySapphire: RubySapphire
)