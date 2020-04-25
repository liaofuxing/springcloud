package com.springcloud.common.spi.sms;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

public class SmsManager {


    private static final Logger LOGGER = LoggerFactory.getLogger(SmsManager.class);

    private final static List<Sms> smsImplList = new ArrayList<>();

    static {
        loadInitialSmsImpl();
        LOGGER.info("SmsManager initialized");
    }

    private static void loadInitialSmsImpl() {

        ServiceLoader<Sms> loadedSms = ServiceLoader.load(Sms.class);
        Iterator<Sms> smsIterator = loadedSms.iterator();
        try {
            while (smsIterator.hasNext()) {
                smsImplList.add(smsIterator.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String sendSms(String no) {
        String SmsCode = "";
        for (Sms sms : smsImplList) {
            SmsCode = sms.sendSmsCode(no);
        }
        return SmsCode;
    }

}
