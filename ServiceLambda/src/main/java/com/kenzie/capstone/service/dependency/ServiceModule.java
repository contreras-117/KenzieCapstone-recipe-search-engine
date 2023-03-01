package com.kenzie.capstone.service.dependency;

import com.kenzie.capstone.service.RecipeService;
import com.kenzie.capstone.service.dao.RecipeDao;

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
    public RecipeService provideLambdaService(@Named("RecipeDao") RecipeDao recipeDao) {
        return new RecipeService(recipeDao);
    }
}

