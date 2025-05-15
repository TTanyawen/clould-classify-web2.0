package com.example.java7_4.entity.bo;


import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
public class CloudTypeBO {
    @ExcelProperty("typeId")
    private Long typeId;

    @ExcelProperty("typeName")
    private String typeName;

    @ExcelProperty("typeNameEn")
    private String typeNameEn;

    @ExcelProperty("typeInfo")
    private String typeInfo;

    @ExcelProperty("imgMax")
    private Long imgMax;
}
