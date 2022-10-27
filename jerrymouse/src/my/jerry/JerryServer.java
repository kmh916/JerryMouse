package my.jerry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class JerryServer {
    public static void main(String[] args) throws IOException {
        Socket socket = new ServerSocket(8878).accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream out = new PrintStream(socket.getOutputStream());

        String str = null;

        str = in.readLine();
        System.out.println("클라이언트 : " + str);
        socket.close();
    }
}
