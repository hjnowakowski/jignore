package org.hn.main.service.dataprovider.gitignorelocal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.hn.main.exception.ResourceFileNotFoundException;
import org.hn.main.service.dataprovider.IgnoreDataProvider;

public class GitIgnoreDataProvider implements IgnoreDataProvider {

    private static final Logger LOGGER = Logger.getLogger(GitIgnoreDataProvider.class.getName());
    private final BufferedReader reader;
    private final Gson gson;

    public GitIgnoreDataProvider(String resourcePath) {
        var inputStreamWithResource = Optional
                .ofNullable(ClassLoader.getSystemClassLoader().getResourceAsStream(resourcePath))
                .orElseThrow(() -> new ResourceFileNotFoundException("File " + resourcePath + "not found in the resources"));
        this.reader = new BufferedReader
                (new InputStreamReader(inputStreamWithResource));
        this.gson = new Gson();
    }

    @Override
    public Map<String, String> getIgnoreContents(List<String> keywords) {
        Map<String, IgnoreDTO> ignoreDTOMap = gson.fromJson(this.reader, new TypeToken<Map<String, IgnoreDTO>>() {
        }.getType());
        return keywords.stream()
                .peek(v -> {
                    if (!ignoreDTOMap.containsKey(v)) {
                        LOGGER.log(Level.INFO, "Key " + v + " does not exist");
                    }
                })
                .map(ignoreDTOMap::get)
                .collect(Collectors.toMap(IgnoreDTO::getKey, IgnoreDTO::getContents));
    }
}
