
package com.rt.cms.generator.formatter;

import com.rt.cms.generator.model.TableClass;

import java.util.Properties;
import java.util.Set;

public interface ListTemplateFormatter {

    /**
     * 获取根据模板生成的数据
     *
     * @param tableClassSet
     * @param properties
     * @param targetPackage
     * @param templateContent
     * @return
     */
    String getFormattedContent(Set<TableClass> tableClassSet, Properties properties, String targetPackage, String templateContent);
}
