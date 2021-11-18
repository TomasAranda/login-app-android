package com.example.login_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
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

        view.findViewById<Button>(R.id.log_button).setOnClickListener {
            // Get EditText values
            val username = view.findViewById<EditText>(R.id.editTextTextUsername).text.toString()
            val password = view.findViewById<EditText>(R.id.editTextTextPassword).text.toString()

            val userToLog = UserContent.UserItem(name = username, password = password)
            try {
                // Log user
                when (args.loginType) {
                    LoginType.LOG_IN -> UserContent.logUserIn(userToLog)
                    LoginType.SIGN_UP -> UserContent.registerNewUser(userToLog)
                }

                // Navigate
                val action = LogInFormFragmentDirections.actionLogInFormFragmentToUserFragment(userToLog.name)
                findNavController().navigate(action)
            } catch (e: Exception) {
                // TODO: Show dialogAlert
                Toast.makeText(context, "Error ${e.message}", Toast.LENGTH_LONG).show()
            }
        }

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