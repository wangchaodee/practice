package com.iflytek.staff.chao.client;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

@Slf4j
public class Client {

    public static void main(String[] args) {
        String msg = "Client Data" ;
        try {
            Socket socket = new Socket("127.0.0.1",10000);
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw.println(msg);
            pw.flush();

            String line = is.readLine();
            System.out.println("received from server : " + line);
            pw.close();
            is.close();
            socket.close();

        } catch (UnknownHostException e) {
           log.error("client error" ,e);
        } catch (IOException e) {
            log.error("client error" ,e);
        }
    }

}
