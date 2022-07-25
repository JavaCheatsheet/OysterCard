package core.transportation;

import core.card.Card;
import core.fare.TubeFare;
import core.exception.LocationCanNotBeEmptyException;

import java.math.BigDecimal;

public class Tube extends Transportation
        implements ITransporation  {

    public Tube(Card card) {
        super(card);
    }

    public BigDecimal getFare()
            throws LocationCanNotBeEmptyException {
        TubeFare tubeFare;
        tubeFare = new TubeFare(
                getCheckin(),
                getCheckout());

        return new BigDecimal(tubeFare.getFair());
    }

}
