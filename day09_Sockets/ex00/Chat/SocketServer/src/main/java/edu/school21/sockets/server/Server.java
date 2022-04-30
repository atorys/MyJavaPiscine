package edu.school21.sockets.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    static ServerSocket server = null;
    static Socket socket = null;

    public Server(int port) throws IOException {
        server = new ServerSocket(port);
    }

    public void start() throws IOException {
        for(;;) {
            socket = server.accept();
        }
    }
}
