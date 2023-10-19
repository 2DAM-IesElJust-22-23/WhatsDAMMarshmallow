    package com.example.whatsdam

    import android.view.View
    import android.widget.TextView
    import androidx.recyclerview.widget.RecyclerView
    import com.example.myapplication.R
    import java.text.DateFormat
    import java.util.Date

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val usernameTextView: TextView = itemView.findViewById(R.id.msg_text_usuari)
        private val messageTextView: TextView = itemView.findViewById(R.id.msg_text)
        private val timeTextView: TextView = itemView.findViewById(R.id.msg_time)

        fun bind(message: Missatge) {
            usernameTextView.text = message.usuari
            messageTextView.text = message.text

            // Obtener la hora actual y formatearla de manera localizada
            val horaActual = Date()
            val horaFormatada = DateFormat.getTimeInstance().format(horaActual)

            // Asignar la hora formateada al TextView correspondiente
            timeTextView.text = horaFormatada
        }
    }
