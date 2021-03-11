package org.hn.main.service;

import java.util.List;
import java.util.Map;

/**
 * IgnoreContentServices are to get .gitignore content
 */
public interface IgnoreContentService {
    Map<String, String> getContent(List<String> keywords);
}
