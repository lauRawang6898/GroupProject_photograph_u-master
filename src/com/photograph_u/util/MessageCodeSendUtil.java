package com.photograph_u.util;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.photograph_u.exception.MessageCodeSendException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageCodeSendUtil {
    private static ThreadLocal<DateFormat> dataFormatThreadLocal = new ThreadLocal<>();

    private static final String ACCOUNT_SID = "150d11a71b464960bbaee3f60170b0b7";
    private static final String AUTH_TOKEN = "a562f29596f74764b2d46c0b8415c30e";
    private static final String REQUEST_URL = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";

    public static void sendMessageCode(String phone, String messageCode, int validMinute) {
        String timestamp = getTimestamp();
        String smsContent = "Your message code is " + messageCode + "，Please input in  " + validMinute + "minutes.";
        String sig = CommUtils.getMd5Content(ACCOUNT_SID + AUTH_TOKEN + timestamp);
        String body = "accountSid=" + ACCOUNT_SID + "&smsContent=" + smsContent + "&to=" + phone + "&timestamp=" + timestamp + "&sig=" + sig + "&respDataType=JSON";
        String response = HttpUtil.doPost(REQUEST_URL, body);
        if (!"".equals(response)) {
            JsonObject jsonObject = (JsonObject) new JsonParser().parse(response);
            String respCode = String.valueOf(jsonObject.get("respCode")).replace("\"", "");
            String respDesc = String.valueOf(jsonObject.get("respDesc")).replace("\"", "");
            if (!respCode.equals("00000")) {
                throw new MessageCodeSendException(respDesc);
            }
        } else {
            throw new MessageCodeSendException("message code error ");
        }
    }

    private static String getTimestamp() {
        DateFormat dateFormat = dataFormatThreadLocal.get();
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        }
        return dateFormat.format(new Date());
    }
}
