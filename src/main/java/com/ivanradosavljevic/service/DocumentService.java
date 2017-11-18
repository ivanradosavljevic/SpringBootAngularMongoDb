package com.ivanradosavljevic.service;

import org.springframework.web.multipart.MultipartFile;

import com.ivanradosavljevic.model.ModelCell;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.List;

public interface DocumentService {

    ResponseMetadata save(MultipartFile multipartFile) throws Exception;
    
    //bez paginacije
    List<ModelCell> getListFromDb();
    
    //paginacija
    List<ModelCell> getListFromDb(int pageNumber, int sortNumber);
   
    //po idu
    List<ModelCell> getById(String id);
}
