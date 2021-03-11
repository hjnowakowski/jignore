package org.hn.main.service.impl;

import org.hn.main.service.IgnoreContentService;
import org.hn.main.service.dataprovider.IgnoreDataProvider;

import java.util.List;
import java.util.Map;

public class IgnoreContentServiceImpl implements IgnoreContentService {

    private final IgnoreDataProvider dataProvider;

    public IgnoreContentServiceImpl(IgnoreDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public Map<String, String> getContent(List<String> keywords) {
        return this.dataProvider.getIgnoreContents(keywords);
    }
}
