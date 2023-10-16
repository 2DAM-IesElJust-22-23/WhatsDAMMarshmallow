package com.example.whatsdam

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val usernameTextView: TextView = itemView.findViewById(R.id.msg_text_usuari)
    private val messageTextView: TextView = itemView.findViewById(R.id.msg_text)

    fun bind(message: Missatge) {
        usernameTextView.text = message.usuari
        messageTextView.text = message.text
    }
}

