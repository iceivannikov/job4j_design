package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        boolean isClosed = false;
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!isClosed) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String response = input.readLine();
                    if (response.contains("msg=Hello")) {
                        output.write("Hello".getBytes());
                    } else if (response.contains("msg=Exit")) {
                        output.write("Server shutting down...".getBytes());
                        isClosed = true;
                    } else {
                        output.write("What".getBytes());
                    }
                    output.flush();
                }
            }
        }
    }
}
