package com.iflytek.staff.chao.server;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class ServerBIO {

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(10000);
            Socket socket = server.accept();
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = is.readLine();
            System.out.println("received from client : " + line);
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println("received data : " + line);
            pw.flush();
            pw.close();
            is.close();
            socket.close();
            server.close();
        }catch (Exception e){
            log.error("ServerBIO error" ,e);
        }
    }
}
