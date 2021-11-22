package com.example.login_app

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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

        val formTitle = view.findViewById<TextView>(R.id.logFormTitle)

        when (args.loginType) {
            LoginType.LOG_IN -> formTitle.text = getString(R.string.log_in)
            LoginType.SIGN_UP -> formTitle.text = getString(R.string.sign_up)
        }

        view.findViewById<Button>(R.id.log_button).setOnClickListener(onClickLogButton(view))
    }

    private fun showAlert(message: String, loginType: LoginType) {
        val titleResource: Int = if(loginType == LoginType.LOG_IN) R.string.login_error_title else R.string.signup_error_title
        val logErrorAlertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setMessage(message)
                setTitle(titleResource)
                setCancelable(true)
            }
            builder.create()
        }

        logErrorAlertDialog?.show()
    }

    private fun onClickLogButton(view: View): View.OnClickListener {
        return View.OnClickListener {
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
                showAlert(e.message!!, args.loginType)
            }

            // hide keyboard, if there is focus
            requireActivity().currentFocus?.let {
                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(it.windowToken, 0)
            }
        }
    }
}