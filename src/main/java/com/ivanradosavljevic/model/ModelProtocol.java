package com.ivanradosavljevic.model;

import java.util.List;

//use only for first request to get number of records and first json 
public class ModelProtocol {

	private List<ModelCell> list;
	private int numberOfRecords;
	
	public ModelProtocol(List<ModelCell> list, int numberOfRecords) {
		super();
		this.list = list;
		this.numberOfRecords = numberOfRecords;
	}
	public List<ModelCell> getList() {
		return list;
	}
	public void setList(List<ModelCell> list) {
		this.list = list;
	}
	public int getNumberOfRecords() {
		return numberOfRecords;
	}
	public void setNumberOfRecords(int numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}
	
	
}
