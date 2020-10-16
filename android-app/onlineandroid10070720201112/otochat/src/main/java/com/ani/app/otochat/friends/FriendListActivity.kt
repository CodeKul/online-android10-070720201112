package com.ani.app.otochat.friends

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.ani.app.otochat.R
import com.ani.app.otochat.registration.RegPrefs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_friend_list.*
import kotlinx.android.synthetic.main.dialog_add_friend.*
import kotlinx.android.synthetic.main.dialog_add_friend.view.*

class FriendListActivity : AppCompatActivity() {
    private lateinit var adapter: FriendsAdapter
    private lateinit var fbd: FirebaseDatabase
    private lateinit var dbRef: DatabaseReference
    val friends = ArrayList<Friend>()

    private val prefs: SharedPreferences by lazy {
        getSharedPreferences(
            RegPrefs.PREFS_NAME,
            MODE_PRIVATE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_list)

        initFirebase()

        initRecycler()

        fabAdd.setOnClickListener { openAddFriendDialog() }
    }

    private fun initFirebase() {
        fbd = Firebase.database
        dbRef = fbd.getReference(RegPrefs.myId(prefs))
    }

    private fun initRecycler() {
        val dataSource = arrayListOf<Friend>()
        adapter = FriendsAdapter(this, dataSource)

        recFrnds.layoutManager = LinearLayoutManager(this)
        recFrnds.adapter = adapter

        loadFriendsOnInit()
    }

    private fun openAddFriendDialog() {
        val builder = MaterialAlertDialogBuilder(this, R.style.MaterialAlertDialog_rounded)
        val alert = builder.create()

        val view = LayoutInflater
            .from(this)
            .inflate(R.layout.dialog_add_friend, null)

        view.btOk.setOnClickListener {
            saveFriendToFirebase(view.etFrnd.text.toString())
            alert.dismiss()
        }

        view.btCn.setOnClickListener { alert.dismiss() }
        alert.setView(view)
        alert.show()
    }

    private fun saveFriendToFirebase(friendId: String) {
        val updatedFriends = ArrayList<Friend>()
        updatedFriends.addAll(friends)

        val map = HashMap<String, Any>()
        map.put("name", friendId.toString())
        map.put("created", "20.30")
        updatedFriends.add(
            Friend(
                map.get("name").toString(),
                map.get("created").toString()
            )
        )

        dbRef.child("friends").setValue(updatedFriends)
    }

    private fun loadFriendsOnInit() {
        dbRef.child("friends").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.value != null) {
                    friends.clear()
                    Log.i("@ani", "Valu Not Null")
                    Log.i("@ani", "" + snapshot.value)

                    val frd = snapshot.value as ArrayList<Map<String, String>>
                    frd.forEach {
                        if(it != null)
                            friends.add(Friend(it.get("name") ?: "", it.get("created") ?: ""))
                    }
                    adapter.refreshData(friends)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

    }
}