package org.hn.main.service.impl;

import org.hn.main.service.IOService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class IOServiceImpl implements IOService {

    private static final Logger LOGGER = Logger.getLogger(IOServiceImpl.class.getName());

    public void writeToFile(File file, String text) {
        try {
            Files.write(Paths.get(file.toURI()),
                    text.getBytes());
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
    }
}
