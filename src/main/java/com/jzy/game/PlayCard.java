package com.jzy.game;

/**
 * @InterfaceName PlayCard
 * @Author JinZhiyun
 * @Description 扑克游戏的行为
 * @Date 2020/5/29 10:25
 * @Version 1.0
 **/
public interface PlayCard {
    /**
     * 返回游戏牌的总数
     *
     * @return
     */
    int getTotalCardsSize();

    /**
     * 洗牌
     */
    void shuffleCards();

    /**
     * 从原型牌初始化底牌，及向各玩家分发初始手牌
     */
    void sendCards();
}
