package org.writer;

import org.content.TextContent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 * 이 클래스는 텍스트 파일을 만들거나 쓰는 기능을 제공합니다.
 */
public class TextWriter {
    private final String writePath;
    /**
     * 이 클래스는 경로를 받아, 그 경로에만 파일을 씁니다.
     */
    public TextWriter(String writePath) {
        this.writePath = writePath;
    }
    /**
     * 이 메서드는 클래스의 경로에 해당 파일에 내용을 추가로 더합니다.
     *
     * @param title 파일의 제목
     * @param textContent 파일에 쓸 내용
     */
    public void appendWrite(String title, TextContent textContent) {
        write(title,textContent,true);
    }
    /**
     * 이 메서드는 해당 경로에 받은 제목으로 파일을 추가합니다.
     *
     * @param title 파일의 제목
     * @param textContent 파일에 쓸 내용
     */
    public void overWrite(String title, TextContent textContent) {
        write(title,textContent,false);
    }

    private void write(String title, TextContent textContent, boolean append) {
        String filePath = writePath +"/"+title;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, append))) {
            writer.write(textContent.getText());
        } catch (IOException e) {
            return;
        }
    }
}
