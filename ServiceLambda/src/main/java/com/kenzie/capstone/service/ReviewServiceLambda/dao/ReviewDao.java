package com.kenzie.capstone.service.ReviewServiceLambda.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.google.common.collect.ImmutableMap;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewRecord;

public class ReviewDao {
    private DynamoDBMapper mapper;

    /**
     * Allows access to and manipulation of Match objects from the data store.
     * @param mapper Access to DynamoDB
     */
    public ReviewDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public ReviewRecord addReview(ReviewRecord record) {
        try {
            mapper.save(record, new DynamoDBSaveExpression().withExpected(ImmutableMap.of(
                    "recipeId", new ExpectedAttributeValue().withExists(false)
            )));
        } catch (ConditionalCheckFailedException e) {
            throw new IllegalArgumentException("id has already been used");
        }

        return record;
    }
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
