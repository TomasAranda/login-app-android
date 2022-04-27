package com.example.login_app

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.login_app.data.RandomUser
import com.example.login_app.data.RandomUserArgument
import com.example.login_app.data.RandomUserResponse

import com.example.login_app.databinding.UserItemBinding

class UsersAdapter() : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    private var values = listOf<RandomUser>()

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
        holder.idView.text = item.name.first
        holder.contentView.text = item.name.last
        holder.button.setOnClickListener { view ->
            val action = UserFragmentDirections.actionUserFragmentToUserDetail(
                RandomUserArgument(
                    item.name.first, item.email, item.picture.large
                )
            )
            view.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = values.size

    @SuppressLint("NotifyDataSetChanged")
    fun setUsers(users: RandomUserResponse) {
        this.values = users.results
        notifyDataSetChanged()
    }

    inner class UserViewHolder(binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content
        val button: Button = binding.changeWelcomeUser
    }

}