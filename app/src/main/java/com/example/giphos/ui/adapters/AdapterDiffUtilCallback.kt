package com.example.giphos.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.giphos.data.model.Giph

class AdapterDiffUtilCallback(val oldList: List<Giph>, val newList:List<Giph>): DiffUtil.Callback() {




    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItemPosition == newItemPosition
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList.get(oldItemPosition) == newList.get(newItemPosition)
    }
}