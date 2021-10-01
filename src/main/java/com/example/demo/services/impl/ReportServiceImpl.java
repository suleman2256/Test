package com.example.demo.services.impl;

import com.example.demo.entities.Person;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.services.interfaces.ReportService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReportServiceImpl implements ReportService {

    private final PersonRepository personRepository;

    private static final String ENCODING_UTF_8 = "UTF-8";

    @Value("${report.file}")
    String reportFile;

    public ResponseEntity<Resource> exportReportPdf() throws IOException, JRException {
        List<Person> personList = personRepository.findAll();
        JasperReport jasperReport = JasperCompileManager.compileReport(reportFile);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(personList);
        Map<String, Object> parameters = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        outputStream.flush();

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Report.pdf");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        ByteArrayResource byteArrayResource = new ByteArrayResource(outputStream.toByteArray());

        return ResponseEntity.ok()
                .headers(header)
                .contentType(MediaType.APPLICATION_PDF)
                .body(byteArrayResource);
    }

    public ResponseEntity<Resource> exportReportXls() throws JRException, IOException {
        List<Person> personList = personRepository.findAll();
        JasperReport jasperReport = JasperCompileManager.compileReport(reportFile);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(personList);
        Map<String, Object> parameters = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.flush();

        JRXlsExporter exporter = new JRXlsExporter();
        SimpleXlsReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
        reportConfigXLS.setSheetNames(new String[]{"Persons"});
        exporter.setConfiguration(reportConfigXLS);
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        //response.setHeader("Content-Disposition", "attachment;filename=Report.xls");
        //response.setContentType("application/octet-stream");
        exporter.exportReport();

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Report.xls");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        ByteArrayResource byteArrayResource = new ByteArrayResource(outputStream.toByteArray());

        return ResponseEntity.ok()
                .headers(header)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(byteArrayResource);
    }
}
