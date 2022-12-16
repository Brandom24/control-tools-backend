package com.dct.dasboardcontroltools.reports.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dct.dasboardcontroltools.data.dao.Reports;
import com.dct.dasboardcontroltools.data.service.ReportsService;
import com.dct.dasboardcontroltools.utils.FileUtils;

@Service
public class ReportService {

	@Autowired
	private ReportsService reportsService;
	
	@Autowired
	private FileUtils fileUtils;
	
	public String obtainReport() {
		
		List<Reports> reportsL = reportsService.getAllReports();
		
		try {
			Workbook libro = new HSSFWorkbook();
			Sheet hoja = libro.createSheet("Hoja excel");
//			hoja.setDisplayGridlines(false);
			
			String[] headers = new String[]{
	                "ID",
	                "SUCURSAL",
	                "SUPERVISORY",
	                "IDSUCURSAL"
	        };
			
			Row headerRow = hoja.createRow(0);
	        for (int i = 0; i < headers.length; ++i) {
	            String header = headers[i];
	            Cell cell = headerRow.createCell(i);
	            cell.setCellValue(header);
	        }
			
	        for (int f = 0; f < reportsL.size(); f++) {
				Reports reportData = reportsL.get(f);
				
				Row fila = hoja.createRow(f+1);
				fila.createCell(0).setCellValue(reportData.getId());
				fila.createCell(1).setCellValue(reportData.getSucursal());
				fila.createCell(2).setCellValue(reportData.getSupervisory());
				fila.createCell(3).setCellValue(reportData.getIdPosition());
			}
			
	        File fileXLS = new File("data.xls");
	        FileOutputStream file = new FileOutputStream(fileXLS);
	        libro.write(file);
	        file.close();
			
			String reportB64 = fileUtils.FileToBase64(fileXLS);
			return reportB64;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
}
