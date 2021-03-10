package org.hn.main;

import org.hn.main.exception.ExecutionContextAlreadyInitializedException;
import org.hn.main.exception.ExecutionContextNotInitializedException;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExecutionContext {
    private static List<String> keywords;
    private static File gitIgnorePath;
    private static boolean isInitialized = false;

    private static String gitIgnoreContent;

    private ExecutionContext() { }

    public static void setInstance(final List<String> keywords, String gitIgnorePath) {
        if (!isInitialized) {
            ExecutionContext.keywords = keywords
                    .stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());
            if (gitIgnorePath.startsWith("~")) {
                gitIgnorePath = gitIgnorePath
                        .replaceFirst("^~", System.getProperty("user.home"));
            }
            if (new File(gitIgnorePath).isDirectory() && !gitIgnorePath.endsWith("/")) {
                gitIgnorePath += "/";
            }
            if (new File(gitIgnorePath).getName().equals(".gitignore")) {
                ExecutionContext.gitIgnorePath = new File(gitIgnorePath);
            } else if (new File(gitIgnorePath).isDirectory()) {
                ExecutionContext.gitIgnorePath = new File(gitIgnorePath + ".gitignore");
            } else {
                throw new RuntimeException("Provided file reference does not lead to .gitignore: " + gitIgnorePath);
            }
            ExecutionContext.isInitialized = true;
        } else {
            throw new ExecutionContextAlreadyInitializedException("ExecutionContext already initialized!");
        }
    }

    //TODO: delete
    public static void setIgnoreContent(String ignoreContent) {
        ExecutionContext.gitIgnoreContent = ignoreContent;
    }

    public static List<String> getKeywords() {
        if (!isInitialized) {
            throw new ExecutionContextNotInitializedException("Value not yet assigned");
        }
        return keywords;
    }

    public static File getGitIgnorePath() {
        if (!isInitialized) {
            throw new ExecutionContextNotInitializedException("Value not yet assigned");
        }
        return gitIgnorePath;
    }

    public static Optional<String> getGitIgnoreContent() {
        return Optional.ofNullable(gitIgnoreContent);
    }
}
