package ex03_TooManyThreads;

import java.net.URL;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class Downloader {

    private List<URL> files;
    private Path path;


    public Downloader(String path) {
        this.files = new LinkedList<>();
    }

    public void addFile(String filename) {
    }

}
