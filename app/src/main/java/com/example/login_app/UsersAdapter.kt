package com.example.login_app

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import com.example.login_app.databinding.UserItemBinding

class UsersAdapter(
    private val values: List<UserContent.UserItem>,
    private val onClick: (UserContent.UserItem) -> Unit
) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id.toString()
        holder.contentView.text = item.name
        holder.button.setOnClickListener { onClick(item) }
    }

    override fun getItemCount(): Int = values.size

    inner class UserViewHolder(binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content
        val button: Button = binding.changeWelcomeUser
    }

}