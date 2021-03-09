package org.hn.main.handler.impl;

import org.hn.main.ExecutionContext;
import org.hn.main.handler.Handler;
import org.hn.main.handler.dataprovider.gitignorelocal.GitIgnoreDataProvider;

public class GetIgnoreContentHandler implements Handler {

    @Override
    public void doExecute() {
        ExecutionContext.setIgnoreContent(new GitIgnoreDataProvider().getIgnoreContents(ExecutionContext.getKeywords()));
    }
}
