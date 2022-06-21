package com.restaff.wordle.presentation.wordgame

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.restaff.wordle.R
import com.restaff.wordle.data.models.WordResult
import com.restaff.wordle.databinding.WordRowItemBinding

class WordGameAdapter(private val data: ArrayList<ArrayList<WordResult>>) :
    RecyclerView.Adapter<WordGameAdapter.ViewHolder>() {

    private var currentRow = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<WordRowItemBinding>(
            LayoutInflater.from(parent.context), R.layout.word_row_item, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordGameAdapter.ViewHolder, position: Int) {
        holder.bindData(position, data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun getWordRow(row: Int): String {
        val wordRows = data[row]
        val stringBuilder: StringBuilder = StringBuilder()
        wordRows.forEach {
            stringBuilder.append(it.guess)
        }
        return stringBuilder.toString()
    }

    fun updateDataList(wordResults: ArrayList<WordResult>, currentRow: Int) {
        if(currentRow<data.size && currentRow>=0) {
            data[currentRow] = wordResults
            notifyItemChanged(currentRow)
        }
    }

    fun updateCurrentRow(currentRow: Int){
        val oldCurrentRow = this.currentRow
        this.currentRow = currentRow
        notifyItemChanged(oldCurrentRow)
        notifyItemChanged(currentRow)
    }

    fun lockCurrentRow(){
        val oldCurrentRow = this.currentRow
        this.currentRow = -1
        notifyItemChanged(oldCurrentRow)
    }

    inner class ViewHolder(private val binding: WordRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int, arrayList: ArrayList<WordResult>) {
            binding.listCharView.removeAllViews()
            val charList = ArrayList<CharView>()
            for (index in arrayList.indices) {
                val charView = CharView(binding.root.context)
                charView.bindData(arrayList[index])

                charView.getEditText().isEnabled = position == currentRow
                if(index==arrayList.size-1){
                    charView.getEditText().imeOptions = EditorInfo.IME_ACTION_DONE
                }else{
                    charView.getEditText().imeOptions = EditorInfo.IME_ACTION_NEXT
                }

                charList.add(charView)
                binding.listCharView.addView(charView)
            }

            for (index in charList.indices) {
                charList[index].getEditText().doAfterTextChanged {
                    arrayList[index].guess = it.toString()
                    if (it?.length == 1) {
                        if (charList[index] == charList.last()) {
                            charList[index].getEditText().requestFocus()
                        } else {
                            charList[index + 1].getEditText().requestFocus()
                        }
                    } else {
                        if (charList[index] == charList.first()) {
                            charList[index].getEditText().requestFocus()
                        } else {
                            charList[index - 1].getEditText().requestFocus()
                        }
                    }
                }
            }
        }
    }
}