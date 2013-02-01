package clientEngine;

import clientEngine.realtyService.RealtyWatcher;

/**
 * Created with IntelliJ IDEA.
 * User: dmytro
 * Date: 08.11.12
 * Time: 15:44
 * To change this template use File | Settings | File Templates.
 */
public class RealtyMain {

    public static void main(String[] args) {
        RealtyWatcher realtyWatcher = new RealtyWatcher();
        realtyWatcher.startWatching();
    }
}