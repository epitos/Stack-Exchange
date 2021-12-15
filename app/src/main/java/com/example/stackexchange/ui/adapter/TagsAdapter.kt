package com.example.stackexchange.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stackexchange.R
import com.example.stackexchange.data.model.Tag

class TagsAdapter: RecyclerView.Adapter<TagsAdapter.TagsAdapterViewHolder>()  {

    private var tags: ArrayList<Tag> = arrayListOf()

    class TagsAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tagName: TextView = itemView.findViewById(R.id.tag_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TagsAdapterViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.tag_item, parent, false))

    override fun onBindViewHolder(holder: TagsAdapterViewHolder, position: Int) {
        holder.tagName.text = tags[position].name
    }

    override fun getItemCount() = tags.size

    fun addTags(list: List<Tag>) {
        tags.clear()
        tags.addAll(list)
    }
}