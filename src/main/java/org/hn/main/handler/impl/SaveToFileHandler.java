package org.hn.main.handler.impl;

import org.hn.main.ExecutionContext;
import org.hn.main.handler.Handler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SaveToFileHandler implements Handler {
    @Override
    public void doExecute() {
        var ignoreFileContent = ExecutionContext
                .getGitIgnoreContent()
                .orElseThrow(() -> new RuntimeException("No git ignore content found"));
        try {
            Files.write(Paths.get(ExecutionContext.getGitIgnorePath()),
                    ignoreFileContent.getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred when accessing gitignore file");
        }
    }
}
