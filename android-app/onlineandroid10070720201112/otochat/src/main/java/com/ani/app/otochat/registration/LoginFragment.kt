package com.ani.app.otochat.registration

import android.app.Dialog
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ani.app.otochat.R
import com.ani.app.otochat.databinding.FragmentLoginBinding
import com.ani.app.otochat.databinding.FragmentRegistrationBinding
import com.ani.app.otochat.friends.Friend
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val vm : RegistrationViewModel by lazy {
        ViewModelProvider(this).get(RegistrationViewModel::class.java)
    }

    private lateinit var auth: FirebaseAuth
    private lateinit var fbd: FirebaseDatabase
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        auth = Firebase.auth
        fbd = Firebase.database
        dbRef = fbd.getReference(RegPrefs.myId( (activity as RegistrationActivity).appPrefs() ))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.lifecycleOwner = activity // imp
        binding.vm = vm

        vm.reg.observe( viewLifecycleOwner /*imp*/, Observer {
            val currentUser = auth.currentUser
            Log.i("@ani", "Current User DN ${currentUser?.displayName}")
            if(currentUser != null) {
                loginUser(vm.valueOf(vm.userName), vm.valueOf(vm.password))
            } else {
                Log.i("@ani", "User Not Registered")
            }
            Log.i("@ani", "$currentUser")
        })
        
        return binding.root
    }

    private fun loginUser(email : String, pass : String) {
        val dialog = progressAlert()
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(activity as RegistrationActivity ) {
                if(it.isSuccessful) {
                    val user = auth.currentUser
                    RegPrefs.markLoggedIn((activity as RegistrationActivity).appPrefs())
                    dialog.dismiss()

                    val friends = ArrayList<Friend>()
                    friends.add(
                        Friend(
                            RegPrefs.myId( (activity as RegistrationActivity).appPrefs() ), "12.00"
                        )
                    )

                    val map = HashMap<String, List<Friend>>()
                    map.put("friends", friends)
                    dbRef.setValue(map)

                    (activity as RegistrationActivity).startFriendsActivity()
                    Log.i("@ani", "User Registered Successfully ${user?.displayName} ${user?.phoneNumber} ")
                }else {
                    dialog.dismiss()
                    Toast.makeText(activity, "Invalid Creds", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun progressAlert() : Dialog {
        val dialog = AlertDialog.Builder(activity as RegistrationActivity)
            .setTitle("Login")
            .setMessage("Login Process In progress")
            .create()
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()

        return dialog
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}