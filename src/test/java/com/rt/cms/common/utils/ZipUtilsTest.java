package com.rt.cms.common.utils;

import org.junit.Test;

/**
 * Created by cjbi on 2018/1/13.
 */
public class ZipUtilsTest {


    @Test
    public void testCompress() throws Exception {
        ZipUtils.compress("C:\\Users\\cjbi\\GitHub\\wetech-admin\\src\\main\\webapp\\WEB-INF\\tmp\\aaa","C:\\Users\\cjbi\\GitHub\\wetech-admin\\src\\main\\webapp\\WEB-INF\\tmp\\aaa\\aaa.zip");
    }

}