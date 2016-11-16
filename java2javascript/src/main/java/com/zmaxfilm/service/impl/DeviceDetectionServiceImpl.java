package com.zmaxfilm.service.impl;

import com.zmaxfilm.service.DeviceDetectionService;
import com.zmaxfilm.util.FileUtils;

/**
 * Created by jimmy on 2016/11/15.
 */
public class DeviceDetectionServiceImpl implements DeviceDetectionService{

    @Override
    public Boolean hasDll() {
        return FileUtils.isExsit("");
    }

    @Override
    public Boolean hasHtml() {
        return FileUtils.isExsit("");
    }

    @Override
    public void downloadDll() {

    }

    @Override
    public void downloadHtml() {

    }

    @Override
    public void checkAndFix() {
        Boolean hasDll = hasDll();
        Boolean hasHtml = hasHtml();
        if(!hasDll) downloadDll();
        if(!hasHtml) downloadHtml();
    }
}
