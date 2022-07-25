package core.transportation;

import java.math.BigDecimal;
import core.exception.LocationCanNotBeEmptyException;

public interface ITransporation {
    public BigDecimal getFare() throws LocationCanNotBeEmptyException;
}
