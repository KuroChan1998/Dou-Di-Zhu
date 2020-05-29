package com.jzy.model;

import lombok.Data;

import java.util.Comparator;

/**
 * @ClassName Card
 * @Author JinZhiyun
 * @Description 一张牌
 * @Date 2020/5/29 9:40
 * @Version 1.0
 **/
@Data
public class Card implements Comparable<Card> {
    /**
     * 大王
     */
    public static final Card RED_JOKER = new Card(CardValue.RED_JOKER);
    /**
     * 小王
     */
    public static final Card BLACK_JOKER = new Card(CardValue.BLACK_JOKER);

    /**
     * 默认扑克牌比较器，仅比较值大小，如：
     * 方块K排在黑桃Q前
     * 方块K与黑桃K相等
     */
    public static final Comparator<Card> DEFAULT_COMPARATOR = new Comparator<Card>() {
        @Override
        public int compare(Card c1, Card c2) {
            if (c1.getValue() != null && c2.getValue() != null) {
                return c1.getValue().getDouDizhuWeight() - c2.getValue().getDouDizhuWeight();
            }
            return Integer.MAX_VALUE;
        }
    };


    /**
     * 根据值和花色综合比较大小，如：
     * 方块K排在黑桃Q前
     * 黑桃K排在方块K前（黑桃>红桃>草花>方片）
     */
    public static final Comparator<Card> COLOR_VALUE_COMPARATOR = new Comparator<Card>() {
        @Override
        public int compare(Card c1, Card c2) {
            int valueCompareResult = DEFAULT_COMPARATOR.compare(c1, c2);
            if (valueCompareResult != 0) {
                return valueCompareResult;
            }
            //牌面值相等
            if (c1.getColor() != null && c2.getColor() != null) {
                return c1.getColor().getWeight() - c2.getColor().getWeight();
            }

            return Integer.MAX_VALUE;
        }
    };


    /**
     * 牌的花色
     */
    private CardColor color;

    /**
     * 牌的值
     */
    private CardValue value;

    public Card() {
    }

    public Card(CardValue value) {
        this.value = value;
    }

    public Card(CardColor color, CardValue value) {
        this.color = color;
        this.value = value;
    }

    @Override
    public String toString() {
        String s = "";
        if (color != null) {
            s += color.getColor();
        }
        if (value != null) {
            s += value.getValue();
        }
        return s;
    }

    /**
     * 两张牌成员方法比较使用默认比较器的比较策略，即仅比较大小
     *
     * @param c
     * @return
     */
    @Override
    public int compareTo(Card c) {
        return DEFAULT_COMPARATOR.compare(this, c);
    }
}
