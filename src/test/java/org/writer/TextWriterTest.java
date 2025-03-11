package org.writer;

import org.content.TextContent;
import org.junit.jupiter.api.*;
import org.reader.TextReader;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

public class TextWriterTest {
    TextReader reader = new TextReader();

    @Test
    @DisplayName("TextWriter가 해당 경로에 TextContent의 내용을 담은 파일을 만들 수 있다.")
    void testTextWriter() {
        String path = "files";
        String title = "writer";
        TextContent textContent = new TextContent("hello writer");
        TextWriter textWriter = new TextWriter(path);
        textWriter.write(title,textContent);
        assertThat(reader.read(path+"/"+title+".txt").get().getText()).isEqualTo("hello writer");
    }
}
