package edu.school21.sockets.server;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    static ServerSocket server = null;
    static Socket socket = null;

    public Server(int port) throws IOException {
        server = new ServerSocket(port);
    }

    private void write(BufferedWriter output, String message) throws IOException {
        output.write(message);
        output.flush();
    }

    public void start() {
        BufferedReader  client_input = null;
        BufferedWriter  client_output = null;
        ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
        UsersService service = context.getBean("usersService", UsersService.class);

        try {
            socket = server.accept();

            client_input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            client_output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            write(client_output, "Hello from Server! <3\n> ");

            switch ( client_input.readLine()) {
                case ("signUp"): {
                    write(client_output, "Enter username:\n> ");
                    String username = client_input.readLine();

                    write(client_output, "Enter password:\n> ");
                    String password = client_input.readLine();

                    try {
                        service.signUp(username, password);
                        write(client_output, "Authentication successful");
                    } catch (UsersServiceImpl.AlreadyAuthenticatedException e) {
                        write(client_output, "This user is already authenticated");
                    }
                    break;
                }
                default:
                    write(client_output, "No such command\n"); break;
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                client_input.close();
                client_output.close();
                socket.close();
                server.close();
            } catch (IOException | NullPointerException throwables) {
                throwables.getStackTrace();
            }
        }
    }
}
