package com.dmytro.realty.service.moneymaker;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created with IntelliJ IDEA.
 * User: dmytro
 * Date: 31.05.13
 * Time: 19:59
 * To change this template use File | Settings | File Templates.
 */
public class LiqPayRequest {

    public static final String MERCHANT_ID = "i1032136775";
    public static final String MERCHANT_SIGN = "kUDsW9xxBanfkOJusGhisaq8DuwXQQzzPKKxNJ";

    private String xml;

    public LiqPayRequest(String id) {
        xml = "<request>" +
                "<version>1.2</version>" +
                "<merchant_id>" + MERCHANT_ID + "</merchant_id>" +
                "<result_url>http://realtyservice.com.ua/app/cabinet</result_url>" +
                "<server_url>http://realtyservice.com.ua/app/user-pay/liq/process</server_url>" +
                "<order_id>" + id + "</order_id>" +
                "<amount>1.01</amount>" +
                "<currency>UAH</currency>" +
                "<description>Realty service subscription</description>" +
                "<default_phone></default_phone>" +
                "<pay_way>card, liqpay, delayed</pay_way>" +
                "<goods_id>1234</goods_id>" +
                "</request>";
    }

    public String getOperationXml() {
        return Base64.encodeBase64String(xml.getBytes());
    }

    public String getSignature() {
        return Base64.encodeBase64String(DigestUtils.sha1(MERCHANT_SIGN + xml + MERCHANT_SIGN));
    }
}
