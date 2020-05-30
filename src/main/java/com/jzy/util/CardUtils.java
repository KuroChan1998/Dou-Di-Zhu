package com.jzy.util;

import com.jzy.game.onepair.ThreePlayersOnePairDouDizhuGame;
import com.jzy.game.twopair.FourPlayersTwoPairsDouDizhuGame;
import com.jzy.model.Card;
import com.jzy.model.CardColor;
import com.jzy.model.CardValue;

import java.util.*;

/**
 * @ClassName CardUtils
 * @Author JinZhiyun
 * @Description 扑克牌工具类
 * @Date 2020/5/29 10:00
 * @Version 1.0
 **/
public class CardUtils {
    /**
     * 扑克牌面值的所有集合
     */
    public static final List<CardValue> VALUES = Arrays.asList(CardValue._3, CardValue._4, CardValue._5, CardValue._6
            , CardValue._7, CardValue._8, CardValue._9, CardValue._10, CardValue.J, CardValue.Q, CardValue.K, CardValue.A, CardValue._2);

    public static final int ONE_PAIR_SIZE = 54;
    /**
     * 一副牌原型
     */
    public static final Map<Integer, Card> ONE_PAIR = new HashMap<>(ONE_PAIR_SIZE);

    static {
        //生成一副牌的原型
        for (int i = 0; i < 13; i++) {
            CardValue value = VALUES.get(i);
            //每张牌的四种花色
            ONE_PAIR.put(4 * i, new Card(CardColor.SPADE, value));
            ONE_PAIR.put(4 * i + 1, new Card(CardColor.HEART, value));
            ONE_PAIR.put(4 * i + 2, new Card(CardColor.CLUB, value));
            ONE_PAIR.put(4 * i + 3, new Card(CardColor.DIAMOND, value));
        }
        ONE_PAIR.put(52, Card.BLACK_JOKER);
        ONE_PAIR.put(53, Card.RED_JOKER);
    }

    /**
     * 两副牌原型
     */
    public static final Map<Integer, Card> TWO_PAIR = new HashMap<>(ONE_PAIR_SIZE * 2);

    static {
        //生成两副牌的原型
        for (int i = 0; i < 13; i++) {
            CardValue value = VALUES.get(i);
            //每张牌的四种花色
            TWO_PAIR.put(8 * i, new Card(CardColor.SPADE, value));
            TWO_PAIR.put(8 * i + 1, new Card(CardColor.SPADE, value));
            TWO_PAIR.put(8 * i + 2, new Card(CardColor.HEART, value));
            TWO_PAIR.put(8 * i + 3, new Card(CardColor.HEART, value));
            TWO_PAIR.put(8 * i + 4, new Card(CardColor.CLUB, value));
            TWO_PAIR.put(8 * i + 5, new Card(CardColor.CLUB, value));
            TWO_PAIR.put(8 * i + 6, new Card(CardColor.DIAMOND, value));
            TWO_PAIR.put(8 * i + 7, new Card(CardColor.DIAMOND, value));
        }
        TWO_PAIR.put(104, Card.BLACK_JOKER);
        TWO_PAIR.put(105, Card.BLACK_JOKER);
        TWO_PAIR.put(106, Card.RED_JOKER);
        TWO_PAIR.put(107, Card.RED_JOKER);
    }

    /**
     * 判断输入的牌中是否含大小王，总的牌范围为一副牌
     *
     * @param cards  输入的牌
     * @param sorted 输入的牌是否已排序
     * @return 是否含大小王
     */
    public static boolean hasDoubleJokersWithinOnePair(List<Card> cards, boolean sorted) {
        if (cards == null || cards.size() < 2) {
            return false;
        }
        if (sorted) {
            //已排序，判断第1、2张即可
            return cards.get(0).equals(Card.RED_JOKER) && cards.get(1).equals(Card.BLACK_JOKER);
        } else {
            //未排序
            return cards.contains(Card.BLACK_JOKER) && cards.contains(Card.RED_JOKER);
        }
    }

    /**
     * 判断输入的牌中是否含四张王，即天王炸，总的牌范围为两副牌
     *
     * @param cards  输入的牌
     * @param sorted 输入的牌是否已排序
     * @return 是否含四张王，即天王炸
     */
    public static boolean hasFourJokersWithinTwoPairs(List<Card> cards, boolean sorted) {
        if (cards == null || cards.size() < 4) {
            return false;
        }
        if (sorted) {
            //已排序，判断第1、2、3、4张即可
            return cards.get(0).equals(Card.RED_JOKER) && cards.get(1).equals(Card.RED_JOKER)
                    && cards.get(2).equals(Card.BLACK_JOKER) && cards.get(3).equals(Card.BLACK_JOKER);
        } else {
            //未排序
            Map<Card, Integer> map = new HashMap<>(2);
            map.put(Card.RED_JOKER, 0);
            map.put(Card.BLACK_JOKER, 0);
            for (Card card : cards) {
                if (Card.RED_JOKER.equals(card)) {
                    int count = map.get(Card.RED_JOKER) + 1;
                    map.put(Card.RED_JOKER, count);
                }
                if (Card.BLACK_JOKER.equals(card)) {
                    int count = map.get(Card.BLACK_JOKER) + 1;
                    map.put(Card.BLACK_JOKER, count);
                }
            }
            return map.get(Card.RED_JOKER) == 2 && map.get(Card.BLACK_JOKER) == 2;
        }
    }

    /**
     * 判断输入的牌中是否至少有一个四星炸弹，总的牌范围为一副牌
     *
     * @param cards  输入的牌
     * @param sorted 输入的牌是否已排序
     * @return 是否至少有一个四星炸弹
     */
    public static boolean hasBombExceptDoubleJokersWithinOnePair(List<Card> cards, boolean sorted) {
        if (cards == null || cards.size() < 4) {
            return false;
        }
        if (!sorted) {
            //这里检测炸弹仅对面值排序即可，无需对花色排序
            Collections.sort(cards);
        }
        int bombSize = 4;

        int shift = 1;
        for (int i = 0; i <= cards.size() - bombSize; i += shift) {
            Card card0 = cards.get(i);
            int repeatTimes = 1;
            for (int j = i + 1; j < i + bombSize; j++) {
                //往后看3张牌
                if (!card0.equalsValue(cards.get(j))) {
                    //不相等，下次检查的指针后移j-i，即shift值为j-i;
                    shift = j - i;
                    break;
                } else {
                    //相等
                    repeatTimes++;
                }
            }
            if (repeatTimes == bombSize) {
                //出现炸弹
                return true;
            }
        }
        return false;
    }

    /**
     * 判断输入的牌中是否至少有一个n星以上炸弹，总的牌范围为两副牌
     *
     * @param cards     输入的牌
     * @param sorted    输入的牌是否已排序
     * @param bombLevel 炸弹星级n
     * @return 是否至少有一个n星以上炸弹
     */
    public static boolean hasBombExceptDoubleJokersWithinTwoPairs(List<Card> cards, boolean sorted, int bombLevel) {
        if (!(bombLevel >= 4 && bombLevel <= 8)) {
            return false;
        }
        if (cards == null || cards.size() < bombLevel) {
            return false;
        }
        if (!sorted) {
            //这里检测炸弹仅对面值排序即可，无需对花色排序
            Collections.sort(cards);
        }

        int shift = 1;
        for (int i = 0; i <= cards.size() - bombLevel; i += shift) {
            Card card0 = cards.get(i);
            int repeatTimes = 1;
            for (int j = i + 1; j < i + bombLevel; j++) {
                if (!card0.equalsValue(cards.get(j))) {
                    //不相等，下次检查的指针后移j-i，即shift值为j-i;
                    shift = j - i;
                    break;
                } else {
                    //相等
                    repeatTimes++;
                }
            }
            if (repeatTimes == bombLevel) {
                //出现炸弹
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int level = 5;

        System.out.println(hasBombExceptDoubleJokersWithinTwoPairs(Arrays.asList(
                new Card(CardValue.Q), new Card(CardValue.Q), new Card(CardValue.Q), new Card(CardValue.Q), new Card(CardValue.Q),new Card(CardValue.K), new Card(CardValue.K), new Card(CardValue.K)), true, level));
    }
}
