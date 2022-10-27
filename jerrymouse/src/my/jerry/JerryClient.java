package my.jerry;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

public class JerryClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket(InetAddress.getByName("localhost"),8878);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream out = new PrintStream(socket.getOutputStream());

        BufferedReader keyBoardStream = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            String message = keyBoardStream.readLine();
            out.println(message);
            out.flush();
        }
    }
}
