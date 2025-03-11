package org.reader;

import org.content.TextContent;
import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

public class TextReaderTest {
    public TextReader textReader;

    @BeforeEach
    void setUp() {
        textReader = new TextReader();
    }

    @Test
    @DisplayName("TextReader가 텍스트 파일의 내용으로 텍스트 객체를 생성할 수 있다.")
    void textReader_read_text_file(){
        String path = "hello.txt";
        Optional<TextContent> textContent = textReader.read(path);
        assertThat(textContent).isPresent();
        assertThat(textContent.get().getText()).isEqualTo("hello world");
    }

    @Test
    @DisplayName("TextReader가 잘못된 path를 받으면 Optional empty를 반환한다.")
    void textReader_read_text_file_wrong_path(){
        String wrongPath = "hell.txt";
        assertThat(textReader.read(wrongPath)).isEqualTo(Optional.empty());
    }
}
