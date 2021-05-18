package com.example.practice_livedata_18_05_21.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.practice_livedata_18_05_21.entity.Word
import com.example.practice_livedata_18_05_21.repository.WordRepository

open class WordViewModel(application: Application) : AndroidViewModel(application) {

    private var wordRepository: WordRepository = WordRepository(application)
    private var mAllWord: LiveData<List<Word>>? = null

    init {
        mAllWord = wordRepository.getAllWord()
    }

    fun insert(word: Word) {
        wordRepository.insert(word)
    }

    fun getAllWords(): LiveData<List<Word>> {
        return mAllWord!!
    }

}