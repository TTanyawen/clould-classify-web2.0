package com.example.java7_4.entity.Enum;

import com.example.java7_4.entity.CloudType;

public enum TagEnum {
    CUMULONIMBUS("CUMULONIMBUS", "积雨云"),
    CIRROCUMULUS("CIRROCUMULUS", "卷积云"),
    CIRROSTRATUS("CIRROSTRATUS", "卷层云"),
    CIRRUS("CIRRUS", "卷云"),
    ALTOCUMULUS("ALTOCUMULUS", "高积云"),
    ALTOSTRATUS("ALTOSTRATUS", "高层云"),
    NIMBOSTRATUS("NIMBOSTRATUS", "雨层云"),
    CUMULUS("CUMULUS", "积云"),
    STRATUS("STRATUS", "层云"),
    LIFE_SHARE("LIFE_SHARE", "生活分享"),
    CLOUD_SHARE("CLOUD_SHARE", "云彩分享"),
    ART("ART", "艺术作品"),
    PHOTOGRAPHY("PHOTOGRAPHY", "摄影"),
    FUN("FUN", "娱乐"),
    FOOD("FOOD", "美食");

    private final String code;
    private final String value;

    // 构造函数，用于初始化每个枚举常量的 code 和 value
    TagEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    // 获取 code 的方法
    public String getCode() {
        return code;
    }

    // 获取 value 的方法
    public String getValue() {
        return value;
    }

    // 根据 code 获取对应的枚举实例
    public static TagEnum getByCode(String code) {
        for (TagEnum tagEnum2 : values()) {
            if (tagEnum2.getCode() == code) {
                return tagEnum2;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // 示例：获取并打印某种云类型的 code 和 value
        TagEnum tagEnum = TagEnum.CUMULONIMBUS;
        System.out.println("Code: " + tagEnum.getCode() + ", Value: " + tagEnum.getValue());

        // 根据 code 获取枚举实例
        TagEnum tagEnum2 = getByCode("FUN");
        if (tagEnum2 != null) {
            System.out.println("Found tag: " + tagEnum2.getValue());
        } else {
            System.out.println("Tag not found for the given code.");
        }
    }
}
