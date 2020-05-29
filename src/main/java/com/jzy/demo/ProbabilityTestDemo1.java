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
 * @Description 一副牌三人斗地主双王炸概率实验
 * @Date 2020/5/29 12:54
 * @Version 1.0
 **/
public class ProbabilityTestDemo1 extends BaseProbabilityTest {
    public static void main(String[] args) {
        //实验次数
        int testTimes = 50000;
        //实验结果明细输出文件路径
        String saveFilePath = "C:\\Users\\92970\\Desktop\\实验结果_一副牌三人斗地主双王炸概率.txt";
//        ProbabilityTestDemo1.testThreePlayersOnePairDouDizhuWithDoubleJokers(testTimes, null);//不输出到文件
        ProbabilityTestDemo1.testThreePlayersOnePairDouDizhuWithDoubleJokers(testTimes, null);

    }

    public static void testThreePlayersOnePairDouDizhuWithDoubleJokers() {
        testThreePlayersOnePairDouDizhuWithDoubleJokers(DEFAULT_TEST_TIMES, null);
    }

    public static void testThreePlayersOnePairDouDizhuWithDoubleJokers(int testTimes) {
        testThreePlayersOnePairDouDizhuWithDoubleJokers(testTimes, null);
    }

    /**
     * 一副牌三人斗地主双王炸概率实验
     *
     * @param testTimes     实验频数
     * @param writeFilePath 将实验结果写入的文件文件
     */
    public static void testThreePlayersOnePairDouDizhuWithDoubleJokers(int testTimes, String writeFilePath) {
        if (testTimes < 1) {
            return;
        }
        System.out.println("=================一副牌三人斗地主双王炸概率实验进行中...=================");
        System.out.println();

        int doubleJokersTimesBeforeCall = 0;
        int player1DoubleJokersTimesBeforeCall = 0;
        int player2DoubleJokersTimesBeforeCall = 0;
        int player3DoubleJokersTimesBeforeCall = 0;
        int doubleJokersTimesAfterCall = 0;
        int player1DoubleJokersTimesAfterCall = 0;
        int player2DoubleJokersTimesAfterCall = 0;
        int player3DoubleJokersTimesAfterCall = 0;

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
            boolean player1HasDoubleJokersBeforeCall = CardUtils.hasDoubleJokersWithinOnePair(player1, true);
            boolean hasDoubleJokersBeforeCall = player1HasDoubleJokersBeforeCall;

            List<Card> player2 = game.getPlayer2();
            boolean player2HasDoubleJokersBeforeCall = CardUtils.hasDoubleJokersWithinOnePair(player2, true);
            hasDoubleJokersBeforeCall |= player2HasDoubleJokersBeforeCall;

            List<Card> player3 = game.getPlayer3();
            boolean player3HasDoubleJokersBeforeCall = CardUtils.hasDoubleJokersWithinOnePair(player3, true);
            hasDoubleJokersBeforeCall |= player3HasDoubleJokersBeforeCall;
            detail.append("是否出现天王炸（叫地主前）: ").append(hasDoubleJokersBeforeCall).append("\n");

            if (player1HasDoubleJokersBeforeCall) {
                player1DoubleJokersTimesBeforeCall++;
            }
            if (player2HasDoubleJokersBeforeCall) {
                player2DoubleJokersTimesBeforeCall++;
            }
            if (player3HasDoubleJokersBeforeCall) {
                player3DoubleJokersTimesBeforeCall++;
            }
            if (hasDoubleJokersBeforeCall) {
                doubleJokersTimesBeforeCall++;
            }

            //==================叫地主后==================//
            List<Card> player1CallLord = game.callLord(player1);
            detail.append("player1叫地主后的牌: ").append(player1CallLord).append("\n");
            boolean player1HasDoubleJokersAfterCall = CardUtils.hasDoubleJokersWithinOnePair(player1CallLord, true);
            boolean hasDoubleJokersAfterCall = player1HasDoubleJokersAfterCall;

            List<Card> player2CallLord = game.callLord(player2);
            detail.append("player2叫地主后的牌: ").append(player2CallLord).append("\n");
            boolean player2HasDoubleJokersAfterCall = CardUtils.hasDoubleJokersWithinOnePair(player2CallLord, true);
            hasDoubleJokersAfterCall |= player2HasDoubleJokersAfterCall;

            List<Card> player3CallLord = game.callLord(player3);
            detail.append("player3叫地主后的牌: ").append(player3CallLord).append("\n");
            boolean player3HasDoubleJokersAfterCall = CardUtils.hasDoubleJokersWithinOnePair(player3CallLord, true);
            hasDoubleJokersAfterCall |= player3HasDoubleJokersAfterCall;
            detail.append("是否出现天王炸（叫地主后）: ").append(hasDoubleJokersAfterCall).append("\n");
            detail.append("\n");

            if (player1HasDoubleJokersAfterCall) {
                player1DoubleJokersTimesAfterCall++;
            }
            if (player2HasDoubleJokersAfterCall) {
                player2DoubleJokersTimesAfterCall++;
            }
            if (player3HasDoubleJokersAfterCall) {
                player3DoubleJokersTimesAfterCall++;
            }
            if (hasDoubleJokersAfterCall) {
                doubleJokersTimesAfterCall++;
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
        double pBefore = doubleJokersTimesBeforeCall * 1.0 / testTimes;
        result.append("全场4星炸（叫地主前）出现次数: ").append(doubleJokersTimesBeforeCall).append("，出现概率: ").append(String.format("%.3f", pBefore * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / pBefore)).append("把出现一次。");
        double pAfter = doubleJokersTimesAfterCall * 1.0 / testTimes;
        result.append("全场4星炸（叫地主后）出现次数: ").append(doubleJokersTimesAfterCall).append("，出现概率: ").append(String.format("%.3f", pAfter * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / pAfter)).append("把出现一次。").append("\n");

        double p1Before = player1DoubleJokersTimesBeforeCall * 1.0 / testTimes;
        result.append("player14星炸（叫地主前）出现次数: ").append(player1DoubleJokersTimesBeforeCall).append("，出现概率: ").append(String.format("%.3f", p1Before * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p1Before)).append("把出现一次。");
        double p1After = player1DoubleJokersTimesAfterCall * 1.0 / testTimes;
        result.append("player14星炸（叫地主后）出现次数: ").append(player1DoubleJokersTimesAfterCall).append("，出现概率: ").append(String.format("%.3f", p1After * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p1After)).append("把出现一次。").append("\n");

        double p2Before = player2DoubleJokersTimesBeforeCall * 1.0 / testTimes;
        result.append("player24星炸（叫地主前）出现次数: ").append(player2DoubleJokersTimesBeforeCall).append("，出现概率: ").append(String.format("%.3f", p2Before * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p2Before)).append("把出现一次。");
        double p2After = player2DoubleJokersTimesAfterCall * 1.0 / testTimes;
        result.append("player24星炸（叫地主后）出现次数: ").append(player2DoubleJokersTimesAfterCall).append("，出现概率: ").append(String.format("%.3f", p2After * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p2After)).append("把出现一次。").append("\n");

        double p3Before = player3DoubleJokersTimesBeforeCall * 1.0 / testTimes;
        result.append("player34星炸（叫地主前）出现次数: ").append(player3DoubleJokersTimesBeforeCall).append("，出现概率: ").append(String.format("%.3f", p3Before * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p3Before)).append("把出现一次。");
        double p3After = player3DoubleJokersTimesAfterCall * 1.0 / testTimes;
        result.append("player34星炸（叫地主后）出现次数: ").append(player3DoubleJokersTimesAfterCall).append("，出现概率: ").append(String.format("%.3f", p3After * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p3After)).append("把出现一次。").append("\n");

        double _pBefore = (p1Before + p2Before + p3Before) / 3;
        result.append("平均每个玩家4星炸（叫地主前）概率: ").append(String.format("%.3f", _pBefore * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / _pBefore)).append("把出现一次。");
        double _pAfter = (p1After + p2After + p3After) / 3;
        result.append("平均每个玩家4星炸（叫地主后）概率: ").append(String.format("%.3f", _pAfter * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / _pAfter)).append("把出现一次。").append("\n");

        //理论值
        double ptBefore = 136 * 1.0 / 1431;
        result.append("每个玩家4星炸（叫地主前）概率理论值: ").append(String.format("%.3f", ptBefore * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / ptBefore)).append("把出现一次。");
        double ptAfter = 190 * 1.0 / 1431;
        result.append("每个玩家4星炸（叫地主后）概率理论值: ").append(String.format("%.3f", ptAfter * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / ptAfter)).append("把出现一次。").append("\n");


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
