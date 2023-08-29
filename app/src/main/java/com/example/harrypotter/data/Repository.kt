package com.example.harrypotter.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.harrypotter.data.local.DetailCharacterEntity
import com.example.harrypotter.data.local.HPDao
import com.example.harrypotter.data.local.HPEntity
import com.example.harrypotter.data.remote.CharactersHarryPotter
import com.example.harrypotter.data.remote.DetailCharacterHarryPotter
import com.example.harrypotter.data.remote.HarryPotterApi

class Repository(private val harryPotterApi: HarryPotterApi, private val hpDao: HPDao) {

    fun getListCharacters(): LiveData<List<HPEntity>> = hpDao.getListCharacters()
    fun getDetailCharacter(id: String): LiveData<DetailCharacterEntity> = hpDao.getDetailCharacter(id)

    suspend fun chargeCharacters() {
        try {
            val response = harryPotterApi.getData()
            if (response.isSuccessful) {
                val resp = response.body()
                resp?.let { characters ->
                    val hpCharactersEntity = characters.map { it.transformar() }
                    hpDao.insertListCharacters(hpCharactersEntity)
                }


            } else {
                Log.e("repository", response.errorBody().toString())

            }

        } catch (exception: Exception){
            Log.e("catch", "")
        }
    }

    suspend fun chargeDetailCharacter(id:String){
        Log.d("repository", id)
        try {
            val responseDetail = harryPotterApi.getDetailData(id)
            Log.d("repository", responseDetail.isSuccessful.toString())
            if(responseDetail.isSuccessful){
                val respDetail = responseDetail.body()
                respDetail?.let{ charactersDetail->
                    val detailsCharacterEntity =  charactersDetail.transformarDetalle()
                    hpDao.insertDetailCharacters(detailsCharacterEntity)
                }
            }else {
                Log.e("repository", responseDetail.errorBody().toString())
            }

        } catch (exception: Exception){
            Log.e("catch", "")
        }
    }
}

fun CharactersHarryPotter.transformar(): HPEntity = HPEntity(
    this.id,
    this.name,
    this.species,
    this.gender,
    this.house,
    this.dateOfBirth,
    this.wizard,
    this.ancestry,
    this.eyeColour,
    this.hairColour,
    this.patronus,
    this.hogwartsStudent,
    this.hogwartsStaff,
    this.actor,
    this.alive,
    this.image
)

fun DetailCharacterHarryPotter.transformarDetalle(): DetailCharacterEntity = DetailCharacterEntity(
    this.id,
    this.name,
    this.species,
    this.gender,
    this.house,
    this.dateOfBirth,
    this.wizard,
    this.ancestry,
    this.eyeColour,
    this.hairColour,
    this.patronus,
    this.hogwartsStudent,
    this.hogwartsStaff,
    this.actor,
    this.alive,
    this.image
)

/*
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
    val actor: String,
    val alive: Boolean,
    val image: String
 */