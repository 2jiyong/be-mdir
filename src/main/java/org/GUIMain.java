package org;

import org.reader.TextReader;
import org.view.NoteView;
import org.writer.TextWriter;

import java.nio.file.Path;
import java.nio.file.Paths;

public class GUIMain {
    public static void main(String[] args) {
        Path basePath = Paths.get("files");
        TextReader reader = new TextReader(basePath);
        TextWriter writer = new TextWriter(basePath);

        NoteView noteView = new NoteView(reader,writer);
    }
}
