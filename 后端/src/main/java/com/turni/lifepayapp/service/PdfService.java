package com.turni.lifepayapp.service;

import java.io.OutputStream;
import java.util.Map;

public interface PdfService {
    void generatePdf(OutputStream outputStream, Map<String, Object> data);
}
