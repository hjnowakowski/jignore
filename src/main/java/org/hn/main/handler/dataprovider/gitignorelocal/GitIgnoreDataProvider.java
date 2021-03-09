package org.hn.main.handler.dataprovider.gitignorelocal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.hn.main.JIgnore;
import org.hn.main.handler.dataprovider.DataProvider;

public class GitIgnoreDataProvider implements DataProvider {

    private static final Logger LOGGER = Logger.getLogger( GitIgnoreDataProvider.class.getName() );

    @Override
    public String getIgnoreContents(List<String> keywords) {
        var gson = new Gson();
        var reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream("gitignore.io.json")));
        Map<String, IgnoreDTO> ignoreDTOMap = gson.fromJson(reader, new TypeToken<Map<String, IgnoreDTO>>(){}.getType());
        return getIgnoreDTO(keywords, ignoreDTOMap);
    }

    private String getIgnoreDTO(final List<String> keywords, final Map<String, IgnoreDTO> map) {
        return keywords.stream()
                .peek(v -> {
                    if(!map.containsKey(v)) {
                        LOGGER.log(Level.WARNING, "Key " + v + " does not exist");
                    }
                })
                .map(map::get)
                .map(IgnoreDTO::getContents)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
