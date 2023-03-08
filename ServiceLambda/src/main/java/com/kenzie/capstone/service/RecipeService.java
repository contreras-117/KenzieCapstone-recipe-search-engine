package com.kenzie.capstone.service;

import com.kenzie.capstone.service.model.ExampleData;
import com.kenzie.capstone.service.dao.RecipeDao;
import com.kenzie.capstone.service.model.RecipeRecord;

import javax.inject.Inject;

import java.util.List;
import java.util.UUID;

public class RecipeService {

    private RecipeDao exampleDao;

    @Inject
    public RecipeService(RecipeDao exampleDao) {
        this.exampleDao = exampleDao;
    }

//    public ExampleData getExampleData(String id) {
//        List<RecipeRecord> records = exampleDao.getExampleData(id);
//        if (records.size() > 0) {
//            return new ExampleData(records.get(0).getId(), records.get(0).getData());
//        }
//        return null;
//    }

//    public ExampleData setExampleData(String data) {
//        String id = UUID.randomUUID().toString();
//        RecipeRecord record = exampleDao.setExampleData(id, data);
//        return new ExampleData(id, data);
//    }
}