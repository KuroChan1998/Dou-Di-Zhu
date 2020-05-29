package com.jzy.game;

import com.jzy.model.Card;

import java.util.List;

/**
 * @InterfaceName DouDizhu
 * @Author JinZhiyun
 * @Description 斗地主的行为
 * @Date 2020/5/29 12:04
 * @Version 1.0
 **/
public interface DouDizhu extends PlayCard {
    /**
     * 叫地主，输入玩家的手牌
     */
    List<Card> callLord(List<Card> player);

    /**
     * 叫地主，输入玩家的编号
     */
    List<Card> callLord(int playerIndex);

    /**
     * 输出显示个玩家的牌
     */
    String showPlayerCards();
}
