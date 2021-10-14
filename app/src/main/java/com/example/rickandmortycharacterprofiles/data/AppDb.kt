package com.example.rickandmortycharacterprofiles.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickandmortycharacterprofiles.data.CharacterEntity

@Database(entities = [Character::class], version = 1, exportSchema = false)
abstract class AppDb : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {
        @Volatile private var instance: AppDb? = null

        fun getDatabase(context: Context): AppDb =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDb::class.java, "characters")
                .fallbackToDestructiveMigration()
                .build()
    }

}