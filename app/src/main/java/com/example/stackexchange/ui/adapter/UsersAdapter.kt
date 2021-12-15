package com.example.stackexchange.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.stackexchange.R
import com.example.stackexchange.data.model.Item
import com.example.stackexchange.ui.view.activity.MainActivity

class UsersAdapter(val context: Context): RecyclerView.Adapter<UsersAdapter.UsersAdapterViewHolder>() {

    private var items: ArrayList<Item> = arrayListOf()

    class UsersAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.findViewById(R.id.user_name)
        val reputation: TextView = itemView.findViewById(R.id.reputation)
        val user: ConstraintLayout = itemView.findViewById(R.id.item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UsersAdapterViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.user_item, parent, false))


    override fun onBindViewHolder(holder: UsersAdapterViewHolder, position: Int) {
        holder.userName.text = items[position].display_name
        holder.reputation.text = items[position].reputation.toString()

        holder.user.setOnClickListener {
            (context as MainActivity).loadFragment(items[position])
        }
    }

    override fun getItemCount() = items.size

    fun addUsers(list: List<Item>) {
        items.clear()
        items.addAll(list)
    }
}