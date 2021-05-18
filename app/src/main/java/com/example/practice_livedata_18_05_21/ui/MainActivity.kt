package com.example.practice_livedata_18_05_21.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practice_livedata_18_05_21.AddNewWordActivity
import com.example.practice_livedata_18_05_21.R
import com.example.practice_livedata_18_05_21.entity.Word
import com.example.practice_livedata_18_05_21.viewmodel.WordViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val NEW_WORD_ACTIVITY_REQUEST_CODE = 1

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private lateinit var wordRecyclerAdapter: WordRecyclerAdapter
    private lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        wordRecyclerAdapter = WordRecyclerAdapter(arrayListOf())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = wordRecyclerAdapter
        wordRecyclerAdapter.notifyDataSetChanged()
        recyclerView = findViewById(R.id.recyclerview)
        fab = findViewById(R.id.fab)



        wordViewModel.getAllWords().observe(this, Observer { wordList ->
            wordRecyclerAdapter.saveData(wordList)
        })

        fab.setOnClickListener {
            startActivityForResult(
                Intent(this, AddNewWordActivity::class.java),
                NEW_WORD_ACTIVITY_REQUEST_CODE
            )
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            val word = data!!.getStringExtra(AddNewWordActivity().EXTRA_REPLY)?.let { Word(it) }
            wordViewModel.insert(word!!)
        } else {
            Toast.makeText(
                this,
                "No data assigned",
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}