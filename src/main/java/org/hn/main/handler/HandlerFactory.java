package org.hn.main.handler;

import org.hn.main.handler.impl.IgnoreToFileHandler;
import org.hn.main.service.dataprovider.gitignorelocal.GitIgnoreDataProvider;
import org.hn.main.service.impl.IgnoreContentServiceImpl;
import org.hn.main.service.impl.IOServiceImpl;

import java.util.List;

/**
 * Class with factory methods that return proper CommandHandler implementations.
 */
public class HandlerFactory {
    public static CommandHandler getIgnoreToFileHandler(final List<String> keywords, final String directory) {
        return new IgnoreToFileHandler(keywords,
                directory,
                new IgnoreContentServiceImpl(new GitIgnoreDataProvider("gitignore.io.json")),
                new IOServiceImpl());
    }
}
