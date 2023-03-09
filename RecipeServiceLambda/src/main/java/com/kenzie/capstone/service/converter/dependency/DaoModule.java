package com.kenzie.capstone.service.converter.dependency;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.capstone.service.dao.ReviewDao;
import com.kenzie.capstone.service.util.DynamoDbClientProvider;
import dagger.Provides;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Provides DynamoDBMapper instance to DAO classes.
 */
@Module
public class DaoModule {

    @Singleton
    @Provides
    @Named("DynamoDBMapper")
    public DynamoDBMapper provideDynamoDBMapper() {
        return new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());
    }

    @Singleton
    @Provides
    @Named("ReviewDao")
    @Inject
    public ReviewDao provideExampleDao(@Named("DynamoDBMapper") DynamoDBMapper mapper) {
        return new ReviewDao(mapper);
    }

}
