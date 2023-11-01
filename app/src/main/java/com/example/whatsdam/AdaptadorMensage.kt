package com.example.whatsdam

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsdamMarshmelow.R

class AdaptadorMensage: RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val vista=inflater.inflate(R.layout.activity_messages_window, parent,false);
        return MensageViewHolder(vista)
    }

    override fun getItemCount(): Int {
        return Mensages.mensages.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MensageViewHolder).bind(Mensages.mensages[position]);
    }

}