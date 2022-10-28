package my.jerry;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class JerryServer {
    public static void main(String[] args){
        Socket socket = null;
        BufferedReader in = null;
        BufferedWriter out = null;
        char[] returnValue = {65,66,67}; // ABC

        try {
            socket = new ServerSocket(8878).accept();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String str = null;

            while(true){
                str = in.readLine();
                if(str.equals("")){
                    out.write(returnValue);
                    out.flush();
                }
                System.out.println("클라이언트 : " + str);
                str = null;
            }

        } catch (Exception e){

        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


}
