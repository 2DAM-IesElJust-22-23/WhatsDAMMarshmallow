package com.example.myapplication.communications

import android.util.Log
import com.example.whatsdam.model.Message
import com.example.whatsdam.repositori.MessagesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

class CommunicationManager() {
    val repository= MessagesRepository.getInstance()



    suspend fun sendServer(msg: String): JSONObject {
        return withContext(Dispatchers.IO) {
            try {
                val socket = Socket(repository.server, repository.port)
                val pw = PrintWriter(socket.getOutputStream(), true)
                val br = BufferedReader(InputStreamReader(socket.getInputStream()))

                pw.println(msg)
                pw.flush()

                val respuesta = br.readLine()

                br.close()
                pw.close()
                socket.close()

                val respuestaJson = JSONObject(respuesta)
                Log.d("MessageRepository", "Mensaje agregado:"+ respuestaJson)


                // Verificar si la respuesta contiene un mensaje entrante y procesarlo
                if (respuestaJson.getString("type") == "message") {
                    val username = respuestaJson.getString("username")
                    val text = respuestaJson.getString("text")
                    // Agregar el mensaje al MessageRepository para mostrarlo en la aplicación
                    val msg = Message(username, text)
                    repository.add(msg)
                }

                respuestaJson
            } catch (e: IOException) {
                JSONObject().put("status", "error")
            }
        }
    }

    suspend fun prepareListener() {


        GlobalScope.launch(Dispatchers.IO) {
            val serverSocket = ServerSocket(repository.listenport!!)

            while (true) {
                val clientSocket = serverSocket.accept()
                val reader = BufferedReader(InputStreamReader(clientSocket.getInputStream()))

                val notification = reader.readLine()

                processNotification(notification)

                val response = JSONObject().put("status", "ok")
                val writer = PrintWriter(clientSocket.getOutputStream(), true)
                writer.println(response.toString())

                writer.close()
                reader.close()
                clientSocket.close()
            }
        }
    }

    private fun processNotification(notification: String) {
        try {
            val json = JSONObject(notification)
            val type = json.getString("type")

            if (type == "message") {
                val username = json.getString("username")
                val text = json.getString("content")
                // Añadir el nuevo mensaje a la lista de mensajes
                var msg=Message(username,text)
                repository.add(msg)
            }
            // Manejar otros tipos de notificaciones si es necesario
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }




    suspend fun login(username: String, server: String): JSONObject {
        val response = JSONObject()
        response.put("status", "error")
        val serverSocket = ServerSocket(0)
        repository.listenport = serverSocket.localPort
        serverSocket.close()
        try {
            prepareListener()

            val msg = JSONObject()
            msg.put("command", "register")
            msg.put("user", username)
            msg.put("listenPort", server)

            val serverResponse = sendServer(msg.toString())

            if (serverResponse.has("status") && serverResponse.getString("status") == "ok") {
                response.put("status", "ok")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return response
    }
}
