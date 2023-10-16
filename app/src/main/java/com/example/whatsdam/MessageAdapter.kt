package com.example.whatsdam

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMessagesWindowBinding

class MessageAdapter : RecyclerView.Adapter<MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivityMessagesWindowBinding.inflate(inflater, parent, false)
        return MessageViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = MessageDataSet.messages[position]
        holder.bind(message)
    }

    override fun getItemCount(): Int {
        return MessageDataSet.messages.size
    }
}
