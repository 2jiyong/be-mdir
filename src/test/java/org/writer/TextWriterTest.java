package org.writer;

import org.content.TextContent;
import org.junit.jupiter.api.*;
import org.reader.TextReader;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

public class TextWriterTest {
    TextReader reader = new TextReader();
    String path = "files";
    TextWriter writer = new TextWriter(path);

    @Test
    @DisplayName("TextWriter가 해당 경로에 TextContent의 내용을 담은 파일을 만들 수 있다.")
    void testTextWriter() {
        String title = "writer";
        TextContent textContent = new TextContent("hello writer");
        writer.write(title,textContent);
        assertThat(reader.read(path+"/"+title+".txt").get().getText()).isEqualTo("hello writer");
    }

    @Test
    @DisplayName("해당 경로에 이미 파일이 있다면, 내용을 추가할 수 있다.")
    void testTextWriterAppend() {
        String title = "append";
        // 파일 추가
        TextContent textContent = new TextContent("hello append1");
        writer.write(title,textContent);

        TextContent appendText = new TextContent(" append2");
        writer.appendWrite(title,appendText);
        assertThat(reader.read(path+"/"+title+".txt").get().getText()).isEqualTo("hello append1 append2");
    }
}
