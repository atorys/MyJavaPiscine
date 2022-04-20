package ex02_FileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Shell {

    private final Path  _start_path;
    private Path        _current_path;

    public Shell(Path path) {
        _start_path = path;
        _current_path = _start_path;
    }

    public void mv(String what, String where) {
        Path path = Paths.get(where);
        File file = new File(what);
//        if (Files.isDirectory(path) && Files.isWritable(path)) {
//            try {
//                File p = new File(where);
//                Files.move(Paths.get(file.getAbsolutePath()), Paths.get(p.getAbsolutePath()));
//            } catch (IOException e) {
//                System.err.println(e.getMessage());
//            }
//        }
//        else {
//            if (!file.renameTo(new File(where)))
//                System.err.println("Unable to rename");
//        }
    }

    public void ls() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(_current_path)) {
            for (Path path : stream) {
                if (!Files.isHidden(path))
                System.out.println(path.getFileName().toString() + "\t" + path.toFile().length() + "KB");

            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void cd(String folder_name) {
        Path path = Paths.get(folder_name);
        if (path.isAbsolute() && Files.isDirectory(path) && Files.isReadable(path))
            _current_path = path;
        else {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(_current_path)) {
                for (Path inner_paths : stream) {
//                    if (inner_paths.equals(path) && Files.isDirectory(inner_paths))
//                        _current_path = path;
                    if (inner_paths.equals(path))
                        _current_path = path;
                }
            }
            catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
//        _current_path = _current_path.normalize();
        System.out.println(_current_path);
    }
}
