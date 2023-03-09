package com.kenzie.capstone.service.converter.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.kenzie.capstone.service.model.ReviewRecord;

import java.util.Map;

public class RecipeDao {
    private DynamoDBMapper mapper;

    /**
     * Allows access to and manipulation of Match objects from the data store.
     * @param mapper Access to DynamoDB
     */
    public RecipeDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public ReviewRecord addReview(ReviewRecord record) {
        try {
            Map<String, ExpectedAttributeValue> expected =
                    Map.of("recipeId", new ExpectedAttributeValue().withExists(true),
                            "reviewerId", new ExpectedAttributeValue().withExists(true));
            DynamoDBSaveExpression expression = new DynamoDBSaveExpression();
            expression.setExpected(expected);
            mapper.save(record, expression);
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
