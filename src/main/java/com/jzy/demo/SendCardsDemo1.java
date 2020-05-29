package com.jzy.demo;

import com.jzy.game.onepair.ThreePlayersOnePairDouDizhuGame;
import com.jzy.game.twopair.FourPlayersTwoPairsDouDizhuGame;

/**
 * @ClassName SendCardsDemo1
 * @Author JinZhiyun
 * @Description 一副牌三人斗地主发牌模拟示例
 * @Date 2020/5/29 12:49
 * @Version 1.0
 **/
public class SendCardsDemo1 {
    public static void main(String[] args) {
        System.out.println("==============一副牌三人斗地主发牌模拟==============");
        ThreePlayersOnePairDouDizhuGame game1 = new ThreePlayersOnePairDouDizhuGame();
        game1.sendCards();
        System.out.println(game1.showAllCards());
        System.out.println("player1叫地主后的牌: " + game1.callLord(1));
        System.out.println("player2叫地主后的牌: " + game1.callLord(2));
        System.out.println("player3叫地主后的牌: " + game1.callLord(3));
        System.out.println("====================================================");
    }
}
