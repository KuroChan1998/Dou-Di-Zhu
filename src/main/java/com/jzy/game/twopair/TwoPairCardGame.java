package com.jzy.game.twopair;

import com.jzy.game.PlayCard;
import com.jzy.util.CardUtils;

/**
 * @ClassName TwoPairCardGame
 * @Author JinZhiyun
 * @Description 一副牌扑克游戏
 * @Date 2020/5/29 12:12
 * @Version 1.0
 **/
public interface TwoPairCardGame extends PlayCard {
    @Override
    default int getTotalCardsSize() {
        return CardUtils.TWO_PAIR.size();
    }
}
