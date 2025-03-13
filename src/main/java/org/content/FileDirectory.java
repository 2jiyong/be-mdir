package org.content;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileDirectory {
    public static final String ROOT_DIRECTORY_PATH = "C:\\";
    public static final String PARENT_PATH = "/..";
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
        setFilesAndDirectories();
    }

    public void printFiles() {
        System.out.print(getParentString());
        System.out.print(getDirectoriesString());
        System.out.print(getFilesString());
    }

    public List<File> getAllFiles() {
        List<File> allFiles = new ArrayList<>();
        parentDirectory.ifPresent(allFiles::add);
        allFiles.addAll(directories);
        allFiles.addAll(files);
        return allFiles;
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

    private void setFilesAndDirectories() {
        if(isRootDirectory(directory)) {
            parentDirectory = Optional.empty();
        }
        File[] directoryFiles = directory.listFiles();
        directories.clear();
        files.clear();
        if(!isRootDirectory(directory)) {
            parentDirectory = Optional.of(directory.getParentFile());
        }
        if(directoryFiles == null) return;
        for(File file : directoryFiles) {
            if(file.isDirectory()) directories.add(file);
            else files.add(file);
        }
    }

    public static boolean isRootDirectory(File file) {
        return file.getAbsolutePath().equals(ROOT_DIRECTORY_PATH);
    }

    public static File getRootDirectory() {
        return new File(ROOT_DIRECTORY_PATH);
    }
}
