package com.elca.project.exporttoexcel;

import com.elca.project.dto.VisitDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class VisitExcelGenerator {

    public static ByteArrayInputStream visitToExcel(List<VisitDto> visits) throws IOException{
        String[] columns = {"Id", "Firstname", "Lastname", "Email", "Phone Number", "Organisation", "VisitorType", "VisitorType Description", "Badge Number", "Date"};

        try(
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ){

            CreationHelper creationHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("Visit Details");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            //Row for Header
            Row headerRow = sheet.createRow(0);

            //Header
            for (int col = 0; col <columns.length; col++){
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(columns[col]);
                cell.setCellStyle(headerCellStyle);
            }

            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy/mm/dd"));

            int rowIdx = 1;
            for (VisitDto visitDto: visits){
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(visitDto.getVisitId());
                row.createCell(1).setCellValue(visitDto.getVisitor().getFirstName());
                row.createCell(2).setCellValue(visitDto.getVisitor().getLastName());
                row.createCell(3).setCellValue(visitDto.getVisitor().getEmail());
                row.createCell(4).setCellValue(visitDto.getVisitor().getPhoneNo());
                row.createCell(5).setCellValue(visitDto.getVisitor().getOrganisation());
                row.createCell(6).setCellValue(visitDto.getVisitorType().getName());
                row.createCell(7).setCellValue(visitDto.getVisitorType().getDescription());
                row.createCell(8).setCellValue(visitDto.getBadge().getBadgeId());

                Cell dateCell = row.createCell(9);
                dateCell.setCellValue(visitDto.getDate().toString());

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());


        }
    }
}
