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
        HOLOSIEVO, DARNITSA, DESNA, DNIPROVSKY, OBOLON, PECHERSK, PODOLSKIY, SVYATOSHYN, SOLOMYANKA, SHEVCHENKIVSKIY
    }

    enum Term {
        LONG_TERM, SHORT_TERM
    }

}
