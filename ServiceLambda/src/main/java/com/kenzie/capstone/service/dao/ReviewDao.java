package com.kenzie.capstone.service.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class ReviewDao {
    private DynamoDBMapper mapper;

    /**
     * Allows access to and manipulation of Match objects from the data store.
     * @param mapper Access to DynamoDB
     */
    public ReviewDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

//    public ExampleData storeExampleData(ExampleData exampleData) {
//        try {
//            mapper.save(exampleData, new DynamoDBSaveExpression()
//                    .withExpected(ImmutableMap.of(
//                            "id",
//                            new ExpectedAttributeValue().withExists(false)
//                    )));
//        } catch (ConditionalCheckFailedException e) {
//            throw new IllegalArgumentException("id has already been used");
//        }
//
//        return exampleData;
//    }
//
//    public List<RecipeRecord> getExampleData(String id) {
//        RecipeRecord exampleRecord = new RecipeRecord();
//        exampleRecord.setId(id);
//
//        DynamoDBQueryExpression<RecipeRecord> queryExpression = new DynamoDBQueryExpression<RecipeRecord>()
//                .withHashKeyValues(exampleRecord)
//                .withConsistentRead(false);
//
//        return mapper.query(RecipeRecord.class, queryExpression);
//    }
//
//    public RecipeRecord setExampleData(String id, String data) {
//        RecipeRecord exampleRecord = new RecipeRecord();
//
//        try {
//            mapper.save(exampleRecord, new DynamoDBSaveExpression()
//                    .withExpected(ImmutableMap.of(
//                            "id",
//                            new ExpectedAttributeValue().withExists(false)
//                    )));
//        } catch (ConditionalCheckFailedException e) {
//            throw new IllegalArgumentException("id already exists");
//        }
//
//        return exampleRecord;
//    }
}
