package com.zmaxfilm.service;

/**
 * Created by jimmy on 2016/11/15.
 */
public interface DeviceDetectionService {

    Boolean hasDll();
    Boolean hasHtml();
    void downloadDll();
    void downloadHtml();

    void checkAndFix();

}
