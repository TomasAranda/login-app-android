package com.example.login_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.log_in).setOnClickListener {
            val action = WelcomeFragmentDirections.actionWelcomeFragmentToLogInFormFragment(LoginType.LOG_IN)
            findNavController().navigate(action)
        }

        view.findViewById<Button>(R.id.sign_up).setOnClickListener {
            val action = WelcomeFragmentDirections.actionWelcomeFragmentToLogInFormFragment(LoginType.SIGN_UP)
            findNavController().navigate(action)
        }
    }
}