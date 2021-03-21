package org.hn.main.service.dataprovider.gitignorelocal;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.hn.main.service.dataprovider.IgnoreDataProvider;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class GitIgnoreDataProvider implements IgnoreDataProvider {

    private static final Logger LOGGER = Logger.getLogger(GitIgnoreDataProvider.class.getName());
    private final BufferedReader reader;
    private final Gson gson;

    public GitIgnoreDataProvider(InputStream inputStreamWithResource) {
        this.reader = new BufferedReader
                (new InputStreamReader(inputStreamWithResource));
        this.gson = new Gson();
    }

    //TODO: Rewrite these methods for better performance
    //Consider streaming instead of loading whole file (https://sites.google.com/site/gson/streaming)
    @Override
    public Map<String, String> getIgnoreContents(final List<String> requestedKeywords) {
        Map<String, IgnoreDTO> ignoreDTOMap = gson.fromJson(this.reader, new TypeToken<Map<String, IgnoreDTO>>() {
        }.getType());
        logNonExistingKey(ignoreDTOMap, requestedKeywords);
        return requestedKeywords.stream()
                .distinct()
                .map(ignoreDTOMap::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(IgnoreDTO::getKey, IgnoreDTO::getContents));
    }

    private void logNonExistingKey(final Map<String, ?> map, final List<String> keys) {
        keys.stream()
                .filter(k -> !map.containsKey(k))
                .forEach(nonExistingKey ->
                        LOGGER.info(String.format("Key %s wasn't found!", nonExistingKey)));
    }
}
