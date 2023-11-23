package com.ieseljust.psp.client.communications;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Objects;

import com.ieseljust.psp.client.CurrentConfig;
import com.ieseljust.psp.client.Message;

import netscape.javascript.JSObject;

import org.json.JSONException;
import org.json.JSONObject;

public class communicationManager {

    /* Aquesta classe s'encarrega de la gestió de la
     comunicació amb el servidor.
     */
    
    public static JSONObject sendServer(String msg) throws IOException {

        // TO-DO:
        // Envía al servidor l'string msg
        // I retorna un JSON amb la resposta
        Socket server=new Socket();

        InetSocketAddress socketAddr=new InetSocketAddress(CurrentConfig.server(),CurrentConfig.port());


        server.connect(socketAddr);

        InputStream is= server.getInputStream();
        OutputStream os= server.getOutputStream();

        InputStreamReader isr= new InputStreamReader(is);
        OutputStreamWriter osr= new OutputStreamWriter(os);

        BufferedReader br= new BufferedReader(isr);
        PrintWriter pr=new PrintWriter(osr);

        pr.println(msg);
        pr.flush();
        String line=br.readLine();
        JSONObject resposta=new JSONObject(line);
        pr.close();
        br.close();
        isr.close();
        is.close();
        os.close();
        osr.close();
        server.close();
        return resposta;
    }

    public static void connect() throws JSONException, communicationManagerException, IOException {

        // TO-DO:

        // Creem un misstge pe al servidor amb l'ordre (command) register, 
        // el nom d'usuari (user) i el port (listenPost) pel qual el client escoltarà 
        // les notificacions (el tenim a CurrentConfig.listenPort())

        // Enviarà el missatge al servidor a través de sendServer.

        // Si es produeix un error, llançarà una excepció i aturarà
        // l'aplicaió (per exemple, si l'usuari ja existeix al servidor)
        // Teniu per a això l'excepció communicationManagerException 
        // com a excepció personalitzada al projecte. 


        JSONObject json= new JSONObject();
        json.put("command","register");
        json.put("user",CurrentConfig.username());
        json.put("listenPort",CurrentConfig.listenPort());

        sendServer(json.toString());

    
    }

    public static void sendMessage(Message m) throws IOException {
        // Envia un misstge al servidor (es fa amb una línia!)
        sendServer(m.toJSONCommand().toString());
    }    
}
