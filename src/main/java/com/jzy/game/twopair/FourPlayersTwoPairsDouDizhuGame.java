package com.jzy.game.twopair;

import com.jzy.game.AbstractDouDizhuGame;
import com.jzy.game.onepair.OnePairCardGame;
import com.jzy.model.Card;
import com.jzy.util.CardUtils;
import lombok.Getter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName ThreePlayersOnePairDouDizhuGame
 * @Author JinZhiyun
 * @Description 一副牌三人斗地主
 * @Date 2020/5/29 10:17
 * @Version 1.0
 **/
public class FourPlayersTwoPairsDouDizhuGame extends AbstractDouDizhuGame implements TwoPairCardGame {
    @Getter
    protected LinkedList<Card> player1;

    @Getter
    protected LinkedList<Card> player2;

    @Getter
    protected LinkedList<Card> player3;

    @Getter
    protected LinkedList<Card> player4;

    @Override
    public void sendCards() {
        player1 = new LinkedList<>();
        player2 = new LinkedList<>();
        player3 = new LinkedList<>();
        player4 = new LinkedList<>();
        holeCards = new LinkedList<>();

        shuffleCards();
        //遍历这副洗好的牌，遍历过程中，将牌发到4个玩家和底牌中
        for (int i = 0; i < indexesForShuffleCards.size(); i++) {
            Card card = CardUtils.TWO_PAIR.get(indexesForShuffleCards.get(i));
            if (i >= 100) {
                //底牌
                holeCards.add(card);
            } else {
                if (i % 4 == 0) {
                    player1.add(card);
                } else if (i % 4 == 1) {
                    player2.add(card);
                } else if (i % 4 == 2) {
                    player3.add(card);
                } else {
                    player4.add(card);
                }
            }
        }

        //对每个人手中的牌排序
        Collections.sort(player1, Card.COLOR_VALUE_COMPARATOR);
        Collections.sort(player2, Card.COLOR_VALUE_COMPARATOR);
        Collections.sort(player3, Card.COLOR_VALUE_COMPARATOR);
        Collections.sort(player4, Card.COLOR_VALUE_COMPARATOR);

        Collections.sort(holeCards, Card.COLOR_VALUE_COMPARATOR);
    }


    @Override
    public String showPlayerCards() {
        String s = ("player1: " + player1) + "\n";
        s += ("player2: " + player2) + "\n";
        s += ("player3: " + player3) + "\n";
        s += ("player4: " + player4) + "\n";
        return s;
    }

    @Override
    public List<Card> callLord(int playerIndex) {
        if (playerIndex == 1) {
            return callLord(player1);
        }

        if (playerIndex == 2) {
            return callLord(player2);
        }

        if (playerIndex == 3) {
            return callLord(player3);
        }

        if (playerIndex == 4) {
            return callLord(player4);
        }

        return null;
    }
}
