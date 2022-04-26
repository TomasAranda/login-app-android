package com.example.login_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.login_app.databinding.FragmentUserDetailBinding
import com.squareup.picasso.Picasso

class UserDetail : Fragment() {

    private val args: UserDetailArgs by navArgs()
    lateinit var binding: FragmentUserDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUserData()
    }

    private fun setUserData() {
        binding.userName.text = args.selectedUser.name
        binding.userEmail.text = args.selectedUser.email
        Picasso.get().load(args.selectedUser.imageUrl).into(binding.userImage)
    }

}