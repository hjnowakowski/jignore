package org.hn.main;

import java.util.Optional;

public class ExecutionContext {
    private static String keyword;
    private static String gitIgnorePath;
    private static String currentDir;
    private static boolean isInitialized = false;

    private static Optional<String> gitIgnoreContent;

    private ExecutionContext() { }

    public static void setInstance(String keyword, String gitIgnorePath, String currentDir) {
        if (!isInitialized) {
            ExecutionContext.keyword = keyword.toLowerCase();
            ExecutionContext.gitIgnorePath = gitIgnorePath;
            ExecutionContext.currentDir = currentDir;
            ExecutionContext.isInitialized = true;
        } else {
            throw new RuntimeException("ExecutionContext already initialized!");
        }
    }

    public static void setIgnoreContent(String ignoreContent) {
        ExecutionContext.gitIgnoreContent = Optional.of(ignoreContent);
    }

    public static String getKeyword() {
        return keyword;
    }

    public static String getGitIgnorePath() {
        return gitIgnorePath;
    }

    public static String getCurrentDir() {
        return currentDir;
    }

    public static Optional<String> getGitIgnoreContent() {
        return gitIgnoreContent;
    }
}
