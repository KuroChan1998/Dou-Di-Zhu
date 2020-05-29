package com.jzy.model;

import lombok.Getter;

/**
 * @ClassName CardValue
 * @Author JinZhiyun
 * @Description 扑克牌的值
 * @Date 2020/5/29 11:16
 * @Version 1.0
 **/
public enum CardValue {
    RED_JOKER("大王", 1),

    BLACK_JOKER("小王", 2),

    _2("2", 3),

    A("A", 4),

    K("K", 5),

    Q("Q", 6),

    J("J", 7),

    _10("10", 8),

    _9("9", 9),

    _8("8", 10),

    _7("7", 11),

    _6("6", 12),

    _5("5", 13),

    _4("4", 14),

    _3("3", 15);

    @Getter
    private String value;

    /**
     * 斗地主的牌大小权重2>A>K....>3
     */
    @Getter
    private int douDizhuWeight;

    CardValue(String value, int douDizhuWeight) {
        this.value = value;
        this.douDizhuWeight = douDizhuWeight;
    }


}
