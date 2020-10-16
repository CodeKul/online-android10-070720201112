package com.ani.app.otochat.friends

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ani.app.otochat.R
import com.ani.app.otochat.chat.ChatActivity

data class Friend(
    val name : String,
    val created : String
)

class FriendsAdapter(
    private val ctx : Context,
    private var dataSource : List<Friend>

) : RecyclerView.Adapter<FriendsAdapter.PhoneViewHolder>() {

    inner class PhoneViewHolder(
        private val itVw: View
    ) : RecyclerView.ViewHolder(itVw) {

        fun name() = itVw.findViewById<TextView>(R.id.txtNm)

        fun created() = itVw.findViewById<TextView>(R.id.txtCrd)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        val itemView = LayoutInflater.from(ctx).inflate(R.layout.friend_item, parent, false )
        return PhoneViewHolder(itemView)
    }

    override fun getItemCount() = dataSource.size

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val bnd = Bundle()
            bnd.putString("chatWith", dataSource.get(position).name)

            val intent = Intent(ctx, ChatActivity::class.java)
            intent.putExtras(bnd)


           ctx.startActivity(intent)
        }
        holder.name().text = dataSource[position].name
        holder.created().text = dataSource[position].created
    }

    fun refreshData(ds : List<Friend>) {
        this.dataSource = ds
        notifyDataSetChanged()
    }
}