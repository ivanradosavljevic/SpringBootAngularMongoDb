package com.ivanradosavljevic.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ivanradosavljevic.model.ModelCell;
import com.ivanradosavljevic.model.ModelProtocol;
import com.ivanradosavljevic.service.DocumentService;
import com.ivanradosavljevic.service.ResponseMetadata;

import java.io.IOException;
import java.util.List;

@Controller
@RestController
@RequestMapping(value = "/doc")
public class DocumentController {

	@Autowired
	DocumentService documentService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody ResponseMetadata handleFileUpload(@RequestParam(value = "file") MultipartFile file) {
		try{

			return documentService.save(file);
		}
		catch (Exception e) {
			return null;
		}
	}

	// init request
	@RequestMapping(value = "/rest/init", method = RequestMethod.GET)
	public ModelProtocol getProtocol() {
		List<ModelCell> list = documentService.getListFromDb();
		return new ModelProtocol(list.subList(0, 20), list.size());
	}

	// without pagination
	@RequestMapping(value = "/rest", method = RequestMethod.GET)
	public List<ModelCell> get() {
		return documentService.getListFromDb();
	}

	// client id
	@RequestMapping(value = "/rest/{id}", method = RequestMethod.GET)
	public List<ModelCell> get(@PathVariable String id) {
		return documentService.getById(id);
	}

	// with pagination
	@RequestMapping(value = "/rest/{pageNumber}/{sortNumber}", method = RequestMethod.GET)
	public List<ModelCell> get(@PathVariable int pageNumber, @PathVariable int sortNumber) {
		return documentService.getListFromDb(pageNumber, sortNumber);
	}
}
