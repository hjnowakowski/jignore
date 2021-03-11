package org.hn.main.service.dataprovider.gitignorelocal;

import com.google.gson.annotations.SerializedName;

public class IgnoreDTO {

    @SerializedName("key")
    private String key;
    @SerializedName("contents")
    private String contents;

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
