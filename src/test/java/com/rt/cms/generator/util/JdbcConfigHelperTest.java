package com.rt.cms.generator.util;

import org.junit.Test;

/**
 * Created by cjbi on 2018/1/5.
 */
public class JdbcConfigHelperTest {
    @Test
    public void testGetTableNames() throws Exception {
        JdbcConfigHelper.getTableNames().forEach(tableName -> {
            System.out.println(tableName);
        });
    }

    @Test
    public void testGetDriverClass() {
        JdbcConfigHelper.DbType dbType = JdbcConfigHelper.getDbType();
        System.out.println(dbType.getDriverClass());
    }

}