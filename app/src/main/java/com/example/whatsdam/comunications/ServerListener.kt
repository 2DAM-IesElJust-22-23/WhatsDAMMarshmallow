package com.example.whatsdam.comunications

import com.example.whatsdam.model.Message
import com.example.whatsdam.repositori.MessagesRepository
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket


class serverListener() : Runnable {
    /*
     * Aquesta classe s'encarrega de gestionar els broadcasts que fa el servidor
     * cap als clients subscrits a les seues publicacions.
     * Implementarà doncs un servei que escoltarà en un port aleatori el que
     * li envia el servidor de missatgeria i ho processarà de forma adeqüada.
     *
     */
    // Referencia al repositori
    val repository= MessagesRepository.getInstance()


    override fun run() {
        // 1. Crear un socket de tipus servidor que escolte pel port de recepció de
        // missatges
        var listener: ServerSocket? = null
        try {
            // Creem el socket en un  port determinat pel sistema
            // i el guardem a listenPort.
            listener = ServerSocket(0)
            repository.listenport=listener.getLocalPort()
        } catch (e: IOException) {
            println("El port ${repository.listenport} està ocupato és inaccessible.")
            return
        }


        while (true) {
            try {
                val s = listener.accept()
                val `is` = s.getInputStream()
                val isr = InputStreamReader(`is`)
                val br = BufferedReader(isr)
                val json = JSONObject(br.readLine())
                val pregunta = json.getString("type")
                val resposta = JSONObject()
                when (pregunta) {
                    "userlist" -> {
                        resposta.put("status", "ok")
                    }

                    "message" -> {
                        val user = json.getString("user")
                        val content = json.getString("content")
                        val msg = Message(user, content)
                        repository.add(msg)
                        resposta.put("status", "ok")
                    }

                    else -> resposta.put("status", "error")
                }
                val os = s.getOutputStream()
                val pw = PrintWriter(os)
                pw.write(resposta.toString() + "\n")
                pw.flush()
                pw.close()
                os.close()
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }
    }
}

