package com.kenzie.appserver.repositories;

import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ReviewRepository extends CrudRepository<ReviewRecord, String> {
}
