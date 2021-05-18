package com.example.practice_livedata_18_05_21.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class Word(

    @PrimaryKey
    @ColumnInfo(name = "word")
    var mWord: String
)
