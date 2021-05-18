package com.example.practice_livedata_18_05_21.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practice_livedata_18_05_21.R
import com.example.practice_livedata_18_05_21.entity.Word

class WordRecyclerAdapter(private var wordList: ArrayList<Word>) :
    RecyclerView.Adapter<WordRecyclerAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.textView)
        fun bind(word: Word) {
            textView.text = word.mWord
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(wordList[position])
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    fun saveData(data : List<Word>) {
        wordList.addAll(data)
        notifyDataSetChanged()
    }

}