package com.ivanradosavljevic.dao;

import org.springframework.stereotype.Repository;

import com.ivanradosavljevic.model.ModelCell;

import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface DocumentDao extends MongoRepository<ModelCell, Long>{
    
}
