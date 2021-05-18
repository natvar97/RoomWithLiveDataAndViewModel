package com.example.practice_livedata_18_05_21.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.practice_livedata_18_05_21.entity.Word
import com.example.practice_livedata_18_05_21.entity.WordDao
import com.example.practice_livedata_18_05_21.entity.WordRoomDatabase


class WordRepository(application: Application){

    private var wordDao: WordDao? = null
    private var mGetAllWords : LiveData<List<Word>>
    private var wordRoomDatabase: WordRoomDatabase? = null

    init {
        val db: WordRoomDatabase = wordRoomDatabase!!.getDatabase(application)
        wordDao = db.wordDao()
        mGetAllWords = wordDao!!.getAllWords()
    }

    fun getAllWord() : LiveData<List<Word>> {
        return mGetAllWords
    }

    fun insert(word : Word) {
        wordRoomDatabase!!.databaseWriteExecutor.execute {
            wordDao!!.insert(word)
        }

    }

}