package com.dmytro.realty.domain;

/**
 * Created with IntelliJ IDEA.
 * User: dmytro
 * Date: 08.06.13
 * Time: 18:42
 * To change this template use File | Settings | File Templates.
 */
public class Product {
    public enum Operation {
        RENT, BUY//, FARM_OUT, SELL, EXCHANGE, LOOKING_PARTNER
    }
    public enum Type {
        APPARTMENT, ROOM, HOUSE//, SOIL
    }

    public enum Location {
        HOLOSIEVO(1), DARNITSA(3), DESNA(5), DNIPROVSKY(7), OBOLON(9), PECHERSK(11), PODOLSKIY(13), SVYATOSHYN(15), SOLOMYANKA(17), SHEVCHENKIVSKIY(19);

        int slandoIndex = 0;

        private Location(int slandoIndex) {
            this.slandoIndex = slandoIndex;
        }

        public int getSlandoIndex() {
            return slandoIndex;
        }
    }

    enum Term {
        LONG_TERM, SHORT_TERM
    }

}
