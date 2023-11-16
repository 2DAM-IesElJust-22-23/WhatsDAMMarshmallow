package com.example.whatsdam.Viewmodels

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.MyMsgViewholderBinding
import com.example.whatsdam.Viewmodels.MessageViewHolder
import com.example.whatsdam.model.MessageDataSet
import com.example.whatsdam.repositori.MessagesRepository

class MessageAdapter : RecyclerView.Adapter<MessageViewHolder>() {
    val repository=MessagesRepository.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MyMsgViewholderBinding.inflate(inflater, parent, false)
        return MessageViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = repository.getMenssage(position)
        holder.bind(message)
    }

    override fun getItemCount(): Int {
        return repository.getNumMessages()
    }
}
