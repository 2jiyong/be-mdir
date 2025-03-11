package org.writer;

import org.content.TextContent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TextWriter {
    private final String writePath;

    public TextWriter(String writePath) {
        this.writePath = writePath;
    }

    private void write(String title, TextContent textContent, boolean append) {
        String filePath = writePath +"/"+title+".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, append))) {
            writer.write(textContent.getText());
        } catch (IOException e) {
            return;
        }
    }

    public void appendWrite(String title, TextContent textContent) {
        write(title,textContent,true);
    }

    public void overWrite(String title, TextContent textContent) {
        write(title,textContent,false);
    }
}
