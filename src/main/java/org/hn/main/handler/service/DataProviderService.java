package org.hn.main.handler.service;

import org.hn.main.exception.InvalidInputException;
import org.hn.main.handler.service.dataprovider.DataProvider;
import org.hn.main.handler.service.dataprovider.IgnoreDTO;

public class DataProviderService {

    private final DataProvider dataProvider;
    private final String command;
    private final String keyword;

    public DataProviderService(DataProvider dataProvider, String command, String keyword) {
        this.dataProvider = dataProvider;
        this.command = command;
        this.keyword = keyword;
    }

    public String getIgnoreFile() {
        return dataProvider.getFilesToIgnore(keyword)
                .map(IgnoreDTO::getIgnoreFileContent)
                .orElseThrow(() -> new InvalidInputException("Couldn't get ignored files for specified keyword"));
    }
}
