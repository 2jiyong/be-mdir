package org.content;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileDirectory {
    private File directory;
    private List<File> directories;
    private List<File> files;

    public FileDirectory() {
        this.directory = new File("C://");
        directories = new ArrayList<>();
        files = new ArrayList<>();
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }

    public void getFilesAndDirectories() {
        File[] directoryFiles = directory.listFiles();
        for(File file : directoryFiles) {
            if(file.isDirectory()) directories.add(file);
            else files.add(file);
        }
    }

    public void printFiles() {
        System.out.print(getDirectoriesString());
        System.out.print(getFilesString());
    }

    private String getDirectoriesString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(File file : directories) {
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
