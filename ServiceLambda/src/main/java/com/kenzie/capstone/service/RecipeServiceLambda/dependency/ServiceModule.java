package com.kenzie.capstone.service.RecipeServiceLambda.dependency;


import com.kenzie.capstone.service.RecipeServiceLambda.RecipeService;
import com.kenzie.capstone.service.RecipeServiceLambda.dao.RecipeDao;
import com.kenzie.capstone.service.RecipeServiceLambda.util.MapperWrapper;
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
    public RecipeService provideLambdaService(@Named("RecipeDao") RecipeDao recipeDao, MapperWrapper mapperWrapper) {
        return new RecipeService(recipeDao, mapperWrapper);
    }
}

