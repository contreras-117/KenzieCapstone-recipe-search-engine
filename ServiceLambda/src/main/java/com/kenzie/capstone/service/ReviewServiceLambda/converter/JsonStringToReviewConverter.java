package com.kenzie.capstone.service.ReviewServiceLambda.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kenzie.capstone.service.exceptions.InvalidDataException;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewCreateRequest;

public class JsonStringToReviewConverter {
    public ReviewCreateRequest convert(String body) {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            ReviewCreateRequest reviewCreateRequest = gson.fromJson(body, ReviewCreateRequest.class);
            return reviewCreateRequest;
        } catch (Exception e) {
            throw new InvalidDataException("Referral could not be deserialized");
        }
    }
}
