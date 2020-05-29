package com.jzy.demo;

import com.jzy.game.onepair.ThreePlayersOnePairDouDizhuGame;
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
 * @Description 一副牌三人斗地主普通4星概率实验
 * @Date 2020/5/29 12:54
 * @Version 1.0
 **/
public class ProbabilityTestDemo3 extends BaseProbabilityTest {
    public static void main(String[] args) {
        //实验次数
        int testTimes = 1000000;
        //实验结果明细输出文件路径
        String saveFilePath = "C:\\Users\\92970\\Desktop\\实验结果_一副牌三人斗地主普通4星炸概率.txt";
        ProbabilityTestDemo3.testThreePlayersOnePairDouDizhuWithLevel4Bomb(testTimes, null);//不输出到文件

    }

    public static void testThreePlayersOnePairDouDizhuWithLevel4Bomb() {
        testThreePlayersOnePairDouDizhuWithLevel4Bomb(DEFAULT_TEST_TIMES, null);
    }

    public static void testThreePlayersOnePairDouDizhuWithLevel4Bomb(int testTimes) {
        testThreePlayersOnePairDouDizhuWithLevel4Bomb(testTimes, null);
    }

    /**
     * 一副牌三人斗地主普通4星概率实验
     *
     * @param testTimes     实验频数
     * @param writeFilePath 将实验结果写入的文件文件
     */
    public static void testThreePlayersOnePairDouDizhuWithLevel4Bomb(int testTimes, String writeFilePath) {
        if (testTimes < 1) {
            return;
        }
        System.out.println("=================一副牌三人斗地主双王炸概率实验进行中...=================");
        System.out.println();

        int level4BombTimesBeforeCall = 0;
        int player1Level4BombTimesBeforeCall = 0;
        int player2Level4BombTimesBeforeCall = 0;
        int player3Level4BombTimesBeforeCall = 0;
        int level4BombTimesAfterCall = 0;
        int player1Level4BombTimesAfterCall = 0;
        int player2Level4BombTimesAfterCall = 0;
        int player3Level4BombTimesAfterCall = 0;

        ThreePlayersOnePairDouDizhuGame game = new ThreePlayersOnePairDouDizhuGame();
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
            boolean player1HasLevel4BombBeforeCall = CardUtils.hasBombExceptDoubleJokersWithinOnePair(player1, true);
            boolean hasLevel4BombBeforeCall = player1HasLevel4BombBeforeCall;

            List<Card> player2 = game.getPlayer2();
            boolean player2HasLevel4BombBeforeCall = CardUtils.hasBombExceptDoubleJokersWithinOnePair(player2, true);
            hasLevel4BombBeforeCall |= player2HasLevel4BombBeforeCall;

            List<Card> player3 = game.getPlayer3();
            boolean player3HasLevel4BombBeforeCall = CardUtils.hasBombExceptDoubleJokersWithinOnePair(player3, true);
            hasLevel4BombBeforeCall |= player3HasLevel4BombBeforeCall;
            detail.append("是否出现天王炸（叫地主前）: ").append(hasLevel4BombBeforeCall).append("\n");

            if (player1HasLevel4BombBeforeCall) {
                player1Level4BombTimesBeforeCall++;
            }
            if (player2HasLevel4BombBeforeCall) {
                player2Level4BombTimesBeforeCall++;
            }
            if (player3HasLevel4BombBeforeCall) {
                player3Level4BombTimesBeforeCall++;
            }
            if (hasLevel4BombBeforeCall) {
                level4BombTimesBeforeCall++;
            }

            //==================叫地主后==================//
            List<Card> player1CallLord = game.callLord(player1);
            detail.append("player1叫地主后的牌: ").append(player1CallLord).append("\n");
            boolean player1HasLevel4BombAfterCall = CardUtils.hasBombExceptDoubleJokersWithinOnePair(player1CallLord, true);
            boolean hasLevel4BombAfterCall = player1HasLevel4BombAfterCall;

            List<Card> player2CallLord = game.callLord(player2);
            detail.append("player2叫地主后的牌: ").append(player2CallLord).append("\n");
            boolean player2HasLevel4BombAfterCall = CardUtils.hasBombExceptDoubleJokersWithinOnePair(player2CallLord, true);
            hasLevel4BombAfterCall |= player2HasLevel4BombAfterCall;

            List<Card> player3CallLord = game.callLord(player3);
            detail.append("player3叫地主后的牌: ").append(player3CallLord).append("\n");
            boolean player3HasLevel4BombAfterCall = CardUtils.hasBombExceptDoubleJokersWithinOnePair(player3CallLord, true);
            hasLevel4BombAfterCall |= player3HasLevel4BombAfterCall;
            detail.append("是否出现天王炸（叫地主后）: ").append(hasLevel4BombAfterCall).append("\n");
            detail.append("\n");

            if (player1HasLevel4BombAfterCall) {
                player1Level4BombTimesAfterCall++;
            }
            if (player2HasLevel4BombAfterCall) {
                player2Level4BombTimesAfterCall++;
            }
            if (player3HasLevel4BombAfterCall) {
                player3Level4BombTimesAfterCall++;
            }
            if (hasLevel4BombAfterCall) {
                level4BombTimesAfterCall++;
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
        double pBefore = level4BombTimesBeforeCall * 1.0 / testTimes;
        result.append("全场天王炸（叫地主前）出现次数: ").append(level4BombTimesBeforeCall).append("，出现概率: ").append(String.format("%.3f", pBefore * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / pBefore)).append("把出现一次。");
        double pAfter = level4BombTimesAfterCall * 1.0 / testTimes;
        result.append("全场天王炸（叫地主后）出现次数: ").append(level4BombTimesAfterCall).append("，出现概率: ").append(String.format("%.3f", pAfter * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / pAfter)).append("把出现一次。").append("\n");

        double p1Before = player1Level4BombTimesBeforeCall * 1.0 / testTimes;
        result.append("player1天王炸（叫地主前）出现次数: ").append(player1Level4BombTimesBeforeCall).append("，出现概率: ").append(String.format("%.3f", p1Before * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p1Before)).append("把出现一次。");
        double p1After = player1Level4BombTimesAfterCall * 1.0 / testTimes;
        result.append("player1天王炸（叫地主后）出现次数: ").append(player1Level4BombTimesAfterCall).append("，出现概率: ").append(String.format("%.3f", p1After * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p1After)).append("把出现一次。").append("\n");

        double p2Before = player2Level4BombTimesBeforeCall * 1.0 / testTimes;
        result.append("player2天王炸（叫地主前）出现次数: ").append(player2Level4BombTimesBeforeCall).append("，出现概率: ").append(String.format("%.3f", p2Before * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p2Before)).append("把出现一次。");
        double p2After = player2Level4BombTimesAfterCall * 1.0 / testTimes;
        result.append("player2天王炸（叫地主后）出现次数: ").append(player2Level4BombTimesAfterCall).append("，出现概率: ").append(String.format("%.3f", p2After * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p2After)).append("把出现一次。").append("\n");

        double p3Before = player3Level4BombTimesBeforeCall * 1.0 / testTimes;
        result.append("player3天王炸（叫地主前）出现次数: ").append(player3Level4BombTimesBeforeCall).append("，出现概率: ").append(String.format("%.3f", p3Before * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p3Before)).append("把出现一次。");
        double p3After = player3Level4BombTimesAfterCall * 1.0 / testTimes;
        result.append("player3天王炸（叫地主后）出现次数: ").append(player3Level4BombTimesAfterCall).append("，出现概率: ").append(String.format("%.3f", p3After * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p3After)).append("把出现一次。").append("\n");

        double _pBefore = (p1Before + p2Before + p3Before) / 3;
        result.append("平均每个玩家天王炸（叫地主前）概率: ").append(String.format("%.3f", _pBefore * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / _pBefore)).append("把出现一次。");
        double _pAfter = (p1After + p2After + p3After) / 3;
        result.append("平均每个玩家天王炸（叫地主后）概率: ").append(String.format("%.3f", _pAfter * 100)).append("%，");
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
