package com.ani.app.mychatapp.contact

import android.content.Context
import android.provider.ContactsContract


// Home Work
// Show contacts is recyclerview
// Show all SMS in Recycler View

data class Contact(
    val nm : String,
    val num : String
)

class ContactUtil(private val context : Context) {
    fun fetchContacts() : List<Contact> {

        val contacts = arrayListOf<Contact>()
        // select PROJECTION from contacts where _id = 10 and name = 'kjhjk'
        val PROJECTION: Array<out String> = arrayOf(
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts.HAS_PHONE_NUMBER
        )

        val cursor = context.contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            PROJECTION,
            null,
            null,
            null
        )
        cursor?.let {
            while(cursor.moveToNext()) {

                val id = cursor.getString(
                    cursor.getColumnIndex(
                        ContactsContract.Contacts._ID
                    )
                )

                val name = cursor.getString(
                    cursor.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME
                    )
                )

                val hasPh = cursor.getInt(
                    cursor.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER
                    )
                )

                //select * from number where contact_id = id
                if(hasPh > 0) {
                    val phCur = context.contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} = ?",
                        arrayOf<String>(id),
                        null
                    )

                    phCur?.let {
                        while(phCur.moveToNext()) {
                            val num = phCur.getString(
                                phCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                            )
                            val contact = Contact( name, num )
                            contacts.add(contact)
//                            Log.i("@ani", "Name is $name, Number $num")
                        }
                    }
                }
            }
            cursor.close()
        }
       return contacts
    }
}