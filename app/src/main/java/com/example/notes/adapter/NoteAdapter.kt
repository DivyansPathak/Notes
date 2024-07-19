package com.example.notes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.NoteLayoutBinding
import com.example.notes.fragment.HomeFragmentDirections
import com.example.notes.model.Note

class NoteAdapter() : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    class NoteViewHolder(val itemBinding: NoteLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root){}

    // refresh the ui of view without refreshing the entire dataset
    private val differCallBack = object : DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.noteDesc == newItem.noteDesc &&
                    oldItem.noteTitle == newItem.noteTitle
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }


    }
    val differ = AsyncListDiffer(this,differCallBack) // determine the difference b/w two list on background thread for smooth ui experience
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
       return NoteViewHolder(
           NoteLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
       )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position]

        holder.itemBinding.noteTitle.text = currentNote.noteTitle
        holder.itemBinding.noteDesc.text = currentNote.noteDesc

        // if i click on item on recycle view

        holder.itemView.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(currentNote)
            it.findNavController().navigate(direction)

        }
    }

}