package org.content;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TextContentTest {

    @Test
    @DisplayName("String을 받아 텍스트 객체를 생성하고 읽을 수 있다.")
    void 기본_텍스트_객체_테스트() {
        TextContent textContent = new TextContent("hello world");
        assertThat(textContent.getText()).isEqualTo("hello world");
    }

    @Test
    @DisplayName("기본 생성자로 텍스트 객체를 생성하고 내용을 변경할 수 있다.")
    void 텍스트_생성자_테스트(){
        TextContent textContent = new TextContent();
        textContent.setText("hello world!!");
        assertThat(textContent.getText()).isEqualTo("hello world!!");
    }

}
