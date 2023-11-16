package com.example.whatsdam.Viewmodels

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.whatsdam.model.Message

class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val messageText: TextView = itemView.findViewById(R.id.msg_text)
    private val timestampText: TextView = itemView.findViewById(R.id.msg_me_timestamp)

    fun bind(message: Message) {
        messageText.text = message.text
        timestampText.text = message.hora
    }
}

