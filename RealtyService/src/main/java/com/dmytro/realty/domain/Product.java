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
        APARTMENT, ROOM//, HOUSE//, SOIL
    }

    public enum Location {
        ALL(0,0,0,0) ,HOLOSIEVO(1,1,81,369), DARNITSA(3,2,85,370), DESNA(5,3,83,371), DNIPROVSKY(7,4,84,372), OBOLON(9,5,76,374), PECHERSK(11,6,82,375), PODOLSKIY(13,7,78,376), SVYATOSHYN(15,8,86,377), SOLOMYANKA(17,9,80,378), SHEVCHENKIVSKIY(19,10,79,379);

        int slandoIndex = 0;
        int avisoIndex = 0;
        int rieltorIndex = 0;
        int mirKvartirIndex = 0;

        private Location(int slandoIndex,int avisoIndex,int rieltorIndex, int mirKvartirIndex) {
            this.slandoIndex = slandoIndex;
            this.avisoIndex = avisoIndex;
            this.rieltorIndex = rieltorIndex;
            this.mirKvartirIndex = mirKvartirIndex;
        }

        public int getSlandoIndex() {
            return slandoIndex;
        }

        public int getAvisoIndex() {
            return avisoIndex;
        }

        public int getRieltorIndex() {
            return rieltorIndex;
        }

        public int getMirKvartirIndex() {
            return mirKvartirIndex;
        }

        public static String all(){
            return "ALL";
        }
    }

    enum Term {
        LONG_TERM, SHORT_TERM
    }

}
