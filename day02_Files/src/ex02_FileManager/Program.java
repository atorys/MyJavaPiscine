package ex02_FileManager;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Program {

    public static void error(String message) {
        System.err.println(message);
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            if (!args[0].contains("=") || !args[0].startsWith("--current-folder")) {
                System.err.printf("Unexpected argument: %s\n", args[0]);
                System.exit(-1);
            }
            Path path = Paths.get(args[0].substring(args[0].indexOf("=") + 1));
            if (!path.isAbsolute() || !Files.isDirectory(path) || !Files.isReadable(path)){
                System.err.printf("Invalid absolute path: %s\n", path);
                System.exit(-1);
            }

            Shell       shell = new Shell(path);
            Scanner     in = new Scanner(System.in);
            String      command;
            String[]    arguments;

            for (;;) {
                command = in.nextLine();
                if (command.isEmpty())
                    continue ;

                arguments = command.split(" ");
                switch (arguments[0])  {
                    case ("ls"): {
                        shell.ls();
                        break ;
                    }
                    case ("mv"): {
                        if (arguments.length != 3) {
                            error("mv WHAT WHERE");
                            continue;
                        }
                        shell.mv(arguments[1], arguments[2]);
                        break;
                    }
                    case ("cd"): {
                        if (arguments.length != 2) {
                            error("cd FOLDER_NAME");
                            continue;
                        }
                        shell.cd(arguments[1]);
                        break;
                    }
                    default: System.err.println("available commands : cd, ls, mv");
                }
            }
        }
    }
}
