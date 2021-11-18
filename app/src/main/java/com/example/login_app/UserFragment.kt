package com.example.login_app

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs

/**
 * A fragment representing a list of Users.
 */
class UserFragment : Fragment() {

    private val args: UserFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Add callback for custom back press navigation
        requireActivity().onBackPressedDispatcher.addCallback(this, handleBackPressed(activity))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_users_list, container, false)

        // hide keyboard, if there is focus from previous fragment
        requireActivity().currentFocus?.let {
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }

        val rv: RecyclerView = view.findViewById(R.id.list)
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = UsersAdapter(UserContent.listOfUsers)

        val title = view.findViewById<TextView>(R.id.list_title)
        title.text = args.loggedUserName

        return view
    }

}

fun handleBackPressed(activity: FragmentActivity?): OnBackPressedCallback {
    return object: OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            Toast.makeText(activity, "You are signed out", Toast.LENGTH_LONG).show()
            activity?.findNavController(R.id.nav_host)?.navigate(R.id.action_userFragment_to_welcomeFragment)
        }
    }
}

//}