package com.example.login_app

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.login_app.data.AppDatabase
import com.example.login_app.data.User
import com.example.login_app.databinding.FragmentUsersListBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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

        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.adapter = UsersAdapter()

        // Pass users from DB to RVAdapter
        runBlocking {
            launch {
                val usersListFromDB: List<User>? = getUsersFromDB()
                if (usersListFromDB != null) {
                    (binding.list.adapter as UsersAdapter).setUsers(usersListFromDB)
                }
            }
        }

        binding.userNameTitle = args.loggedUserName

        return view
    }

    private suspend fun getUsersFromDB(): List<User>? {
        return context?.let { AppDatabase.getInstance(it).userDao().getAll() }
    }

    private fun handleBackPressed(activity: FragmentActivity?): OnBackPressedCallback {
        return object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val logOutAlertDialog: AlertDialog? = activity?.let {
                    val builder = AlertDialog.Builder(it)
                    builder.apply {
                        setTitle(R.string.dialog_title)
                        setMessage(R.string.dialog_message)

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