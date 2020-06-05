package com.elca.project.exporttoexcel;

        import com.elca.project.dto.VisitorDto;
        import org.apache.poi.ss.usermodel.*;
        import org.apache.poi.xssf.streaming.SXSSFWorkbook;
        import org.apache.poi.xssf.usermodel.XSSFWorkbook;

        import java.io.ByteArrayInputStream;
        import java.io.ByteArrayOutputStream;
        import java.io.IOException;
        import java.util.Arrays;
        import java.util.List;

public class ExcelGenerator {
    public static ByteArrayInputStream visitorToExcel(List<VisitorDto> visitorC, String[] columns, String sheetname) throws IOException {
        //
        try(
                Workbook workbook = new SXSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ){
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet(sheetname);

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < columns.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(columns[col]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowIdx = 1;
            for (VisitorDto visitor : visitorC) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(visitor.getVisitorId());
                row.createCell(1).setCellValue(visitor.getFirstName());
                row.createCell(2).setCellValue(visitor.getLastName());
                row.createCell(3).setCellValue(visitor.getEmail());
                row.createCell(4).setCellValue(visitor.getPhoneNo());
                row.createCell(5).setCellValue(visitor.getOrganisation());

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
