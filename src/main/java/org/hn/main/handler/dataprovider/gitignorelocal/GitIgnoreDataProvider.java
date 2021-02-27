package org.hn.main.handler.dataprovider.gitignorelocal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Optional;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.hn.main.handler.dataprovider.DataProvider;

public class GitIgnoreDataProvider implements DataProvider {

    @Override
    public String getIgnoreContents(String keyword) {
        var gson = new Gson();
        var reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream("gitignore.io.json")));
        Map<String, IgnoreDTO> b = gson.fromJson(reader, new TypeToken<Map<String, IgnoreDTO>>(){}.getType());
        return getIgnoreDTO(keyword, b);
    }

    private String getIgnoreDTO(String keyword, Map<String, IgnoreDTO> map) {
        return Optional
                .of(map.get(keyword))
                .map(IgnoreDTO::getContents)
                .orElseThrow(() -> new RuntimeException("Key wasn't find"));
    }

}
