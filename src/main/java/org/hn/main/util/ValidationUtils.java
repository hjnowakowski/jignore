package org.hn.main.util;

import org.hn.main.exception.PathParsingException;

import java.io.File;
import java.util.HashSet;
import java.util.List;

public class ValidationUtils {

    private ValidationUtils() { }

    /**
     * Returns a reference to the file.
     * If path does not point to a file it will try to append optionalFilename.
     *
     * @param path to a directory or a file
     * @param optionalFilename to be appended if path points to directory
     * @return reference to a file
     */
    public static File getFileObjFromPath(String path, final String optionalFilename) {
        if (path.startsWith("~")) {
            path = path
                    .replaceFirst("^~", System.getProperty("user.home"));
        }
        if (new File(path).getName().equals(optionalFilename)) {
            return new File(path);
        }
        if (new File(path).isDirectory() && !path.endsWith("/")) {
            return new File(path + "/" + optionalFilename);
        }
        if (new File(path).isDirectory() && path.endsWith("/")) {
            return new File(path + optionalFilename);
        }
        throw new PathParsingException("Could not resolve a File object of " + path + " and file " + optionalFilename);
    }

    /**
     * Is true when all list members are unique
     * @param list
     * @return
     */
    public static <T> boolean areAllUnique(List<T> list){
        return list.stream().allMatch(new HashSet<>()::add);
    }
}
