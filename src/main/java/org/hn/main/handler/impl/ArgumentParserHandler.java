package org.hn.main.handler.impl;

import org.hn.main.ExecutionContext;
import org.hn.main.handler.Handler;

import java.io.File;

public class ArgumentParserHandler implements Handler {

    @Override
    public void doExecute() {
        if (!new File(ExecutionContext.getGitIgnorePath()).isFile()) {
            throw new RuntimeException("Provided file does not exist");
        }
        var keyword = ExecutionContext.getKeyword();
        if (!keyword.equals(keyword.toLowerCase())) {
            throw new RuntimeException("Keyword has to be lowercase!, but is: " + keyword);
        }
    }
}
