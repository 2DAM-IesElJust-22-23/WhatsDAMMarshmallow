package com.example.whatsdam.viewmodels


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.R
import com.example.myapplication.databinding.MyMsgViewholderBinding
import com.example.whatsdam.repositori.MessagesRepository

class MessageAdapter() :RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    val repository=MessagesRepository.getInstance()
    private val MISSATGE_D_USUARI = 1
    private val MISSATGE_D_ALTRE = 2

    override fun getItemViewType(position: Int): Int {
        // Aquesta funcio permet definir el tipus de vista segons algun criteri

        // Obtenim el missatge en la posició que se'ns indica
        var message = repository.getMenssage(position)

        // Comprovem si l'usuari que ha enviat el missatge
        // és l'usuari actual (per això fem ús del nom d'usuari
        // guardat al repositori)
        return if (message!!.usuari == repository.username ) {
            MISSATGE_D_USUARI
        } else {
            MISSATGE_D_ALTRE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MyMsgViewholderBinding.inflate(inflater, parent, false)
        return when (viewType) {
            MISSATGE_D_USUARI -> {
                val vista = inflater.inflate(R.layout.my_msg_viewholder, parent, false)
                MessageViewHolder(vista)
            }
            MISSATGE_D_ALTRE -> {
                val vista = inflater.inflate(R.layout.other_msg_viewholder, parent, false)
                MessageAltreViewHolder(vista)
            }

            else -> {return MessageViewHolder(binding.root)}
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (getItemViewType(position) === MISSATGE_D_USUARI) {
            (holder as MessageViewHolder).bind(repository.getmessages().get(position))
        } else {
            (holder as MessageAltreViewHolder).bind(repository.getmessages().get(position))
        }
    }



    override fun getItemCount(): Int {
        return repository.getNumMessages() !!
    }


}
