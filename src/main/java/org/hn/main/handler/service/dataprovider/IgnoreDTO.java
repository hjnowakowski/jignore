package org.hn.main.handler.service.dataprovider;

import java.io.Serializable;

public class IgnoreDTO implements Serializable {

    protected String keyword;
    protected String ignoreFileContent;

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setIgnoreFileContent(String ignoreFileContent) {
        this.ignoreFileContent = ignoreFileContent;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getIgnoreFileContent() {
        return ignoreFileContent;
    }

    public IgnoreDTO(String keyword, String ignoreFileContent) {
        this.keyword = keyword;
        this.ignoreFileContent = ignoreFileContent;
    }

    public IgnoreDTO() {
    }
}
