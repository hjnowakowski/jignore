package org.hn.main;

import org.hn.main.handler.Handler;
import org.hn.main.handler.impl.GetIgnoreContentHandler;
import org.hn.main.handler.impl.ArgumentParserHandler;
import org.hn.main.handler.impl.SaveToFileHandler;
import picocli.CommandLine;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JIgnore implements Callable<Integer> {

    private static final Logger LOGGER = Logger.getLogger( JIgnore.class.getName() );

    @CommandLine.Option(
            names = {"-d", "--directory"},
            description = "Directory with the git repository")
    private String projectDirectory = null;

    @CommandLine.Parameters(index = "0..*", description = "List of keywords determining .gitignore contents.")
    private List<String> keywordList;

    public static void main(String[] args) {
        LOGGER.info("Running JIgnore...");
        int exitCode = new CommandLine(new JIgnore()).execute(args);
        System.exit(0);
    }

    @Override
    public Integer call() throws Exception {
        var directory = Optional.ofNullable(projectDirectory)
                .orElse(System.getProperty("user.dir"));
        var keywords = Optional.ofNullable(keywordList)
                .orElseThrow(() -> new RuntimeException("Keyword list is empty"));
        try {
            LOGGER.info(keywords.toString());
            LOGGER.info(directory);
            ExecutionContext.setInstance(keywords, directory);
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.log(Level.SEVERE, "Bad args provided");
            e.printStackTrace();
        }

        try {
            for (Handler h : List.of(
                    new ArgumentParserHandler(),
                    new GetIgnoreContentHandler(),
                    new SaveToFileHandler()
            )) {
                h.doExecute();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred when running steps", e);
        }

        return 0;
    }
}
