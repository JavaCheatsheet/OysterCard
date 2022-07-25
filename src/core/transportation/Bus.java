package core.transportation;

import core.card.Card;

import java.math.BigDecimal;

public class Bus extends Transportation implements ITransporation {

    public Bus(Card card) {
        super(card);
    }

    public BigDecimal getFare() {
        return new BigDecimal(1.8);
    };
}
