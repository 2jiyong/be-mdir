package org.content;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileDirectory {
    public static final String ROOT_DIRECTORY_PATH = "C:\\";
    public static final String PARENT_PATH = "/..";
    private File directory;
    private final List<File> directories;
    private final List<File> files;

    public FileDirectory() {
        directories = new ArrayList<>();
        files = new ArrayList<>();
        setDirectory(getRootDirectory());
    }

    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        if(!directory.exists()) return;
        this.directory = directory;
        setFilesAndDirectories();
    }

    public String getAbsolutePath(){
        return directory.getAbsolutePath();
    }

    public List<String> getAllString(){
        List<String> allFiles = new ArrayList<>();
        allFiles.addAll(getDirectoriesString());
        allFiles.addAll(getFilesString());
        return allFiles;
    }

    public List<String> getDirectoriesString(){
        List<String> allDirectories = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(File file : directories) {
            sb.append("/");
            sb.append(file.getName());
            allDirectories.add(sb.toString());
            sb.setLength(0);
        }
        return allDirectories;
    }

    public List<String> getFilesString(){
        List<String> allFiles = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(File file : files) {
            sb.append(file.getName());
            allFiles.add(sb.toString());
            sb.setLength(0);
        }
        return allFiles;
    }

    private void setFilesAndDirectories() {
        File[] directoryFiles = directory.listFiles();
        directories.clear();
        files.clear();
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
