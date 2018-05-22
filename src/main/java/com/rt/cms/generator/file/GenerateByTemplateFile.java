package com.rt.cms.generator.file;

import java.util.Properties;

import com.rt.cms.generator.model.TableClass;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.CompilationUnit;

import com.rt.cms.generator.formatter.TemplateFormatter;

public class GenerateByTemplateFile extends GeneratedJavaFile {
    public static final String ENCODING = "UTF-8";

    private String targetPackage;

    private String fileName;

    private String templateContent;

    private Properties properties;

    private TableClass tableClass;

    private TemplateFormatter templateFormatter;

    public GenerateByTemplateFile(TableClass tableClass, TemplateFormatter templateFormatter, Properties properties, String targetProject, String targetPackage, String fileName, String templateContent) {
        super(null, targetProject, ENCODING, null);
        this.targetProject = targetProject;
        this.targetPackage = targetPackage;
        this.fileName = fileName;
        this.templateContent = templateContent;
        this.properties = properties;
        this.tableClass = tableClass;
        this.templateFormatter = templateFormatter;
    }

    @Override
    public CompilationUnit getCompilationUnit() {
        return null;
    }

    @Override
    public String getFileName() {
        return templateFormatter.getFormattedContent(tableClass, properties, targetPackage, fileName);
    }

    @Override
    public String getFormattedContent() {
        return templateFormatter.getFormattedContent(tableClass, properties, targetPackage, templateContent);
    }

    @Override
    public String getTargetPackage() {
        return templateFormatter.getFormattedContent(tableClass, properties, targetPackage, targetPackage);
    }

    @Override
    public boolean isMergeable() {
        return false;
    }

}
