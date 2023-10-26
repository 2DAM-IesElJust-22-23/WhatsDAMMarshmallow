package com.example.whatsdam

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val messageText: TextView = itemView.findViewById(R.id.msg_text)
    private val timestampText: TextView = itemView.findViewById(R.id.msg_me_timestamp)

    fun bind(message: Missatge) {
        messageText.text = message.text
        timestampText.text = message.hora
    }
}

