package com.kenzie.capstone.service.ReviewServiceLambda.dependency;

import com.kenzie.capstone.service.ReviewServiceLambda.ReviewService;
import com.kenzie.capstone.service.ReviewServiceLambda.dependency.DaoModule;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Declares the dependency roots that Dagger will provide.
 */
@Singleton
@Component(modules = {DaoModule.class, com.kenzie.capstone.service.ReviewServiceLambda.dependency.ServiceModule.class})
public interface ServiceComponent {
    ReviewService provideLambdaService();
}
