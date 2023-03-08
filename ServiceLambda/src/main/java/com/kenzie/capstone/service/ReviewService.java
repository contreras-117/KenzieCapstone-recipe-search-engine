package com.kenzie.capstone.service;

import com.kenzie.capstone.service.dao.ReviewDao;

import javax.inject.Inject;

public class ReviewService {

    private ReviewDao exampleDao;

    @Inject
    public ReviewService(ReviewDao exampleDao) {
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
