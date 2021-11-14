package com.example.login_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.navArgs

class LogInFormFragment : Fragment() {
    private val args: LogInFormFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val formTitle = view.findViewById<TextView>(R.id.logFormTitle)

        when (args.loginType) {
            LoginType.LOG_IN -> {
                formTitle.text = getString(R.string.log_in)
            }
            LoginType.SIGN_UP -> {
                formTitle.text = getString(R.string.sign_up)
            }
        }
    }

}