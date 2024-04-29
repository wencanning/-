package com.turni.lifepayapp.service.impl;

import com.turni.lifepayapp.service.PdfService;
import com.turni.lifepayapp.utils.PageEventHandler;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.io.OutputStream;
import java.util.Map;
import cn.hutool.core.io.resource.ResourceUtil;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.font.FontProvider;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfServiceImpl implements PdfService {
    @Resource
    private TemplateEngine templateEngine;


    @Override
    public void generatePdf(OutputStream outputStream, Map<String,Object> data) {
        Context context = new Context();
        context.setVariables(data);
        String html = templateEngine.process("bill", context);

        try (PdfWriter pdfWriter = new PdfWriter(outputStream); PdfDocument pdfDocument = new PdfDocument(pdfWriter)) {
            pdfDocument.setDefaultPageSize(PageSize.A4);
            pdfDocument.addEventHandler(PdfDocumentEvent.INSERT_PAGE, new PageEventHandler());

            ConverterProperties converterProperties = new ConverterProperties();
            FontProvider fontProvider = new DefaultFontProvider(true, true, false);
            fontProvider.addFont(FontProgramFactory.createFont(ResourceUtil.readBytes("fonts/SimHei.ttf")));
            converterProperties.setFontProvider(fontProvider);
            HtmlConverter.convertToPdf(html, pdfDocument, converterProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
