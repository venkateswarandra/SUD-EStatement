package com.sud.life.estatement.reports.util;

import com.sud.life.estatement.reports.DTO.NumberToWordsConverter;
import com.sud.life.estatement.reports.services.JBossHomeDirFetcher;
import com.sud.life.estatement.utils.UtilConstants;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JasperReportUtil {

    public static String generateBase64PdfFromJson(
            String jsonString,
            JasperReport jasperReport,
            JBossHomeDirFetcher.ReportPaths paths,
            String instAmount
    ) throws JRException, IOException {

        try (InputStream jsonStream = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8))) {
            JsonDataSource jsonDataSource = new JsonDataSource(jsonStream);

            Map<String, Object> params = new HashMap<>();
            params.put(UtilConstants.HEADER_IMG, paths.getImagePath());
            params.put(UtilConstants.FOOTER_IMG, paths.getImagePath());
            SimpleDateFormat sdf = new SimpleDateFormat(UtilConstants.DATE_FORMAT);
            params.put(UtilConstants.DATE, sdf.format(new Date()));
            params.put(UtilConstants.AMOUNT_IN_WORDS,instAmount);
            // Add Amount in Words if available
          /*  if (instAmountStr != null && !instAmountStr.trim().isEmpty()) {
                long instAmount = (long) Double.parseDouble(instAmountStr);
                String amountInWords = NumberToWordsConverter.convert(instAmount);
                params.put(UtilConstants.AMOUNT_IN_WORDS, amountInWords);
            }*/

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, jsonDataSource);

            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                 BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream)) {

                JRPdfExporter exporter = new JRPdfExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(bufferedOutputStream));
                exporter.exportReport();
                bufferedOutputStream.flush();

                return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
            }
        }
    }
}

