package org.content;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileDirectory {
    private static final String ROOT_DIRECTORY_PATH = "C:\\";
    private static final String PARENT_PATH = "/..";
    private File directory;
    private Optional<File> parentDirectory;
    private final List<File> directories;
    private final List<File> files;

    public FileDirectory() {
        parentDirectory = Optional.empty();
        directories = new ArrayList<>();
        files = new ArrayList<>();
        setDirectory(getRootDirectory());
    }

    public void setDirectory(File directory) {
        if(!directory.exists()) return;
        this.directory = directory;
        getFilesAndDirectories();
    }

    public void printFiles() {
        System.out.print(getParentString());
        System.out.print(getDirectoriesString());
        System.out.print(getFilesString());
    }

    private void getFilesAndDirectories() {
        File[] directoryFiles = directory.listFiles();
        directories.clear();
        files.clear();
        if(!isRootDirectory(directory)) {
            parentDirectory = Optional.of(directory.getParentFile());
        }

        for(File file : directoryFiles) {
            if(file.isDirectory()) directories.add(file);
            else files.add(file);
        }
    }

    public String getParentString(){
        return isRootDirectory(directory) ? "" : PARENT_PATH+"\n";
    }

    public String getDirectoriesString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(File file : directories) {
            stringBuilder.append("/");
            stringBuilder.append(file.getName());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public String getFilesString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(File file : files) {
            stringBuilder.append(file.getName());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private boolean isRootDirectory(File file) {
        return file.getAbsolutePath().equals(ROOT_DIRECTORY_PATH);
    }

    private File getRootDirectory() {
        return new File(ROOT_DIRECTORY_PATH);
    }
}
