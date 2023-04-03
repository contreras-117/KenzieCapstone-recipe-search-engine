package com.kenzie.capstone.service.ReviewServiceLambda.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.google.common.collect.ImmutableMap;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewRecord;

import java.util.List;

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
                    "recipeId", new ExpectedAttributeValue(),
                    "reviewerId", new ExpectedAttributeValue())
            ));
        } catch (ConditionalCheckFailedException e) {
            throw new IllegalArgumentException("id has already been used");
        }
        return record;
    }

    public List<ReviewRecord> getRecipeReviews(String recipeId) {
        ReviewRecord reviewRecord = new ReviewRecord();
        reviewRecord.setRecipeId(recipeId);

        DynamoDBQueryExpression<ReviewRecord> queryExpression = new DynamoDBQueryExpression<ReviewRecord>()
                .withHashKeyValues(reviewRecord)
                .withConsistentRead(false);

        return mapper.query(ReviewRecord.class, queryExpression);
    }

}
