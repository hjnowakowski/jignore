package org.hn.main.handler.dataprovider;

import java.util.List;

public interface DataProvider {
    String getIgnoreContents(List<String> keywords);
}
