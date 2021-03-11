package org.hn.main.service.dataprovider;

import java.util.List;
import java.util.Map;

/**
 * IgnoreDataProvider leverages a resource of choice to provide .gitignore data.
 */
public interface IgnoreDataProvider {
    Map<String, String> getIgnoreContents(List<String> keywords);
}
