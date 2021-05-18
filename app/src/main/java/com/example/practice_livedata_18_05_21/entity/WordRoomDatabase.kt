package com.example.practice_livedata_18_05_21.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    @Volatile
    private var INSTANCE: WordRoomDatabase? = null
    private val NUMBER_OF_THREADS: Int = 4

    val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

    fun getDatabase(context: Context): WordRoomDatabase {
        if (INSTANCE == null) {
            synchronized(WordRoomDatabase::class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        WordRoomDatabase::class.java, "word_database"
                    ).build()
                }
            }
        }
        return INSTANCE!!
    }


}