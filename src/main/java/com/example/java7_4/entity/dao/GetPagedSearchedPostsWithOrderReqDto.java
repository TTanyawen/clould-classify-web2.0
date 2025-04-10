package com.example.java7_4.entity.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPagedSearchedPostsWithOrderReqDto {
    private int currentPage;
    private int pageSize;
    private String sortType;//like_desc/collect_desc/time_asc/time_desc/
    private String searchText;
    private String searchType;//content/username
    private LocalDateTime createBegin;
    private LocalDateTime createEnd;
}
