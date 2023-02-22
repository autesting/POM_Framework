//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.appinventive.qa.util;

import java.util.UUID;

//import com.appinventive.qa.log4j.LogManager;
//import com.appinventive.qa.log4j.Logger;

public class UID {
//    private static final Logger LOGGER = LogManager.getLogger();

    public UID() {
    }

    public static String generateUID() {
        String uuid = UUID.randomUUID().toString();
//        LOGGER.debug("Generating randomUUID - " + uuid);
        return uuid;
    }

    public static String generateUID(String prefix) {
//        LOGGER.debug("Generating randomUUD with prefix - " + prefix);
        return prefix + "-" + generateUID();
    }
}
