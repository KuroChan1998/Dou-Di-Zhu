package com.jzy.demo;

import com.jzy.game.twopair.FourPlayersTwoPairsDouDizhuGame;

/**
 * @ClassName SendCardsDemo2
 * @Author JinZhiyun
 * @Description 两副牌四人斗地主发牌模拟
 * @Date 2020/5/29 17:55
 * @Version 1.0
 **/
public class SendCardsDemo2 {
    public static void main(String[] args) {
        System.out.println("==============两副牌四人斗地主发牌模拟==============");
        FourPlayersTwoPairsDouDizhuGame game2 = new FourPlayersTwoPairsDouDizhuGame();
        game2.sendCards();
        System.out.println(game2.showAllCards());
        System.out.println("player1叫地主后的牌: " + game2.callLord(1));
        System.out.println("player2叫地主后的牌: " + game2.callLord(2));
        System.out.println("player3叫地主后的牌: " + game2.callLord(3));
        System.out.println("player4叫地主后的牌: " + game2.callLord(4));
        System.out.println("====================================================");
    }
}
