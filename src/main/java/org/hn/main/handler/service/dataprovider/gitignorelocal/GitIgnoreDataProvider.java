package org.hn.main.handler.service.dataprovider.gitignorelocal;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hn.main.handler.service.dataprovider.DataProvider;
import org.hn.main.handler.service.dataprovider.IgnoreDTO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GitIgnoreDataProvider implements DataProvider {
    private final String URL = "https://www.toptal.com/developers/gitignore/api/kotlin";

    private static final Logger LOGGER = Logger.getLogger( GitIgnoreDataProvider.class.getName() );

    @Override
    public Optional<IgnoreDTO> getFilesToIgnore(String keyword) {
        var objectMapper = new ObjectMapper();
        Map<String, GitIgnoreDTO> a = null;
        TypeReference<HashMap<String, GitIgnoreDTO>> typeRef = new TypeReference<>() {};
        try {
            a = objectMapper.readValue(ClassLoader.getSystemClassLoader().getResourceAsStream("gitignore.io.json"), typeRef);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error parsing json from the resources", e);
            throw new RuntimeException("Error parsing json from the resources", e);
        }
        return Optional.of(getIgnoreDTO(keyword, a));
    }

    private GitIgnoreDTO getIgnoreDTO(String keyword, Map<String, GitIgnoreDTO> map) {
        return Optional
                .of(map.get(keyword))
                .orElseThrow(() -> new RuntimeException("Key wasn't find"));
    }
}
