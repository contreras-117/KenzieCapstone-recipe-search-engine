package com.kenzie.capstone.service.RecipeServiceLambda.dependency;


//import com.kenzie.capstone.service.RecipeService;
import com.kenzie.capstone.service.RecipeServiceLambda.RecipeService;
import com.kenzie.capstone.service.RecipeServiceLambda.dao.RecipeDao;
import dagger.Provides;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

//@Module(
//    includes = DaoModule.class
//)
//public class ServiceModule {
//
//    @Singleton
//    @Provides
//    @Inject
//    public RecipeService provideLambdaService(@Named("RecipeDao") RecipeDao recipeDao) {
//        return new RecipeService(recipeDao);
//    }
//}

