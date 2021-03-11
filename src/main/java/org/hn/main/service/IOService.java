package org.hn.main.service;

import java.io.File;

/**
 * IOServices offer IO API
 */
public interface IOService {
    void appendToFile(File file, String text);
}
