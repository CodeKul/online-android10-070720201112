package com.ani.app.otochat.registration

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ani.app.otochat.MainActivity
import com.ani.app.otochat.R
import com.ani.app.otochat.databinding.FragmentRegistrationBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegistrationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegistrationFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null


    private val vm : RegistrationViewModel by lazy {
        ViewModelProvider(this).get(RegistrationViewModel::class.java)
    }

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        auth = Firebase.auth

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding : FragmentRegistrationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_registration, container, false)
        binding.lifecycleOwner = activity // imp
        binding.vm = vm

        vm.reg.observe( viewLifecycleOwner /*imp*/, Observer {
            Log.i("@ani", "Registration Clicked")

            val currentUser = auth.currentUser
            if(currentUser == null) {
                registerUser(vm.valueOf(vm.userName), vm.valueOf(vm.password))
            }
            Log.i("@ani", "$currentUser")
        })

        return binding.root
    }

    private fun registerUser(email : String, pass : String) {
        val dialog = progressAlert()
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(activity as RegistrationActivity ) {
            if(it.isSuccessful) {
                val user = auth.currentUser
                dialog.dismiss()
                (activity as RegistrationActivity).startFriendsActivity()
                Log.i("@ani", "User Registered Successfully ${user?.displayName} ${user?.phoneNumber} ")
            }else {
                dialog.dismiss()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegistrationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegistrationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun progressAlert() : Dialog {
        val dialog = AlertDialog.Builder(activity as RegistrationActivity)
            .setTitle("Registration")
            .setMessage("User Registration in Progress")
            .create()
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()

        return dialog
    }
}