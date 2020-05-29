package com.jzy.game.onepair;

import com.jzy.game.AbstractPokerGame;
import com.jzy.game.PlayCard;
import com.jzy.util.CardUtils;

/**
 * @ClassName AbstractOnePairGame
 * @Author JinZhiyun
 * @Description 一副牌扑克游戏
 * @Date 2020/5/29 12:12
 * @Version 1.0
 **/
public interface OnePairCardGame extends PlayCard {
    @Override
    default int getTotalCardsSize() {
        return CardUtils.ONE_PAIR_SIZE;
    }
}
