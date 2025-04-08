package com.example.java7_4.entity.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPagedForumDataWithConditionReqDto {
    private int currentPage;
    private int pageSize;
    private String sortType;//like_desc/collect_desc/time_asc/time_desc/
}
