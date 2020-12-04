package recursion;

import java.io.File;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.List;

public class FileSearcher {

    private Path root;

    public FileSearcher(String root) throws NotDirectoryException {
        Path rootPath = Paths.get(root);
        if (!Files.isDirectory(rootPath))
            throw new NotDirectoryException("\"" + root + "\"  is not a directory.");

        this.root = rootPath;
    }

    public List<Path> find(String filename) {
        return find(root, filename);
    }

    private List<Path> find(Path root, String filename) {
        List<Path> paths = new LinkedList<>();
        File[] rootFiles = root.toFile().listFiles();

        if (rootFiles == null)
            return paths;

        for (File file : rootFiles) {
            if (file.getName().toLowerCase().contains(filename.toLowerCase())) {
                System.out.println(file);
                paths.add(file.toPath());
            }
            if (file.isDirectory())
                paths.addAll(find(file.toPath(), filename));
        }
        return paths;
    }

    public Path getRoot() {
        return root;
    }

    public void setRoot(String root) throws NotDirectoryException {
        Path rootPath = Paths.get(root);
        if (!Files.isDirectory(rootPath))
            throw new NotDirectoryException("\"" + root + "\" " + "is not a directory.");

        this.root = rootPath;
    }
}
