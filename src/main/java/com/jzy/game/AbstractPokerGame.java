package com.jzy.game;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName AbstractPokerGame
 * @Author JinZhiyun
 * @Description 扑克游戏抽象类
 * @Date 2020/5/29 10:29
 * @Version 1.0
 **/
public abstract class AbstractPokerGame implements PlayCard {
    @Getter
    protected ArrayList<Integer> indexesForShuffleCards;

    /**
     * 输出显示所有分发完成的牌
     */
    public abstract String showAllCards();

    @Override
    public void shuffleCards() {
        indexesForShuffleCards = new ArrayList<>();
        //洗牌准备：比如，一副54张的牌 ArrayList里边为0-53的数的新牌
        int total= getTotalCardsSize();
        for (int i = 0; i < total; i++) {
            indexesForShuffleCards.add(i);
        }
        //洗牌
        Collections.shuffle(indexesForShuffleCards);
    }
}
