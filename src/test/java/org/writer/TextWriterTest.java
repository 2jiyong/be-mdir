package org.writer;

import org.content.TextContent;
import org.junit.jupiter.api.*;
import org.reader.TextReader;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.*;

public class TextWriterTest {
    TextReader reader = new TextReader();
    Path basePath = Paths.get("files");
    TextWriter writer = new TextWriter(basePath);

    @Test
    @DisplayName("TextWriter가 해당 경로에 TextContent의 내용을 담은 파일을 만들 수 있다.")
    void testTextWriter() {
        String title = "writer.txt";
        TextContent textContent = new TextContent("hello writer");
        writer.write(title,textContent);
        Path writePath = basePath.resolve(title);
        assertThat(reader.read(writePath).get().getText()).isEqualTo("hello writer");
    }
}
