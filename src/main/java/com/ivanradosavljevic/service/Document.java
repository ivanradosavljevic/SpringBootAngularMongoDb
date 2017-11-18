package com.ivanradosavljevic.service;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import com.ivanradosavljevic.model.ModelCell;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class Document {

	private String filePath;
	private ArrayList<ModelCell> list;
	private File serverFile;
	public Document() {
		list = new ArrayList<>();
	}

	private void generateList() {

		File inputWorkbook = serverFile;
		Workbook workbook;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss",Locale.ENGLISH);
		Cell cell;
		try {
			workbook = Workbook.getWorkbook(inputWorkbook);
			Sheet sheet = workbook.getSheet(0);
			for(int i = 1; i <sheet.getRows(); i++){
				ModelCell mc = new ModelCell();
				cell = sheet.getCell(0,i);
				mc.setClientName(cell.getContents());
				cell = sheet.getCell(1,i);
				mc.setClientId(cell.getContents());
				cell = sheet.getCell(2,i);
				//mc.setInputDate(df.parse(cell.getContents()));
				mc.setInputDate(cell.getContents());
				cell = sheet.getCell(3,i);
				mc.setAmount(Integer.parseInt(cell.getContents()));
				cell = sheet.getCell(4,i);
				mc.setFileMetaDataId(cell.getContents());
				cell = sheet.getCell(5,i);
				mc.setFileName(cell.getContents());
				cell = sheet.getCell(6,i);
				mc.setSource(cell.getContents());
				list.add(mc);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public ArrayList<ModelCell> getList() {
		return list;
	}

	public void setList(ArrayList<ModelCell> list) {
		this.list = list;
	}


	public File getServerFile() {
		return serverFile;
	}

	public void setServerFile(File serverFile) {
		this.serverFile = serverFile;
	}

	public void generateListFromFile() {
		generateList();
		
	}
	
}
