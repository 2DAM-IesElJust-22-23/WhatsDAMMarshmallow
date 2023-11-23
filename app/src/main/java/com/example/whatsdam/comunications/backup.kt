package com.example.whatsdam.comunications

import com.example.whatsdam.model.Message
import com.example.whatsdam.repositori.MessagesRepository
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.net.InetSocketAddress
import java.net.Socket

/**
object communicationManager {
    /* Aquesta classe s'encarrega de la gestió de la
     comunicació amb el servidor.
     */

    // Referencia al repositori
    val repository= MessagesRepository.getInstance()
    @Throws(IOException::class)
    fun sendServer(msg: String?): JSONObject {

        val server = Socket()
        val socketAddr: InetSocketAddress = InetSocketAddress(repository.server, repository.port)
        server.connect(socketAddr)
        val `is` = server.getInputStream()
        val os = server.getOutputStream()
        val isr = InputStreamReader(`is`)
        val osr = OutputStreamWriter(os)
        val br = BufferedReader(isr)
        val pr = PrintWriter(osr)
        pr.println(msg)
        pr.flush()
        val line = br.readLine()
        val resposta = JSONObject(line)
        pr.close()
        br.close()
        isr.close()
        `is`.close()
        os.close()
        osr.close()
        server.close()
        return resposta
    }

    @Throws(JSONException::class, IOException::class)
    fun connect() {

        val json = JSONObject()
        json.put("command", "register")
        json.put("user", repository.username)
        json.put("listenPort", repository.listenport)
        sendServer(json.toString())
    }

    @Throws(IOException::class)
    fun sendMessage(m: Message) {
        // Envia un misstge al servidor (es fa amb una línia!)
        sendServer(repository.toJsonComand(m).toString())
    }
}

*/