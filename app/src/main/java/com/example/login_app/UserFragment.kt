package com.example.login_app

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.login_app.databinding.FragmentUsersListBinding

/**
 * A fragment representing a list of Users.
 */
class UserFragment : Fragment() {

    private val args: UserFragmentArgs by navArgs()
    lateinit var binding: FragmentUsersListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Add callback for custom back press navigation
        requireActivity().onBackPressedDispatcher.addCallback(this, handleBackPressed(activity))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_users_list, container, false)

        val view = binding.root

        // hide keyboard, if there is focus from previous fragment
        requireActivity().currentFocus?.let {
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(it.windowToken, 0)
        }

        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.adapter = UsersAdapter(UserContent.listOfUsers) { adapterOnClick(it) }

        binding.userNameTitle = args.loggedUserName

        return view
    }

    /* Updates List Title when User Item button is clicked. */
    private fun adapterOnClick(user: UserContent.UserItem) {
        binding.userNameTitle = user.name
    }

    private fun handleBackPressed(activity: FragmentActivity?): OnBackPressedCallback {
        return object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val logOutAlertDialog: AlertDialog? = activity?.let {
                    val builder = AlertDialog.Builder(it)
                    builder.apply {
                        setMessage(R.string.dialog_message)
                        setTitle(R.string.dialog_title)

                        setPositiveButton(R.string.ok) { _, _ ->
                            activity.findNavController(R.id.nav_host).navigate(R.id.action_userFragment_to_welcomeFragment)
                        }
                        setNegativeButton(R.string.cancel) { _, _ ->  }
                    }
                    builder.create()
                }
                logOutAlertDialog?.setOnShowListener { logOutAlertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(
                    ContextCompat.getColor(context!!, R.color.black)
                ) }
                logOutAlertDialog?.show()
            }
        }
    }

}