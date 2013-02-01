package clientEngine.requestService;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.handler.codec.http.*;

import java.net.URI;
import java.util.Arrays;

import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.*;
import static org.jboss.netty.handler.codec.http.HttpHeaders.Values.*;

/**
 * Created with IntelliJ IDEA.
 * User: dmytro
 * Date: 08.11.12
 * Time: 16:10
 * To change this template use File | Settings | File Templates.
 */
public class RequestFactory {
    public static String protocol = "http://";
    public static String host = "kiev.ko.slando.ua";
    public static String appartment_request = "/nedvizhimost/arenda-kvartir/";
    public static String roomRequest = "/nedvizhimost/arenda-komnat/";
    public static String imageSource = "/ajax/misc/phoneimage/";

    public static String createApartmentAddress(int from, int to, int roomsTo) {
        return protocol + host + appartment_request + findAppartmentCriteria(from, to, roomsTo);
    }

    public static String createRoomsAddress(int from, int to) {
        return protocol + host + roomRequest + findRoomCriteria(from, to);
    }

    public static String createImageAddress(String id){
        return protocol + host + imageSource + id + "/?nomobile=1";
    }

    public static String findAppartmentCriteria(int priceFrom, int priceTo, int rooms) {
        return "?search%5Bfilter_float_price%3Afrom%5D=" + priceFrom + "&search%5Bfilter_float_price%3Ato%5D=" + priceTo + "&search%5Bfilter_float_number_of_rooms%3Ato%5D=" + rooms + "&search%5Bprivate_business%5D=private";
    }

    public static String findRoomCriteria(int priceFrom, int priceTo) {
        return "?search%5Bfilter_float_price%3Afrom%5D=" + priceFrom + "&search%5Bfilter_float_price%3Ato%5D=" + priceTo + "&search%5Bprivate_business%5D=private";
    }
}
