package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.ChefMateUserRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ChefMateUserRepository extends CrudRepository<ChefMateUserRecord, String> {
}
