package org.hn.main;

import org.hn.main.handler.Handler;
import org.hn.main.handler.impl.GetIgnoreContentHandler;
import org.hn.main.handler.impl.ArgumentParserHandler;
import org.hn.main.handler.impl.SaveToFileHandler;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JIgnore  {

    private static final Logger LOGGER = Logger.getLogger( JIgnore.class.getName() );

    public static void main(String[] args) {
        LOGGER.info("Running JIgnore...");
        JIgnore.run(args);
        System.exit(0);
    }

    public static void run(String[] args) {
        try {
            ExecutionContext.setInstance(args[0], args[1], System.getProperty("user.dir"));
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
    }
}
