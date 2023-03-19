package com.kenzie.capstone.service.ReviewServiceLambda.dependency;


import com.kenzie.capstone.service.ReviewServiceLambda.ReviewService;
import com.kenzie.capstone.service.ReviewServiceLambda.dao.ReviewDao;
import com.kenzie.capstone.service.ReviewServiceLambda.dependency.DaoModule;
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

