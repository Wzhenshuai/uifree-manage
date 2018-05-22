
package com.rt.cms.generator.file;

import java.util.Properties;
import java.util.Set;

import com.rt.cms.generator.formatter.ListTemplateFormatter;
import com.rt.cms.generator.model.TableClass;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.CompilationUnit;

public class GenerateByListTemplateFile extends GeneratedJavaFile {
    public static final String ENCODING = "UTF-8";

    private String targetPackage;

    private String fileNameTemplate;

    private String templateContent;

    private Properties properties;

    private Set<TableClass> tableClassSet;

    private ListTemplateFormatter templateFormatter;

    public GenerateByListTemplateFile(Set<TableClass> tableClassSet, ListTemplateFormatter templateFormatter, Properties properties, String targetProject, String targetPackage, String fileNameTemplate, String templateContent) {
        super(null, targetProject, ENCODING, null);
        this.targetProject = targetProject;
        this.targetPackage = targetPackage;
        this.fileNameTemplate = fileNameTemplate;
        this.templateContent = templateContent;
        this.properties = properties;
        this.tableClassSet = tableClassSet;
        this.templateFormatter = templateFormatter;
    }

    @Override
    public CompilationUnit getCompilationUnit() {
        return null;
    }

    @Override
    public String getFileName() {
        return templateFormatter.getFormattedContent(tableClassSet, properties, targetPackage, fileNameTemplate);
    }

    @Override
    public String getFormattedContent() {
        return templateFormatter.getFormattedContent(tableClassSet, properties, targetPackage, templateContent);
    }

    @Override
    public String getTargetPackage() {
        return targetPackage;
    }

    @Override
    public boolean isMergeable() {
        return false;
    }

}
