//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.appinventive.qa.util;

public class ClientUtils {
    private static ClientUtils _instance = null;
    private CID cid = new CID(UID.generateUID());

    public static synchronized ClientUtils getInstance() {
        if (_instance == null) {
            _instance = new ClientUtils();
        }

        return _instance;
    }

    private ClientUtils() {
    }

    public CID getCID() {
        return this.cid;
    }
}
