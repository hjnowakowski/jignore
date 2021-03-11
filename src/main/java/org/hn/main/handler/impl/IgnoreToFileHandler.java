package org.hn.main.handler.impl;

import org.hn.main.handler.CommandHandler;
import org.hn.main.service.IgnoreContentService;
import org.hn.main.service.impl.IOServiceImpl;
import org.hn.main.util.ValidationUtils;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class IgnoreToFileHandler implements CommandHandler {

    private static final Logger LOG = Logger.getLogger(IgnoreToFileHandler.class.getName());

    private final List<String> keywords;
    private final File ignoreFile;
    private final IgnoreContentService ignoreContentService;
    private final IOServiceImpl ioService;

    public IgnoreToFileHandler(List<String> keywords, String ignoreFile, IgnoreContentService ignoreContentService, IOServiceImpl ioService) {
        this.keywords = keywords
                .stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        this.ignoreFile = ValidationUtils.getFileObjFromPath(ignoreFile, ".gitignore");
        this.ignoreContentService = ignoreContentService;
        this.ioService = ioService;
    }

    @Override
    public void doHandle() {
        LOG.info("Handling gitignore command...");
        var ignoreContent = getIgnoreContent(this.keywords);
        this.ioService.appendToFile(this.ignoreFile, ignoreContent);
    }

    private String getIgnoreContent(List<String> keywords) {
        return this.ignoreContentService
                .getContent(keywords)
                .values()
                .stream()
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
