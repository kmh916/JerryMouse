package my.jerry;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class JerryClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket(InetAddress.getByName("localhost"),8878);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        BufferedReader keyBoardStream = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            String message = keyBoardStream.readLine();
            if(message.equals("exit")) break;
            out.write(message+"\n");
            out.flush();
        }
        in.close();
        out.close();
        socket.close();
    }
}
