package com.ivanradosavljevic.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="docs")
public class ModelCell {

    @Id
    private String id;
    private String clientId;   
    private String clientName;
    private String inputDate;
    private int amount;
    private String fileMetaDataId;
    private String fileName;
    private String source;
    
    
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getInputDate() {
		return inputDate;
	}
	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getFileMetaDataId() {
		return fileMetaDataId;
	}
	public void setFileMetaDataId(String fileMetaDataId) {
		this.fileMetaDataId = fileMetaDataId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
}
