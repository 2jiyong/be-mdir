package org.reader;

import org.content.TextContent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class TextReader {
    public Optional<TextContent> read(String path) {
        StringBuilder text = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line).append("\n");
            }
        } catch (IOException e) {
            return Optional.empty();
        }
        if(!text.isEmpty()) text.deleteCharAt(text.length() - 1);
        return Optional.of(new TextContent(text.toString()));
    }
}
