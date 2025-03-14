package org.writer;

import org.content.TextContent;
import org.junit.jupiter.api.*;
import org.reader.TextReader;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.*;

public class TextWriterTest {
    Path basePath = Paths.get("files");
    TextReader reader = new TextReader(basePath);
    TextWriter writer = new TextWriter(basePath);

    @Test
    @DisplayName("TextWriter가 해당 경로에 TextContent의 내용을 담은 파일을 만들 수 있다.")
    void testTextWriter() {
        String title = "writer.txt";
        TextContent textContent = new TextContent("hello writer");
        writer.write(Paths.get(title),textContent);
        Path writePath = Paths.get(title);
        assertThat(reader.read(writePath).get().getText()).isEqualTo("hello writer");
    }
}
