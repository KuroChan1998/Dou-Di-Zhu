package com.jzy.demo;

import com.jzy.game.twopair.FourPlayersTwoPairsDouDizhuGame;
import com.jzy.model.Card;
import com.jzy.util.CardUtils;
import com.jzy.util.FileUtils;
import com.jzy.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName ProbabilityTestDemo1
 * @Author JinZhiyun
 * @Description 两副牌四人斗地主六星及以上炸概率实验
 * @Date 2020/5/29 12:54
 * @Version 1.0
 **/
public class ProbabilityTestDemo4 extends BaseProbabilityTest {
    public static void main(String[] args) {
        //实验次数
        int testTimes = 1000000;
        //实验结果明细输出文件路径
        String saveFilePath = "E:\\Engineering\\java\\idea\\doudizhu\\output_example\\实验结果_两副牌四人斗地主六星及以上炸概率.txt";
        //炸弹级别
//        int bombLevel6 = 6;
//        ProbabilityTestDemo4.testFourPlayersTwoPairsDouDizhuWithBombGreaterThanLevel(testTimes, null, bombLevel6);

        //炸弹级别
//        int bombLevel7 = 7;
//        ProbabilityTestDemo4.testFourPlayersTwoPairsDouDizhuWithBombGreaterThanLevel(testTimes, null, bombLevel7);

        //炸弹级别
        int bombLevel8 = 8;
        ProbabilityTestDemo4.testFourPlayersTwoPairsDouDizhuWithBombGreaterThanLevel(testTimes, null, bombLevel8);
    }

    /**
     * 两副牌四人斗地主n星及以上炸概率实验
     *
     * @param testTimes     实验频数
     * @param writeFilePath 将实验结果写入的文件文件
     * @param bombLevel 炸弹级别
     */
    public static void testFourPlayersTwoPairsDouDizhuWithBombGreaterThanLevel(int testTimes, String writeFilePath, int bombLevel) {
        if (testTimes < 1) {
            return;
        }
        System.out.println("=================两副牌四人斗地主六星及以上炸概率实验进行中...=================");
        System.out.println();

        int bombGreaterThanLevel6TimesBeforeCall = 0;
        int player1BombGreaterThanLevel6TimesBeforeCall = 0;
        int player2BombGreaterThanLevel6TimesBeforeCall = 0;
        int player3BombGreaterThanLevel6TimesBeforeCall = 0;
        int player4BombGreaterThanLevel6TimesBeforeCall = 0;
        int bombGreaterThanLevel6TimesAfterCall = 0;
        int player1BombGreaterThanLevel6TimesAfterCall = 0;
        int player2BombGreaterThanLevel6TimesAfterCall = 0;
        int player3BombGreaterThanLevel6TimesAfterCall = 0;
        int player4BombGreaterThanLevel6TimesAfterCall = 0;

        FourPlayersTwoPairsDouDizhuGame game = new FourPlayersTwoPairsDouDizhuGame();
        for (int i = 1; i <= testTimes; i++) {
            if (i % 10000 == 0){
                System.out.println("已完成"+i+"次实验, 还剩"+(testTimes-i)+"次待完成");
            }

            StringBuilder detail = new StringBuilder();
            detail.append("=================一副牌三人斗地主发牌模拟，第").append(i).append("次=================").append("\n");
            game.sendCards();
            detail.append(game.showAllCards()).append("\n");
            //==================叫地主前==================//
            List<Card> player1 = game.getPlayer1();
            boolean player1HasBombGreaterThanLevel6BeforeCall = CardUtils.hasBombExceptDoubleJokersWithinTwoPairs(player1, true, bombLevel);
            boolean hasBombGreaterThanLevel6BeforeCall = player1HasBombGreaterThanLevel6BeforeCall;

            List<Card> player2 = game.getPlayer2();
            boolean player2HasBombGreaterThanLevel6BeforeCall = CardUtils.hasBombExceptDoubleJokersWithinTwoPairs(player2, true, bombLevel);
            hasBombGreaterThanLevel6BeforeCall |= player2HasBombGreaterThanLevel6BeforeCall;

            List<Card> player3 = game.getPlayer3();
            boolean player3HasBombGreaterThanLevel6BeforeCall = CardUtils.hasBombExceptDoubleJokersWithinTwoPairs(player3, true, bombLevel);
            hasBombGreaterThanLevel6BeforeCall |= player3HasBombGreaterThanLevel6BeforeCall;

            List<Card> player4 = game.getPlayer4();
            boolean player4HasBombGreaterThanLevel6BeforeCall = CardUtils.hasBombExceptDoubleJokersWithinTwoPairs(player4, true, bombLevel);
            hasBombGreaterThanLevel6BeforeCall |= player4HasBombGreaterThanLevel6BeforeCall;
            detail.append("是否出现").append(bombLevel).append("星及以上炸（叫地主前）: ").append(hasBombGreaterThanLevel6BeforeCall).append("\n");

            if (player1HasBombGreaterThanLevel6BeforeCall) {
                player1BombGreaterThanLevel6TimesBeforeCall++;
            }
            if (player2HasBombGreaterThanLevel6BeforeCall) {
                player2BombGreaterThanLevel6TimesBeforeCall++;
            }
            if (player3HasBombGreaterThanLevel6BeforeCall) {
                player3BombGreaterThanLevel6TimesBeforeCall++;
            }
            if (player4HasBombGreaterThanLevel6BeforeCall) {
                player4BombGreaterThanLevel6TimesBeforeCall++;
            }
            if (hasBombGreaterThanLevel6BeforeCall) {
                bombGreaterThanLevel6TimesBeforeCall++;
            }

            //==================叫地主后==================//
            List<Card> player1CallLord = game.callLord(player1);
            detail.append("player1叫地主后的牌: ").append(player1CallLord).append("\n");
            boolean player1HasBombGreaterThanLevel6AfterCall = CardUtils.hasBombExceptDoubleJokersWithinTwoPairs(player1CallLord, true, bombLevel);
            boolean hasBombGreaterThanLevel6AfterCall = player1HasBombGreaterThanLevel6AfterCall;

            List<Card> player2CallLord = game.callLord(player2);
            detail.append("player2叫地主后的牌: ").append(player2CallLord).append("\n");
            boolean player2HasBombGreaterThanLevel6AfterCall = CardUtils.hasBombExceptDoubleJokersWithinTwoPairs(player2CallLord, true, bombLevel);
            hasBombGreaterThanLevel6AfterCall |= player2HasBombGreaterThanLevel6AfterCall;

            List<Card> player3CallLord = game.callLord(player3);
            detail.append("player3叫地主后的牌: ").append(player3CallLord).append("\n");
            boolean player3HasBombGreaterThanLevel6AfterCall = CardUtils.hasBombExceptDoubleJokersWithinTwoPairs(player3CallLord, true, bombLevel);
            hasBombGreaterThanLevel6AfterCall |= player3HasBombGreaterThanLevel6AfterCall;

            List<Card> player4CallLord = game.callLord(player4);
            detail.append("player4叫地主后的牌: ").append(player4CallLord).append("\n");
            boolean player4HasBombGreaterThanLevel6AfterCall = CardUtils.hasBombExceptDoubleJokersWithinTwoPairs(player4CallLord, true, bombLevel);
            hasBombGreaterThanLevel6AfterCall |= player4HasBombGreaterThanLevel6AfterCall;
            detail.append("是否出现").append(bombLevel).append("星及以上炸（叫地主后）: ").append(hasBombGreaterThanLevel6AfterCall).append("\n");
            detail.append("\n");

            if (player1HasBombGreaterThanLevel6AfterCall) {
                player1BombGreaterThanLevel6TimesAfterCall++;
            }
            if (player2HasBombGreaterThanLevel6AfterCall) {
                player2BombGreaterThanLevel6TimesAfterCall++;
            }
            if (player3HasBombGreaterThanLevel6AfterCall) {
                player3BombGreaterThanLevel6TimesAfterCall++;
            }
            if (player4HasBombGreaterThanLevel6AfterCall) {
                player4BombGreaterThanLevel6TimesAfterCall++;
            }
            if (hasBombGreaterThanLevel6AfterCall) {
                bombGreaterThanLevel6TimesAfterCall++;
            }

            if (StringUtils.isNotEmpty(writeFilePath)) {
                //写入文件
                try {
                    if (i == 1) {
                        FileUtils.write(new File(writeFilePath), detail.toString(), false);
                    } else {
                        FileUtils.write(new File(writeFilePath), detail.toString(), true);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("=================实验结果=================").append("\n");
        result.append("实验次数: ").append(testTimes).append("\n");
        double pBefore = bombGreaterThanLevel6TimesBeforeCall * 1.0 / testTimes;
        result.append("全场").append(bombLevel).append("星及以上炸（叫地主前）出现次数: ").append(bombGreaterThanLevel6TimesBeforeCall).append("，出现概率: ").append(String.format("%.3f", pBefore * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / pBefore)).append("把出现一次。");
        double pAfter = bombGreaterThanLevel6TimesAfterCall * 1.0 / testTimes;
        result.append("全场").append(bombLevel).append("星及以上炸（叫地主后）出现次数: ").append(bombGreaterThanLevel6TimesAfterCall).append("，出现概率: ").append(String.format("%.3f", pAfter * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / pAfter)).append("把出现一次。").append("\n");

        double p1Before = player1BombGreaterThanLevel6TimesBeforeCall * 1.0 / testTimes;
        result.append("player1 ").append(bombLevel).append("星及以上炸（叫地主前）出现次数: ").append(player1BombGreaterThanLevel6TimesBeforeCall).append("，出现概率: ").append(String.format("%.3f", p1Before * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p1Before)).append("把出现一次。");
        double p1After = player1BombGreaterThanLevel6TimesAfterCall * 1.0 / testTimes;
        result.append("player1 ").append(bombLevel).append("星及以上炸（叫地主后）出现次数: ").append(player1BombGreaterThanLevel6TimesAfterCall).append("，出现概率: ").append(String.format("%.3f", p1After * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p1After)).append("把出现一次。").append("\n");

        double p2Before = player2BombGreaterThanLevel6TimesBeforeCall * 1.0 / testTimes;
        result.append("player2 ").append(bombLevel).append("星及以上炸（叫地主前）出现次数: ").append(player2BombGreaterThanLevel6TimesBeforeCall).append("，出现概率: ").append(String.format("%.3f", p2Before * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p2Before)).append("把出现一次。");
        double p2After = player2BombGreaterThanLevel6TimesAfterCall * 1.0 / testTimes;
        result.append("player2 ").append(bombLevel).append("星及以上炸（叫地主后）出现次数: ").append(player2BombGreaterThanLevel6TimesAfterCall).append("，出现概率: ").append(String.format("%.3f", p2After * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p2After)).append("把出现一次。").append("\n");

        double p3Before = player3BombGreaterThanLevel6TimesBeforeCall * 1.0 / testTimes;
        result.append("player3 ").append(bombLevel).append("星及以上炸（叫地主前）出现次数: ").append(player3BombGreaterThanLevel6TimesBeforeCall).append("，出现概率: ").append(String.format("%.3f", p3Before * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p3Before)).append("把出现一次。");
        double p3After = player3BombGreaterThanLevel6TimesAfterCall * 1.0 / testTimes;
        result.append("player3 ").append(bombLevel).append("星及以上炸（叫地主后）出现次数: ").append(player3BombGreaterThanLevel6TimesAfterCall).append("，出现概率: ").append(String.format("%.3f", p3After * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p3After)).append("把出现一次。").append("\n");

        double p4Before = player4BombGreaterThanLevel6TimesBeforeCall * 1.0 / testTimes;
        result.append("player4 ").append(bombLevel).append("星及以上炸（叫地主前）出现次数: ").append(player4BombGreaterThanLevel6TimesBeforeCall).append("，出现概率: ").append(String.format("%.3f", p3Before * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p3Before)).append("把出现一次。");
        double p4After = player4BombGreaterThanLevel6TimesAfterCall * 1.0 / testTimes;
        result.append("player4 ").append(bombLevel).append("星及以上炸（叫地主后）出现次数: ").append(player4BombGreaterThanLevel6TimesAfterCall).append("，出现概率: ").append(String.format("%.3f", p4After * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p3After)).append("把出现一次。").append("\n");

        double _pBefore = (p1Before + p2Before + p3Before + p4Before) / 4;
        result.append("平均每个玩家").append(bombLevel).append("星及以上炸（叫地主前）概率: ").append(String.format("%.3f", _pBefore * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / _pBefore)).append("把出现一次。");
        double _pAfter = (p1After + p2After + p3After + p4After) / 4;
        result.append("平均每个玩家").append(bombLevel).append("星及以上炸（叫地主后）概率: ").append(String.format("%.3f", _pAfter * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / _pAfter)).append("把出现一次。").append("\n");



        if (StringUtils.isNotEmpty(writeFilePath)) {
            //写入文件
            try {
                FileUtils.write(new File(writeFilePath), result.toString(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //输出控制台
        System.out.print(result.toString());
    }
}
