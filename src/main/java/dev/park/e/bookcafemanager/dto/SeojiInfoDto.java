package dev.park.e.bookcafemanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class SeojiInfoDto {

    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private Integer volume;

    @JsonProperty("docs")
    public void flattenNestedProperties(List<Map<String, Object>> docs) {
        Map<String, Object> seojiInfo = docs.get(0);
        this.isbn = (String) seojiInfo.get("EA_ISBN");
        this.title = (String) seojiInfo.get("TITLE");
        this.author = (String) seojiInfo.get("AUTHOR");
        this.publisher = (String) seojiInfo.get("PUBLISHER");
        if (!seojiInfo.get("VOL").equals("")) {
            this.volume = Integer.parseInt((String) seojiInfo.get("VOL"));
        }
    }
}
