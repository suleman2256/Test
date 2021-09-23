package com.example.demo.util;

import com.example.demo.entities.Person;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class PersonsExcel {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERS = { "Person ID", "First Name", "Last Name", "Middle Name", "Date", "Month Salary" };
    static String SHEET = "Persons";

    public static ByteArrayInputStream personsToExcel(List<Person> personList) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet(SHEET);
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERS.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERS[col]);
               /* CellStyle style = workbook.createCellStyle();
                XSSFFont font = (XSSFFont) workbook.createFont();
                font.setBold(true);
                font.setFontHeight(14);
                style.setFont(font);
                sheet.autoSizeColumn(col);
                cell.setCellStyle(style);
                cell.getCellStyle().setAlignment(HorizontalAlignment.CENTER);*/

            }
            int rowIdx = 1;
            for (Person person : personList) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(person.getId());
                row.createCell(1).setCellValue(person.getFirstName());
                row.createCell(2).setCellValue(person.getLastName());
                row.createCell(3).setCellValue(person.getMiddleName());
                row.createCell(4).setCellValue(person.getDate());
                row.createCell(5).setCellValue(person.getMonthSalary());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Fail to import data to Excel file: " + e.getMessage());
        }
    }
}
