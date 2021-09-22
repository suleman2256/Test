package com.example.demo.util;

import com.example.demo.entities.Person;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PersonsPDF {

    private final List<Person> personList;

    public PersonsPDF(List<Person> personList) {
        this.personList = personList;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(6);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("Person ID", font));
        cell.setHorizontalAlignment(Phrase.ALIGN_CENTER);
        table.addCell(cell);

        cell.setPhrase(new Phrase("First Name", font));
        cell.setHorizontalAlignment(Phrase.ALIGN_CENTER);
        table.addCell(cell);

        cell.setPhrase(new Phrase("Last Name", font));
        cell.setHorizontalAlignment(Phrase.ALIGN_CENTER);
        table.addCell(cell);

        cell.setPhrase(new Phrase("Middle Name", font));
        cell.setHorizontalAlignment(Phrase.ALIGN_CENTER);
        table.addCell(cell);

        cell.setPhrase(new Phrase("Date", font));
        cell.setHorizontalAlignment(Phrase.ALIGN_CENTER);
        table.addCell(cell);

        cell.setPhrase(new Phrase("Month Salary", font));
        cell.setHorizontalAlignment(Phrase.ALIGN_CENTER);
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Person person : personList) {
            table.addCell(String.valueOf(person.getId()));
            table.addCell(String.valueOf(person.getFirstName()));
            table.addCell(String.valueOf(person.getLastName()));
            table.addCell(String.valueOf(person.getMiddleName()));
            table.addCell(String.valueOf(person.getDate()));
            table.addCell(String.valueOf(person.getMonthSalary()));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setSize(18);
        font.setColor(Color.BLACK);

        Paragraph p = new Paragraph("List of Persons", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{2.0f, 2.0f, 2.0f, 2.5f, 2.0f, 2.5f});
        table.setHorizontalAlignment(Cell.ALIGN_CENTER);
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
