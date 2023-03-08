package com.kenzie.capstone.service.dependency;


import com.kenzie.capstone.service.ReviewService;
import com.kenzie.capstone.service.dao.ReviewDao;
import dagger.Module;
import dagger.Provides;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Module(
    includes = DaoModule.class
)
public class ServiceModule {

    @Singleton
    @Provides
    @Inject
    public ReviewService provideLambdaService(@Named("ReviewDao") ReviewDao reviewDao) {
        return new ReviewService(reviewDao);
    }
}

