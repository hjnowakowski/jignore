package org.hn.main.handler.dataprovider.gitignorelocal;

import com.google.gson.annotations.SerializedName;

public class IgnoreDTO {

    @SerializedName("key")
    protected String key;
    @SerializedName("contents")
    protected String contents;

    public void setKeyword(String key) {
        this.key = key;
    }

    public void setIgnoreFileContent(String contents) {
        this.contents = contents;
    }

    public String getKey() {
        return key;
    }

    public String getContents() {
        return contents;
    }

    public IgnoreDTO(String key, String contents) {
        this.key = key;
        this.contents = contents;
    }

    public IgnoreDTO() {
    }
}
