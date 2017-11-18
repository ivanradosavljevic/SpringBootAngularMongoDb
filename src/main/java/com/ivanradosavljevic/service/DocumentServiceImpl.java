package com.ivanradosavljevic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.ivanradosavljevic.dao.DocumentDao;
import com.ivanradosavljevic.model.ModelCell;
import com.mongodb.MongoClient;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentDao documentDao;

	@Override
	public ResponseMetadata save(MultipartFile file) {

		ResponseMetadata metadata = new ResponseMetadata();
		// create file form byteArray
		if (!file.isEmpty()) {
			try{
				File serverFile = generateFileOnServer(file);
				Document doc = new Document();
				doc.setFilePath(file.getOriginalFilename());
				doc.setServerFile(serverFile);
				doc.generateListFromFile();
				ArrayList<ModelCell> list = doc.getList();
				for (ModelCell mc : list) {
					documentDao.save(mc);
				}
				metadata.setMessage("File uploaded successfully");
				metadata.setStatus(200);
				return metadata;
			}
			catch (Exception e) {
				metadata.setMessage(e.getMessage());
				metadata.setStatus(409);
				return metadata;
			}
			
		} else {
			metadata.setMessage("Something went wrong");
			metadata.setStatus(444);
			return metadata;
		}

	}

	private File generateFileOnServer(MultipartFile file) throws Exception {
		byte[] bytes = file.getBytes();
		// create direcotry to store file
		String rootPath = System.getProperty("user.dir");
		File dir = new File(rootPath + File.separator + "tempFile");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// create the file on server
		File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
		if (!serverFile.exists()) {
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
			return serverFile;
		} else {
			throw new Exception("File already exists");
		}
	}

	@Override
	public List<ModelCell> getListFromDb() {
		return documentDao.findAll();
	}

	@Override
	public List<ModelCell> getListFromDb(int pageNumber, int sortNumber) {
		// TODO Auto-generated method stub
		return documentDao.findAll(new PageRequest(pageNumber, sortNumber)).getContent();
	}

	@Override
	public List<ModelCell> getById(String id) {
		ModelCell mc = new ModelCell();
		Query query = new Query();
		query.addCriteria(Criteria.where("clientId").is(id));
		MongoOperations mongoOps = new MongoTemplate(new MongoClient("localhost"), "test");
		return mongoOps.find(query, ModelCell.class);
	}

}
