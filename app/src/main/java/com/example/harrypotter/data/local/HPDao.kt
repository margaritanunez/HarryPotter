package com.example.harrypotter.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HPDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListCharacters(hpEntity: List<HPEntity>)

    @Query("select * from characters_table order by id ASC")
    fun getListCharacters(): LiveData<List<HPEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailCharacters (hpEntity: HPEntity)

    @Query("select * from characters_table where id= :id")
    fun getDetailCharacter(id: String): LiveData<HPEntity>


}