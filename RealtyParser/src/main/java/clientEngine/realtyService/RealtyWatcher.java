package clientEngine.realtyService;

import clientEngine.requestService.RequestFactory;
import clientEngine.sendService.SendMan;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static clientEngine.realtyService.RealtyConstants.*;

public class RealtyWatcher {
    public static int WATCH_TIMER = 120000;
    private int lookFor = APARTMENTS;
    private SendMan sendMan;
    private RealtyParser realtyParser;

    public RealtyWatcher() {
        sendMan = new SendMan();
        realtyParser = new RealtyParser();
    }

    public void startWatching() {
        Timer timer = new Timer();
        timer.schedule(new NewsReader(), 1000, 120000);
    }

    private void startParsing(String address) {
        List<RealtyUnit> realtyUnits = new ArrayList<>();
        List<String> offerLinks = realtyParser.parseOffersList(address, lookFor);
        for (String link : offerLinks)
            realtyUnits.add(realtyParser.parseOffer(link, lookFor));
        sendNews(realtyUnits);
    }

    private void sendNews(List<RealtyUnit> realtyUnits) {
        String content = "";
        for (RealtyUnit unit : realtyUnits)
            content += unit.toString();
        if (content.length() > 1)
            sendMan.sendNews(content, lookFor);
    }

    private class NewsReader extends TimerTask {
        @Override
        public void run() {
            System.out.println("LOOK FOR APPARTMENT!!!");
            lookFor = APARTMENTS;
            startParsing(RequestFactory.createApartmentAddress(priceFromApartment, priceToApartment, rooms));
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("LOOK FOR ROOM!!!");
            lookFor = ROOMS;
            startParsing(RequestFactory.createRoomsAddress(priceFromRoom, priceToRoom));
        }
    }
}


