package ex02_FileManager;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import static java.nio.file.StandardCopyOption.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Shell {

    private String  _current_path;

    public Shell(String path) {
        _current_path = path;
        if (!_current_path.endsWith("/"))
            _current_path += "/";
    }

    public void mv(String what, String where) {
        String path;
        String file = what;

        if (!where.endsWith("/"))
            where += "/";

        if ((Paths.get(what).isAbsolute() && Files.notExists(Paths.get(what)))
                || (!Paths.get(what).isAbsolute() && Files.notExists(Paths.get(_current_path + what)))) {
            System.err.println("No such file or directory");
            return;
        }
        else if (!Paths.get(what).isAbsolute())
            file = _current_path + what;

        if (Files.exists(Paths.get(_current_path + where)) && Files.isDirectory(Paths.get(_current_path + where))
                && Paths.get(_current_path + where).isAbsolute())
            path = _current_path + where + Paths.get(what).getFileName().toString();
        else if (Paths.get(where).isAbsolute())
            path = where + Paths.get(what).getFileName().toString();
        else
            path = _current_path + where;

        try {
            Files.move(Paths.get(file), Paths.get(path), REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void ls() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(_current_path))) {
            for (Path path : stream) {
                if (!Files.isHidden(path))
                    System.out.println(path.getFileName().toString() + " " + path.toFile().length() + "KB");
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }

    public void cd(String folder_name) {
        Path path = Paths.get(folder_name);
        if (path.isAbsolute() && Files.isDirectory(path) && Files.isReadable(path))
            _current_path = folder_name;
        else {
            String new_path = _current_path + folder_name;
            if (Files.exists(Paths.get(new_path)) &&  Files.isDirectory(Paths.get(new_path)) &&  Files.isReadable(Paths.get(new_path)))
                _current_path += folder_name;
            else {
                System.err.println("No such directory");
                return;
            }
        }
        _current_path = Paths.get(_current_path).normalize().toString();
        if (!_current_path.endsWith("/"))
            _current_path += "/";
        System.out.println(_current_path);
    }
}
