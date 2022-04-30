package edu.school21.sockets.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.io.*;
import java.net.Socket;

public class Main {

    static Socket client = null;


    @Parameters(separators = "=", commandDescription = "define server port")
    public static class Arguments {
        @Parameter(names = {"--server-port"})
        private static Integer port;

        Integer getPort() {
            return port;
        }
    }


    public static void write(BufferedWriter output, String message) throws IOException {
        output.write(message);
        output.flush();
    }

    public static void main(String[] args) {

        BufferedReader input = null;
        BufferedReader terminal = null;
        BufferedWriter output = null;

        Arguments arg = new Arguments();
        JCommander jc = JCommander.newBuilder().addObject(arg).build();
        jc.parse(args);

        try {
            client = new Socket("localhost", arg.getPort());
            terminal = new BufferedReader(new InputStreamReader(System.in));
            input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            output = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            String server_answer = input.readLine();
            System.out.println(server_answer);

            if (server_answer.contains("No such command")) {
                return;
            }
            write(output, terminal.readLine() + '\n');

            System.out.println(input.readLine());
            write(output, terminal.readLine() + '\n');

            System.out.println(input.readLine());
            write(output, terminal.readLine() + '\n');

            System.out.println(input.readLine());

        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (client != null)
                    client.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
