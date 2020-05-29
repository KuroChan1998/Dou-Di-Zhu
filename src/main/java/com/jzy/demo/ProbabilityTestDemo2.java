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
 * @Description 两副牌四人斗地主天王炸概率实验
 * @Date 2020/5/29 12:54
 * @Version 1.0
 **/
public class ProbabilityTestDemo2 extends BaseProbabilityTest {
    public static void main(String[] args) {
        //两副牌四人斗地主天王炸概率实验
        //实验次数
        int testTimes = 30000;
        //实验结果明细输出文件路径
        String saveFilePath = "E:\\Engineering\\java\\idea\\doudizhu\\output_example\\实验结果_两副牌四人斗地主天王炸概率.txt";
        ProbabilityTestDemo2.testFourPlayersTwoPairsDouDizhuWithFourJokers(testTimes, saveFilePath);

    }

    public static void testFourPlayersTwoPairsDouDizhuWithFourJokers() {
        testFourPlayersTwoPairsDouDizhuWithFourJokers(DEFAULT_TEST_TIMES, null);
    }

    public static void testFourPlayersTwoPairsDouDizhuWithFourJokers(int testTimes) {
        testFourPlayersTwoPairsDouDizhuWithFourJokers(testTimes, null);
    }

    /**
     * 两副牌四人斗地主天王炸概率实验
     *
     * @param testTimes     实验频数
     * @param writeFilePath 将实验结果写入的文件文件
     */
    public static void testFourPlayersTwoPairsDouDizhuWithFourJokers(int testTimes, String writeFilePath) {
        if (testTimes < 1) {
            return;
        }
        System.out.println("=================两副牌四人斗地主天王炸概率实验进行中...=================");
        System.out.println();

        int fourJokersTimesBeforeCall = 0;
        int player1FourJokersTimesBeforeCall = 0;
        int player2FourJokersTimesBeforeCall = 0;
        int player3FourJokersTimesBeforeCall = 0;
        int player4FourJokersTimesBeforeCall = 0;
        int fourJokersTimesAfterCall = 0;
        int player1FourJokersTimesAfterCall = 0;
        int player2FourJokersTimesAfterCall = 0;
        int player3FourJokersTimesAfterCall = 0;
        int player4FourJokersTimesAfterCall = 0;

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
            boolean player1HasFourJokersBeforeCall = CardUtils.hasFourJokersWithinTwoPairs(player1, true);
            boolean hasFourJokersBeforeCall = player1HasFourJokersBeforeCall;

            List<Card> player2 = game.getPlayer2();
            boolean player2HasFourJokersBeforeCall = CardUtils.hasFourJokersWithinTwoPairs(player2, true);
            hasFourJokersBeforeCall |= player2HasFourJokersBeforeCall;

            List<Card> player3 = game.getPlayer3();
            boolean player3HasFourJokersBeforeCall = CardUtils.hasFourJokersWithinTwoPairs(player3, true);
            hasFourJokersBeforeCall |= player3HasFourJokersBeforeCall;

            List<Card> player4 = game.getPlayer4();
            boolean player4HasFourJokersBeforeCall = CardUtils.hasFourJokersWithinTwoPairs(player4, true);
            hasFourJokersBeforeCall |= player4HasFourJokersBeforeCall;
            detail.append("是否出现天王炸（叫地主前）: ").append(hasFourJokersBeforeCall).append("\n");

            if (player1HasFourJokersBeforeCall) {
                player1FourJokersTimesBeforeCall++;
            }
            if (player2HasFourJokersBeforeCall) {
                player2FourJokersTimesBeforeCall++;
            }
            if (player3HasFourJokersBeforeCall) {
                player3FourJokersTimesBeforeCall++;
            }
            if (player4HasFourJokersBeforeCall) {
                player4FourJokersTimesBeforeCall++;
            }
            if (hasFourJokersBeforeCall) {
                fourJokersTimesBeforeCall++;
            }

            //==================叫地主后==================//
            List<Card> player1CallLord = game.callLord(player1);
            detail.append("player1叫地主后的牌: ").append(player1CallLord).append("\n");
            boolean player1HasFourJokersAfterCall = CardUtils.hasFourJokersWithinTwoPairs(player1CallLord, true);
            boolean hasFourJokersAfterCall = player1HasFourJokersAfterCall;

            List<Card> player2CallLord = game.callLord(player2);
            detail.append("player2叫地主后的牌: ").append(player2CallLord).append("\n");
            boolean player2HasFourJokersAfterCall = CardUtils.hasFourJokersWithinTwoPairs(player2CallLord, true);
            hasFourJokersAfterCall |= player2HasFourJokersAfterCall;

            List<Card> player3CallLord = game.callLord(player3);
            detail.append("player3叫地主后的牌: ").append(player3CallLord).append("\n");
            boolean player3HasFourJokersAfterCall = CardUtils.hasFourJokersWithinTwoPairs(player3CallLord, true);
            hasFourJokersAfterCall |= player3HasFourJokersAfterCall;

            List<Card> player4CallLord = game.callLord(player4);
            detail.append("player4叫地主后的牌: ").append(player4CallLord).append("\n");
            boolean player4HasFourJokersAfterCall = CardUtils.hasFourJokersWithinTwoPairs(player4CallLord, true);
            hasFourJokersAfterCall |= player4HasFourJokersAfterCall;
            detail.append("是否出现天王炸（叫地主后）: ").append(hasFourJokersAfterCall).append("\n");
            detail.append("\n");

            if (player1HasFourJokersAfterCall) {
                player1FourJokersTimesAfterCall++;
            }
            if (player2HasFourJokersAfterCall) {
                player2FourJokersTimesAfterCall++;
            }
            if (player3HasFourJokersAfterCall) {
                player3FourJokersTimesAfterCall++;
            }
            if (player4HasFourJokersAfterCall) {
                player4FourJokersTimesAfterCall++;
            }
            if (hasFourJokersAfterCall) {
                fourJokersTimesAfterCall++;
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
        double pBefore = fourJokersTimesBeforeCall * 1.0 / testTimes;
        result.append("全场天王炸（叫地主前）出现次数: ").append(fourJokersTimesBeforeCall).append("，出现概率: ").append(String.format("%.3f", pBefore * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / pBefore)).append("把出现一次。");
        double pAfter = fourJokersTimesAfterCall * 1.0 / testTimes;
        result.append("全场天王炸（叫地主后）出现次数: ").append(fourJokersTimesAfterCall).append("，出现概率: ").append(String.format("%.3f", pAfter * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / pAfter)).append("把出现一次。").append("\n");

        double p1Before = player1FourJokersTimesBeforeCall * 1.0 / testTimes;
        result.append("player1天王炸（叫地主前）出现次数: ").append(player1FourJokersTimesBeforeCall).append("，出现概率: ").append(String.format("%.3f", p1Before * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p1Before)).append("把出现一次。");
        double p1After = player1FourJokersTimesAfterCall * 1.0 / testTimes;
        result.append("player1天王炸（叫地主后）出现次数: ").append(player1FourJokersTimesAfterCall).append("，出现概率: ").append(String.format("%.3f", p1After * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p1After)).append("把出现一次。").append("\n");

        double p2Before = player2FourJokersTimesBeforeCall * 1.0 / testTimes;
        result.append("player2天王炸（叫地主前）出现次数: ").append(player2FourJokersTimesBeforeCall).append("，出现概率: ").append(String.format("%.3f", p2Before * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p2Before)).append("把出现一次。");
        double p2After = player2FourJokersTimesAfterCall * 1.0 / testTimes;
        result.append("player2天王炸（叫地主后）出现次数: ").append(player2FourJokersTimesAfterCall).append("，出现概率: ").append(String.format("%.3f", p2After * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p2After)).append("把出现一次。").append("\n");

        double p3Before = player3FourJokersTimesBeforeCall * 1.0 / testTimes;
        result.append("player3天王炸（叫地主前）出现次数: ").append(player3FourJokersTimesBeforeCall).append("，出现概率: ").append(String.format("%.3f", p3Before * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p3Before)).append("把出现一次。");
        double p3After = player3FourJokersTimesAfterCall * 1.0 / testTimes;
        result.append("player3天王炸（叫地主后）出现次数: ").append(player3FourJokersTimesAfterCall).append("，出现概率: ").append(String.format("%.3f", p3After * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p3After)).append("把出现一次。").append("\n");

        double p4Before = player4FourJokersTimesBeforeCall * 1.0 / testTimes;
        result.append("player4天王炸（叫地主前）出现次数: ").append(player4FourJokersTimesBeforeCall).append("，出现概率: ").append(String.format("%.3f", p3Before * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p3Before)).append("把出现一次。");
        double p4After = player4FourJokersTimesAfterCall * 1.0 / testTimes;
        result.append("player4天王炸（叫地主后）出现次数: ").append(player4FourJokersTimesAfterCall).append("，出现概率: ").append(String.format("%.3f", p4After * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / p3After)).append("把出现一次。").append("\n");

        double _pBefore = (p1Before + p2Before + p3Before + p4Before) / 4;
        result.append("平均每个玩家天王炸（叫地主前）概率: ").append(String.format("%.3f", _pBefore * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / _pBefore)).append("把出现一次。");
        double _pAfter = (p1After + p2After + p3After + p4After) / 4;
        result.append("平均每个玩家天王炸（叫地主后）概率: ").append(String.format("%.3f", _pAfter * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / _pAfter)).append("把出现一次。").append("\n");

        //理论值
        double ptBefore = 0.002360;
        result.append("每个玩家天王炸（叫地主前）概率理论值: ").append(String.format("%.3f", ptBefore * 100)).append("%，");
        result.append("平均每").append(String.format("%.2f", 1.0 / ptBefore)).append("把出现一次。");
        double ptAfter = 0.007636;
        result.append("每个玩家天王炸（叫地主后）概率理论值: ").append(String.format("%.3f", ptAfter * 100)).append("%，");
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
