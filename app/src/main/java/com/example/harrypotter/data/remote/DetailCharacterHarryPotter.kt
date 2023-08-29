package com.example.harrypotter.data.remote

data class DetailCharacterHarryPotter(
    val id: String,
    val name: String,
    val species: String,
    val gender: String,
    val house: String,
    val dateOfBirth: String,
    val wizard: Boolean,
    val ancestry: String,
    val eyeColour: String,
    val hairColour: String,
    val patronus: String,
    val hogwartsStudent: Boolean,
    val hogwartsStaff: Boolean,
    val actor:  String,
    val alive: Boolean,
    val image: String
)
