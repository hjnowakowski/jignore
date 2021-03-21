package org.hn.main.service;

import java.io.File;

/**
 * IOServices offer IO API
 */
public interface IOService {
    void writeToFile(File file, String text);
}
