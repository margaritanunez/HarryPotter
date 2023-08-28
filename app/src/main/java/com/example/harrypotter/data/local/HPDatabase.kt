package com.example.harrypotter.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities= [HPEntity::class], version = 1)
abstract class HPDatabase: RoomDatabase() {
    abstract fun getCharactersDao() : HPDao

    companion object{

        @Volatile

        private var INSTANCE: HPDatabase? = null

        fun getDatabase(context: Context): HPDatabase{
            val tempInstance = INSTANCE
            if (tempInstance !=null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HPDatabase::class.java,
                    "characters_database"
                ).build()

                INSTANCE = instance
                return instance

            }
        }
    }
}
}