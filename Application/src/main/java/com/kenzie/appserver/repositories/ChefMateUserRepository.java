package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.ChefMateUserRecord;
import org.springframework.data.repository.CrudRepository;

public interface ChefMateUserRepository extends CrudRepository<ChefMateUserRecord, String> {
}
