package org.content;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileDirectory {
    private File directory;
    private static final File rootDirectory = new File("C://");
    private final List<File> directories;
    private final List<File> files;

    public FileDirectory() {
        this.directory = new File("C://");
        directories = new ArrayList<>();
        files = new ArrayList<>();
    }

    public void setDirectory(File directory) {
        if(directory.exists()) return;
        this.directory = directory;
        getFilesAndDirectories();
    }

    public void printFiles() {
        System.out.print(getDirectoriesString());
        System.out.print(getFilesString());
    }

    private void getFilesAndDirectories() {
        File[] directoryFiles = directory.listFiles();
        directories.clear();
        files.clear();
        for(File file : directoryFiles) {
            if(file.isDirectory()) directories.add(file);
            else files.add(file);
        }
    }

    private String getDirectoriesString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(File file : directories) {
            stringBuilder.append("/");
            stringBuilder.append(file.getName());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private String getFilesString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(File file : files) {
            stringBuilder.append(file.getName());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
