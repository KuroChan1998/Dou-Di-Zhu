package com.jzy.game;

import com.jzy.model.Card;
import lombok.Getter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName AbstractDouDizhuGame
 * @Author JinZhiyun
 * @Description 斗地主的抽象类
 * @Date 2020/5/29 12:06
 * @Version 1.0
 **/
public abstract class AbstractDouDizhuGame extends AbstractPokerGame implements DouDizhu {
    /**
     * 底牌
     */
    @Getter
    protected LinkedList<Card> holeCards;

    /**
     * 地主牌
     */
    @Getter
    protected LinkedList<Card> lordCards;

    @Override
    public String showAllCards() {
        String s= showPlayerCards();
        s+=("底牌: " + holeCards);
        return s;
    }

    @Override
    public List<Card> callLord(List<Card> player) {
        lordCards=new LinkedList<>();
        lordCards.addAll(player);
        lordCards.addAll(holeCards);
        Collections.sort(lordCards, Card.COLOR_VALUE_COMPARATOR);
        return lordCards;
    }
}
