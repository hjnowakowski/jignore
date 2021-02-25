package org.hn.main;

import java.util.Optional;

public class ExecutionContext {
    private static Operation operation;
    private static String keyword;
    private static String gitIgnorePath;
    private static boolean isInitialized = false;

    private static Optional<String> gitIgnoreContent;

    private ExecutionContext() { }

    public static void setInstance(String operation, String keyword, String gitIgnorePath) {
        if (!isInitialized) {
            ExecutionContext.operation = Operation.GENERATE;
            ExecutionContext.keyword = keyword.toLowerCase();
            ExecutionContext.gitIgnorePath = gitIgnorePath;
            ExecutionContext.isInitialized = true;
        } else {
            throw new RuntimeException("ExecutionContext already initialized!");
        }
    }

    public static void setIgnoreContent(String ignoreContent) {
        ExecutionContext.gitIgnoreContent = Optional.of(ignoreContent);
    }

    public static Operation getOperation() {
        return operation;
    }

    public static String getKeyword() {
        return keyword;
    }

    public static String getGitIgnorePath() {
        return gitIgnorePath;
    }

    public static Optional<String> getGitIgnoreContent() {
        return gitIgnoreContent;
    }
}

enum Operation {
    GENERATE
}
