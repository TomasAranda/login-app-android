package com.example.login_app

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.navArgs

/**
 * A fragment representing a list of Users.
 */
class UserFragment : Fragment() {
    private val args: UserFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//
//        // This callback will only be called when MyFragment is at least Started.
//        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
//            OnBackPressedCallback(true) {
//                // Handle the back button event
//                val builder = AlertDialog.Builder(getActivity())
//
//                builder.setTitle("Save Or Not")
//                builder.setMessage("Do you want to save this? ")
//                builder.setPositiveButton("Save", DialogInterface.OnClickListener()) {
//                    onClick(dialog, id) {
//                        // Save your content
//                        save()
//                        // Then pop
//                        NavHostFragment.findNavController(MyFragment.this).popBackStack()
//                    }
//                    builder.setNegativeButton("Discard",null)
//                    builder.show()
//                })
//            }
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_users_list, container, false)

        val rv: RecyclerView = view.findViewById(R.id.list)
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = UsersAdapter(UserContent.listOfUsers)

        val title = view.findViewById<TextView>(R.id.list_title)
        title.text = args.loggedUserName

        return view
    }

}