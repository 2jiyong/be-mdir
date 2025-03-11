package org.content;

/**
 * 이 클래스는 메모장의 텍스트를 객체로 나타낸 것입니다.
 */
public class TextContent {
    private String text;

    public TextContent() {}

    public TextContent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
