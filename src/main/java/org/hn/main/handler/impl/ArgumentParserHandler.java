package org.hn.main.handler.impl;

import org.hn.main.ExecutionContext;
import org.hn.main.handler.Handler;

import java.io.File;

public class ArgumentParserHandler implements Handler {

    @Override
    public void doExecute() {
        if (!ExecutionContext.getGitIgnorePath().exists()) {
            throw new RuntimeException("Provided file does not exist: " + ExecutionContext.getGitIgnorePath());
        }
        ExecutionContext.getKeywords()
                .stream()
                .filter(k -> !k.equals(k.toLowerCase()))
                .forEach(v -> {
                    throw new RuntimeException("Keywords have to be lowercase, but found: " + v);
                });
    }
}
