package edu.school21.sockets.app;

import com.beust.jcommander.JCommander;
import edu.school21.sockets.server.Server;
import java.io.IOException;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;


public class Main {

    @Parameters(separators = "=", commandDescription = "define server port")
    public static class Arguments {
        @Parameter (names = {"--port"})
        private static Integer port;

        Integer getPort() {
            return port;
        }
    }

    public static void main(String[] args) throws IOException {
        Arguments arg = new Arguments();
        JCommander jc = JCommander.newBuilder().addObject(arg).build();
        jc.parse(args);

        Server server = new Server(arg.getPort());
        server.start();
    }
}
