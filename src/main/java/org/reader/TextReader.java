package org.reader;

import org.content.TextContent;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
/**
 * 이 클래스는 텍스트 파일을 읽어오는 클래스입니다.
 */
public class TextReader {
    private Path filePath;

    public TextReader(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * 경로를 받아 해당 파일을 읽어 TextContent로 만들어 반환하는 클래스입니다.
     *
     * @param path 경로
     * @return Optional (만약 path에 파일이 없으면 empty)
     */
    public Optional<TextContent> read(Path path) {
        StringBuilder text = new StringBuilder();
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
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
