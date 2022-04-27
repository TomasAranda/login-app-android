package com.example.login_app

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
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
import com.example.login_app.api.RandomUserService
import com.example.login_app.data.RandomUserResponse
import com.example.login_app.databinding.FragmentUsersListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        fetchRandomUsers(binding.list.adapter as UsersAdapter)


        binding.userNameTitle = args.loggedUserName

        return view
    }

    private fun fetchRandomUsers(adapter: UsersAdapter) {
        RandomUserService.getUsers(object : Callback<RandomUserResponse> {
            override fun onResponse(
                call: Call<RandomUserResponse>,
                response: Response<RandomUserResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    adapter.setUsers(response.body()!!)
                }
            }

            override fun onFailure(call: Call<RandomUserResponse>, t: Throwable) {
                Log.e("API CALL ERROR", "${t.message}")
                t.printStackTrace()
            }
        })
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