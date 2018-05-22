
package com.rt.cms.generator.formatter;

import com.rt.cms.generator.model.TableClass;

import java.util.Properties;

public interface TemplateFormatter {

    /**
     * 获取根据模板生成的数据
     *
     * @param tableClass
     * @param properties
     * @param targetPackage
     * @param templateContent
     * @return
     */
    String getFormattedContent(TableClass tableClass, Properties properties, String targetPackage, String templateContent);
}
