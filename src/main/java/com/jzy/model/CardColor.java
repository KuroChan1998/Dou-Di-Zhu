package com.jzy.model;

import lombok.Getter;

/**
 * 扑克牌花色枚举
 */
public enum CardColor {
    /**
     * 黑桃
     */
    SPADE("♠", 1),

    /**
     * 红桃
     */
    HEART("♥", 2),

    /**
     * 方片
     */
    CLUB("♦", 3),

    /**
     * 草花
     */
    DIAMOND("♣", 4);

    @Getter
    private String color;

    @Getter
    private int weight;

    CardColor(String color, int weight) {
        this.color=color;
        this.weight=weight;
    }
}
