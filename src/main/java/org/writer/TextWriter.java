package org.writer;

import org.content.TextContent;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * 이 클래스는 텍스트 파일을 만들거나 쓰는 기능을 제공합니다.
 */
public class TextWriter {
    private final Path writePath;
    /**
     * 이 클래스는 경로를 받아, 그 경로에만 파일을 씁니다.
     */
    public TextWriter(Path writePath) {
        this.writePath = writePath;
    }
    /**
     * 이 메서드는 클래스의 경로에 해당 파일에 내용을 추가로 더합니다.
     *
     * @param title 파일의 제목
     * @param textContent 파일에 쓸 내용
     */

    public void write(String title, TextContent textContent) {
        Path filePath = writePath.resolve(title);
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE)) {
            writer.write(textContent.getText());
        } catch (IOException e) {
            return;
        }
    }
}
