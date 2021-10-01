package com.example.demo.services.interfaces;

import net.sf.jasperreports.engine.JRException;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface ReportService {

    ResponseEntity<Resource> exportReportPdf() throws IOException, JRException;

    ResponseEntity<Resource> exportReportXls() throws IOException, JRException;
}