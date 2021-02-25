package org.hn.main.handler.impl;

import org.hn.main.ExecutionContext;
import org.hn.main.exception.InvalidInputException;
import org.hn.main.handler.Handler;
import org.hn.main.handler.service.DataProviderService;
import org.hn.main.handler.service.dataprovider.gitignorelocal.GitIgnoreDataProvider;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GetIgnoreContentHandler implements Handler {

    private static final Logger LOGGER = Logger.getLogger( GetIgnoreContentHandler.class.getName() );

    @Override
    public void doExecute() {
        DataProviderService s = null;
        try {
            s = new DataProviderService(new GitIgnoreDataProvider(), "generate", ExecutionContext.getKeyword());
            ExecutionContext.setIgnoreContent(s.getIgnoreFile());
        } catch (InvalidInputException e) {
            LOGGER.log(Level.INFO, "Error occurred when fetching ignore data", e);
        }
    }
}
