package org.hn.main.handler;

import org.hn.main.exception.ResourceFileNotFoundException;
import org.hn.main.handler.impl.IgnoreToFileHandler;
import org.hn.main.service.dataprovider.gitignorelocal.GitIgnoreDataProvider;
import org.hn.main.service.impl.IgnoreContentServiceImpl;
import org.hn.main.service.impl.IOServiceImpl;

import java.util.List;
import java.util.Optional;

/**
 * Class with factory methods that return proper CommandHandler implementations.
 */
public class HandlerFactory {

    private HandlerFactory() {}

    public static CommandHandler getIgnoreToFileHandler(final List<String> keywords, final String directory) {
        var resourcePath = "gitignore.io.json";
        var inputStreamWithResource = Optional
                .ofNullable(ClassLoader.getSystemClassLoader().getResourceAsStream(resourcePath))
                .orElseThrow(() -> new ResourceFileNotFoundException("File " + resourcePath + "not found in the resources"));
        return new IgnoreToFileHandler(keywords,
                directory,
                new IgnoreContentServiceImpl(new GitIgnoreDataProvider(inputStreamWithResource)),
                new IOServiceImpl());
    }
}
