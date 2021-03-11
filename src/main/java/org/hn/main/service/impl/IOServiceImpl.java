package org.hn.main.service.impl;

import org.hn.main.service.IOService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class IOServiceImpl implements IOService {

    public void appendToFile(File file, String text) {
        try {
            Files.write(Paths.get(file.toURI()),
                    text.getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred when accessing " + file + " file");
        }
    }

}
