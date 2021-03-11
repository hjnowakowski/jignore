package org.hn.main;

import org.hn.main.exception.InvalidInputException;
import org.hn.main.handler.HandlerFactory;
import picocli.CommandLine;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

public class JIgnore implements Callable<Integer> {

    private static final Logger LOG = Logger.getLogger(JIgnore.class.getName());

    @CommandLine.Option(
            names = {"-d", "--directory"},
            description = "Directory with the git repository")
    private String projectDirectory = null;

    @CommandLine.Parameters(index = "0..*", description = "List of keywords determining .gitignore contents.")
    private List<String> keywordList;

    public static void main(String[] args) {
        LOG.info("Running JIgnore...");
        int exitCode = new CommandLine(new JIgnore()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        var directory = Optional.ofNullable(projectDirectory)
                // if null get the dir where script was called
                .orElse(System.getProperty("user.dir"));
        var keywords = Optional.ofNullable(keywordList)
                .orElseThrow(() -> new InvalidInputException("Keyword list is empty"));
        try {
            HandlerFactory.getIgnoreToFileHandler(keywords, directory).doHandle();
        } catch (Exception e) {
            return -1;
        }
        return 0;
    }
}
