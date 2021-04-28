package dev.park.e.bookcafemanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
public class Search {

    @Setter
    private String criteria;
    private String[] keyword;

    public Search(String criteria, String keyword) {
        if (keyword != null) {
            setCriteria(criteria);
            setKeyword(keyword);
        }
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword.split("[^0-9a-zA-Z가-힣ㄱ-ㅎ]+");
    }
}
