package com.kenzie.appserver.service;

import com.kenzie.appserver.controller.model.ChefMateUserResponse;
import com.kenzie.appserver.controller.model.CreateChefMateUserRequest;
import com.kenzie.appserver.repositories.ChefMateUserRepository;
import com.kenzie.capstone.service.client.LambdaServiceClient;
import org.springframework.stereotype.Service;

@Service
public class ChefMateUserService {

    private ChefMateUserRepository chefMateUserRepository;
    private LambdaServiceClient lambdaServiceClient;

    public ChefMateUserService(ChefMateUserRepository chefMateUserRepository, LambdaServiceClient lambdaServiceClient) {
        this.chefMateUserRepository = chefMateUserRepository;
        this.lambdaServiceClient = lambdaServiceClient;
    }

    /**
     * findByCustomerId
     * @param userId
     * @return The Customer with the given customerId
     */
    public ChefMateUserResponse getUserById(String userId) {

        return null;

    }

    /**
     * addNewCustomer
     *
     * This creates a new customer.  If the referrerId is included, the referrerId must be valid and have a
     * corresponding customer in the DB.  This posts the referrals to the referral service
     * @param chefMateUserRequest
     * @return A CustomerResponse describing the customer
     */
    public ChefMateUserResponse addNewUser(CreateChefMateUserRequest chefMateUserRequest) {

        return null;
    }

    /**
     * updateCustomer - This updates the customer name for the given customer id
     * @param userId - The Id of the customer to update
     */
    public ChefMateUserResponse updateUser(String userId) {

        return null;
    }

    /**
     * deleteCustomer - This deletes the customer record for the given customer id
     * @param userId
     */
    public void deleteUser(String userId) {

    }

        /* -----------------------------------------------------------------------------------------------------------
        Private Methods
       ----------------------------------------------------------------------------------------------------------- */


}
